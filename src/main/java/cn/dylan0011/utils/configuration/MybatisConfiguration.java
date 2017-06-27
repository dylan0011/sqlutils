package cn.dylan0011.utils.configuration;

import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.util.ObjectUtils;

import javax.sql.DataSource;

/**
 * Created by yuhp@terminus.io on 2017/6/27.
 * Desc:
 */
@Configuration
@ConditionalOnClass({SqlSessionFactory.class, SqlSessionFactoryBean.class})
@ConditionalOnMissingBean(SqlSessionTemplate.class)
@EnableConfigurationProperties(MybatisProperties.class)
@AutoConfigureAfter(DataSourceAutoConfiguration.class)
@SuppressWarnings("SpringJavaAutowiringInspection")
public class MybatisConfiguration {

    private final DataSource dataSource;
    private final MybatisProperties mybatisProperties;

    private final DatabaseIdProvider provider;

    @Autowired
    public MybatisConfiguration(MybatisProperties mybatisProperties, ObjectProvider<DatabaseIdProvider> provider, DataSource dataSource) {
        this.mybatisProperties = mybatisProperties;
        this.provider = provider.getIfAvailable();
        this.dataSource = dataSource;
    }

    @Bean
    @ConditionalOnMissingBean(PlatformTransactionManager.class)
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(dataSource);
        if (this.provider != null) {
            factory.setDatabaseIdProvider(this.provider);
        }
//        if (StringUtils.hasLength(this.mybatisProperties.getTypeAliasesPackage())) {
//            factory.setTypeAliasesPackage(TypeAliasPackageParser.flatPackageNames(this.mybatisProperties.getTypeAliasesPackage()));
//        }
//        if (StringUtils.hasLength(this.mybatisProperties.getTypeHandlersPackage())) {
//            factory.setTypeHandlersPackage(this.mybatisProperties.getTypeHandlersPackage());
//        }
        final Resource[] mapperLocations = this.mybatisProperties.resolveMapperLocations();
        if (!ObjectUtils.isEmpty(mapperLocations)) {
            factory.setMapperLocations(mapperLocations);
        }
        System.out.println("********************");
        return factory.getObject();
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory,
                this.mybatisProperties.getExecutorType());
    }

}
