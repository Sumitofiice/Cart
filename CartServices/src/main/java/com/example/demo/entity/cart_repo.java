package com.example.demo.entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface cart_repo  extends JpaRepository<cart_list, Long>{
//	List<item_list> findByPublished(boolean published);
//	  List<item_list> findByTitleContaining(String title);

}
