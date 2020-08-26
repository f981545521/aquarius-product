package cn.acyou.aquarius.product.conf;

import io.seata.rm.datasource.DataSourceProxy;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;

/**
 * @author youfang
 * @date 2017-12-07 17:37
 **/
@Configuration
@EnableTransactionManagement
public class MyBatisConfigurer implements TransactionManagementConfigurer {

    @Autowired
    private DataSource dataSource;//注入数据源

    public DataSourceProxy getDataSourceProxy() {
        return new DataSourceProxy(dataSource);
    }

    @Bean(name = "sqlSessionFactory")
    protected SqlSessionFactoryBean getSqlSessionFactoryBean(){
        SqlSessionFactoryBean sqlsession = new SqlSessionFactoryBean();
        sqlsession.setDataSource(getDataSourceProxy());
        // typeAliasesPackage：它一般对应我们的实体类所在的包，这个时候会自动取对应包中不包括包名的简单类名作为包括包名的别名。多个package之间可以用逗号或者分号等来进行分隔。(value的值一定要是包的全名)
        sqlsession.setTypeAliasesPackage("cn.acyou.aquarius.product.entity");//扫描entity包 使用别名
        org.apache.ibatis.session.Configuration configuration=new org.apache.ibatis.session.Configuration();
        configuration.setUseGeneratedKeys(true);//使用jdbc的getGeneratedKeys获取数据库自增主键值
        configuration.setUseColumnLabel(true);//使用列别名替换列名 select user as User
        //-自动使用驼峰命名属性映射字段   userId    user_id  **这样使用resultType也会自动映射了**
        //sqlsession.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
        configuration.setMapUnderscoreToCamelCase(true);
        sqlsession.setConfiguration(configuration);
        sqlsession.setFailFast(true);

        //添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            // mapperLocations：它表示我们的Mapper文件存放的位置，当我们的Mapper文件跟对应的Mapper接口处于同一位置的时候可以不用指定该属性的值。
            sqlsession.setMapperLocations(resolver.getResources("classpath*:mapper/*.xml"));
            return sqlsession;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }


    @Bean
    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }

}
