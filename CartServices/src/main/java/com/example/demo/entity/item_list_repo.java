package com.example.demo.entity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface item_list_repo extends JpaRepository<item_list, Long>{
//	List<item_list> findByPublished(boolean published);
//	  List<item_list> findByTitleContaining(String title);
}
