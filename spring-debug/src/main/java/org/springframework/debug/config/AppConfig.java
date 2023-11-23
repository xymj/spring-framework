package org.springframework.debug.config;

import org.springframework.context.annotation.*;
import org.springframework.debug.service.OrderService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @create 2023/11/9 20:41
 * @description
 */

@Configuration // 不添此注解，不会进行包扫描，ConfigurationClassBeanDefinitionReader进行扫描bean的创建和初始化
// @ComponentScans(value = {@ComponentScan(basePackages = "org.springframework.debug")})
@ComponentScan(basePackages = "org.springframework.debug") // 路径不对会抛java.lang.IllegalArgumentException:
                                                           // @EnableAsync annotation metadata was
                                                           // not injected异常
@EnableAspectJAutoProxy // 开启代理
// @EnableTransactionManagement // 开启事务
public class AppConfig {

  @Bean
  public OrderService orderService() {
    return new OrderService();
  }

  @Bean
  public OrderService orderService1() {
    return new OrderService();
  }

  @Bean
  public DataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
	dataSource.setDriverClassName("com.mysql.jdbc.Driver");
    dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/db_test?characterEncoding=utf-8&SSL=false");
    dataSource.setUsername("root");
    dataSource.setPassword("root");
    return dataSource;
  }

  @Bean
  public JdbcTemplate jdbcTemplate() {
    return new JdbcTemplate(dataSource());
  }


  @Bean
  public DataSourceTransactionManager dataSourceTransactionManager() {
    DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
    dataSourceTransactionManager.setDataSource(dataSource());
    return dataSourceTransactionManager;
  }


}
