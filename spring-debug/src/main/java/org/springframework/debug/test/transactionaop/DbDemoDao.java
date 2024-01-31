package org.springframework.debug.test.transactionaop;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * CREATE TABLE `db_demo` (
 * `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
 * `name` varchar(128) NOT NULL COMMENT 'name',
 * `city` varchar(128) NOT NULL DEFAULT 'bj' COMMENT 'city',
 * `age` int(10) NOT NULL DEFAULT '0' COMMENT 'id', PRIMARY KEY (`id`) ) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT
 * CHARSET=utf8 COMMENT='db_demo表'
 */
@Repository
public class DbDemoDao {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  public List<Map<String, Object>> query() {
    List<Map<String, Object>> rows = jdbcTemplate.query("select * from db_demo", (resultSet, i) -> {
      // 此接口回调接口调用前已经调用resultSet.next()处理，并记录行号，通过参数i进行传递
      Map<String, Object> row = new HashMap<>();
      row.put("rowId", i);
      row.put("name", resultSet.getString("name"));
      row.put("age", resultSet.getInt("age"));
      row.put("city", resultSet.getString("city"));
      return row;
    });

    return rows;
  }

  public int queryMaxId() {
    Integer id = jdbcTemplate.query("select max(id) from db_demo", (resultSet) -> {
      // return resultSet.getInt(1); // resultSet直接获取触发异常，需要先通过next方法移动游标，，触发事务completeTransactionAfterThrowing(txInfo, ex);逻辑
      while (resultSet.next()) {
//		  return resultSet.getInt("id"); // 触发异常，max(id) 没有重命名
		  return resultSet.getInt("max(id)");
//        return resultSet.getInt(1);
      }
      return 1;
    });
    return id;
  }

  public int insert() {
    int update =
        jdbcTemplate.update("insert into db_demo(name, age, city) values(?, ?, ?)", "张三", 18, "北京");
    return update;
  }

  public int update(int id, String name) {
    int update = jdbcTemplate.update("update db_demo set name = ? where id = ?", name, id);
    return update;
  }
}
