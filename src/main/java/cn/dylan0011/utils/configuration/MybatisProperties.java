package cn.dylan0011.utils.configuration;

import lombok.Data;
import org.apache.ibatis.session.ExecutorType;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yuhp@terminus.io on 2017/6/27.
 * Desc:
 */
@ConfigurationProperties(prefix = MybatisProperties.PREFIX)
@Data
public class MybatisProperties {
    static final String PREFIX = "mybatis";

    private String[] mapperLocations;
    private String typeAliasesPackage;

    /**
     * executorType
     */
    private ExecutorType executorType = ExecutorType.SIMPLE;

    public Resource[] resolveMapperLocations() {
        List<Resource> resources = new ArrayList<>();
        if (this.mapperLocations != null) {
            for (String mapperLocation: this.mapperLocations) {
                Resource[] mappers;
                try {
                    mappers = new PathMatchingResourcePatternResolver().getResources(mapperLocation);
                    resources.addAll(Arrays.asList(mappers));
                } catch (IOException e) {
                    // ignore this shit
                }
            }
        }

        Resource[] mapperLocations = new Resource[resources.size()];
        mapperLocations = resources.toArray(mapperLocations);
        return mapperLocations;
    }

}
