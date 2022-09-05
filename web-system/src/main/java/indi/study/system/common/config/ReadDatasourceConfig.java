package indi.study.system.common.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 *  数据源配置类
 *  basePackages = "..." 扫描包dao文件
 *  @ConfigurationProperties 路径找的是yml数据源配置
 *
 * */
@Configuration
@MapperScan(basePackages = "indi.study.system.dao.read", sqlSessionTemplateRef  = "sqlSessionTemplate")
public class ReadDatasourceConfig {
    // mapper类的包路径---dao文件路径
    static final String MAPPER_PACKAGE = "indi.study.system.dao.read";
    //自定义mapper的xml文件路径
    private static final String MAPPER_XML_PATH = "classpath:/mybatis/mapper/read/*.xml";
    // sqlSession工厂名称
    static final String SESSION_FACTORY = "readSqlSessionFactory";
    // 数据源名称
    private static final String DATASOURCE_NAME = "readDataSource";
    // 数据源配置的前缀
    private static final String DATASOURCE_PREFIX = "spring.datasource.druid.read";
    //DataSourceTransactionManager的名称，建议按照数据库的名称+TransactionManager驼峰命名的方式赋值
    //如demo数据库，命名如下
    private static final String TRANSACTION_MANAGER_NAME = "readTransactionManager";

    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.druid.read")
    public DataSource readDataSource() {
        System.out.println("1111111111111111111111");
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public SqlSessionFactory sqlSessionFactory(@Qualifier("readDataSource") DataSource dataSource) throws Exception{
        System.out.println("222222222222222222");
//        final SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
//        sessionFactoryBean.setDataSource(druidDataSource());
//        try {
//            PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//            sessionFactoryBean.setMapperLocations(resolver.getResources(MAPPER_XML_PATH));
//            sessionFactoryBean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
//            return sessionFactoryBean.getObject();
//        } catch (Exception e) {
//            throw new RuntimeException(e.getMessage());
//        }
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        // 开启数据源的小驼峰映射
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        bean.setConfiguration(configuration);
        return bean.getObject();
    }

    @Bean
    @Primary
    public DataSourceTransactionManager transactionManager(@Qualifier("readDataSource") DataSource dataSource) {
        System.out.println("333333333333333333333333S");
        return new DataSourceTransactionManager(dataSource);
    }
    @Bean
    @Primary
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        System.out.println("44444444444444444444");
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
