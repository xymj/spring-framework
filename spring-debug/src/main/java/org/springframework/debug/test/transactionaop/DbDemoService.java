package org.springframework.debug.test.transactionaop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class DbDemoService {

	@Autowired
	private DbDemoDao dbDemoDao;

	@Transactional
	public void query() {
		List<Map<String, Object>> query = dbDemoDao.query();
		System.out.println(query);
	}

	@Transactional
    public void insert() {
		int insert = dbDemoDao.insert();
		System.out.println("insert count: " + insert);
		query();

		int id = dbDemoDao.queryMaxId();
		update(id, "test --> " + id);
		query();
	}


	@Transactional
    public void update(int id, String name) {
		int update = dbDemoDao.update(id, name);
		System.out.println("update count: " + update);
	}
}
