package indi.study.system.common.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

/**
 *  数据源配置类
 *  basePackages = "..." 扫描包dao文件
 *  @ConfigurationProperties 路径找的是yml数据源配置
 *
 * */
@Configuration
@MapperScan(basePackages = WriteDatasourceConfig.MAPPER_PACKAGE, sqlSessionTemplateRef  = WriteDatasourceConfig.SESSION_TEMPLATE)
public class WriteDatasourceConfig {
    // mapper类的包路径---dao文件路径
    static final String MAPPER_PACKAGE = "indi.study.system.dao.write";
    //自定义mapper的xml文件路径
    private static final String MAPPER_XML_PATH = "classpath:/mybatis/mapper/write/*.xml";
    // sqlSession工厂名称
    static final String SESSION_FACTORY = "writeSqlSessionFactory";
    // sqlSession实现类
    static final String SESSION_TEMPLATE = "writeSqlSessionTemplate";
    // 数据源名称
    private static final String DATASOURCE_NAME = "writeDataSource";
    // 数据源配置的前缀
    private static final String DATASOURCE_PREFIX = "spring.datasource.druid.write";
    //DataSourceTransactionManager的名称，建议按照数据库的名称+TransactionManager驼峰命名的方式赋值
    //如demo数据库，命名如下
    private static final String TRANSACTION_MANAGER_NAME = "writeTransactionManager";

    @Bean(name = DATASOURCE_NAME)
    @ConfigurationProperties(prefix = DATASOURCE_PREFIX)
    public DruidDataSource druidDataSource() {
        return new DruidDataSource();
    }

    @Bean(name = SESSION_FACTORY)
    public SqlSessionFactory sqlSessionFactory(@Qualifier(DATASOURCE_NAME) DruidDataSource druidDataSource) throws Exception{
        final SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(druidDataSource);
        try {
            PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            sessionFactoryBean.setMapperLocations(resolver.getResources(MAPPER_XML_PATH));
            sessionFactoryBean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
            return sessionFactoryBean.getObject();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Bean(name = TRANSACTION_MANAGER_NAME)
    public DataSourceTransactionManager transactionManager(@Qualifier(DATASOURCE_NAME) DruidDataSource druidDataSource) {
        return new DataSourceTransactionManager(druidDataSource);
    }
    @Bean(name = SESSION_TEMPLATE)
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier(SESSION_FACTORY) SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}