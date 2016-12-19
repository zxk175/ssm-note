package com.zxk175.ssm_note.conf.spring;

import com.alibaba.druid.pool.DruidDataSource;
import com.zxk175.ssm_note.conf.constant.DBConstant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by zxk175 on 16/12/7.
 */

@Configuration
@EnableTransactionManagement
public class SpringDaoXml {
    private static final Logger logger = LogManager.getLogger(SpringDaoXml.class);

    ///**
    // * jndi方式配置数据源
    // *
    // * @return
    // */
    //@Bean(name = "dataSource")
    //public JndiObjectFactoryBean jndiObjectFactoryBean() {
    //    JndiObjectFactoryBean jndiObjectFactoryBean = new JndiObjectFactoryBean();
    //    jndiObjectFactoryBean.setJndiName(DBConstant.JNDINAME);
    //    return jndiObjectFactoryBean;
    //}

    /**
     * 配置数据源
     *
     * @return
     */
    @Bean(name = "dataSource", initMethod = "init", destroyMethod = "close")
    public DruidDataSource dataSource() {
        logger.info("============> url: {}", DBConstant.URL);
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(DBConstant.URL);
        dataSource.setUsername(DBConstant.USERNAME);
        dataSource.setPassword(DBConstant.PASSWORD);
        dataSource.setDriverClassName(DBConstant.DRIVER);
        dataSource.setInitialSize(DBConstant.INITIALSIZE);
        dataSource.setMaxActive(DBConstant.MAXACTIVE);
        dataSource.setMaxWait(DBConstant.MAXWAIT);
        dataSource.setMinIdle(DBConstant.MINIDLE);
        dataSource.setValidationQuery(DBConstant.VALIDATIONQUERY);
        dataSource.setTestOnBorrow(DBConstant.TESTONBORROW);
        dataSource.setTestOnReturn(DBConstant.TESTONRETURN);
        dataSource.setTestWhileIdle(DBConstant.TESTWHILEIDLE);
        dataSource.setTimeBetweenEvictionRunsMillis(DBConstant.TIMEBETWEENEVICTIONRUNSMILLIS);
        dataSource.setMinEvictableIdleTimeMillis(DBConstant.MINEVICTABLEIDLETIMEMILLIS);
        dataSource.setRemoveAbandoned(DBConstant.REMOVEABANDONED);
        dataSource.setRemoveAbandonedTimeout(DBConstant.REMOVEABANDONEDTIMEOUT);
        dataSource.setLogAbandoned(DBConstant.LOGABANDONED);
        return dataSource;
    }

    /**
     * SqlSessionFactory配置
     *
     * @return
     * @throws Exception
     */
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactoryBean sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        String res = "mybatis/mybatis-config.xml";
        Resource resource = new ClassPathResource(res);
        sessionFactory.setConfigLocation(resource);
        return sessionFactory;
    }

    /**
     * mybatis映射文件配置
     *
     * @return
     */
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.zxk175.ssm_note.dao");
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        return mapperScannerConfigurer;
    }

    /**
     * 配置事务管理器
     *
     * @return
     */
    @Bean
    public DataSourceTransactionManager txManager() {
        return new DataSourceTransactionManager(dataSource());
    }
}
