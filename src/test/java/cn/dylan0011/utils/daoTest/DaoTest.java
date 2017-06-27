package cn.dylan0011.utils.daoTest;

import cn.dylan0011.utils.BaseTest;
import com.google.common.base.Stopwatch;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;

/**
 * Created by yuhp@terminus.io on 2017/6/27.
 * Desc:
 */
public class DaoTest extends BaseTest {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Test
    public void getSqlsessionFactory() {
        Stopwatch stopwatch = Stopwatch.createStarted();
        System.out.println("***");
        System.out.println(stopwatch.elapsed(TimeUnit.MILLISECONDS));
    }

}
