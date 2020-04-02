package com.example.demo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.item_list;
import com.example.demo.entity.item_list_repo;
@Repository
public class Dao {
	@Autowired
	JdbcTemplate jdbctemplate;
	@Autowired
	com.example.demo.entity.item_list_repo item_list_repo;

	public Map<String, Object> listItem(Map<String, Object> map) {
		Map<String, Object> result = new HashMap<>();
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("item_list");
//		EntityManager em = emf.createEntityManager();
		try {
//			int id = (int) map.get("id");
//			Long Id=new Long(id);
			String item_name = map.get("item_name").toString();
			String item_price = map.get("item_price").toString();
			int item_no = (int) map.get("item_no");
//	List<item_list> employeeList = item_list_repo.findAll();
//	
//	System.out.println(employeeList);
//	result.put("Data", employeeList);

//			em.getTransaction().begin();
			item_list itemList = new item_list();
//			itemList.setId(Id);
			itemList.setItem_name(item_name);
			itemList.setItem_price(item_price);
			itemList.setItem_no(item_no);
			item_list_repo.save(itemList);
//			em.persist(itemList);
			result.put("Status",true);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("Status",false);
			result.put("message",e.getMessage());
		} finally {
//			em.getTransaction().commit();
//
//			emf.close();
//			em.close();
		}
		return result;
	}
}
