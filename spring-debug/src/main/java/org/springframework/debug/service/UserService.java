package org.springframework.debug.service;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.debug.bean.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

/**
 * @create 2023/11/9 20:40
 * @description
 */
@Component
public class UserService implements InitializingBean {

  @Autowired
  private OrderService orderService;

  @Autowired
  private JdbcTemplate jdbcTemplate;

  private User user;

  @PostConstruct
  public void init() {
    System.out.println("@PostConstruct UserService user init");
    user = new User();
    user.setName("zhangsan");
  }

  public void test() {
    System.out.println(orderService + ", User: " + user);
    List<Map<String, Object>> result =
        jdbcTemplate.query("select * from emp", new RowMapper<Map<String, Object>>() {
          @Override
          public Map<String, Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            Map<String, Object> row = new HashMap<>();
            for (int i = 1; i <= columnCount; i++) {
              String columnName = metaData.getColumnLabel(i);
              Object value = rs.getObject(i);
              row.put(columnName, value);
            }
            return row;
          }
        });

    System.out.println("query result: " + result);
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    System.out.println("afterPropertiesSet UserService user init");
    user = new User();
    user.setName("lisan");
  }
}
