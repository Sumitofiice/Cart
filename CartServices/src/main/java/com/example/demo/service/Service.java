package com.example.demo.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.dao.Dao;
@org.springframework.stereotype.Service
public class Service {
@Autowired
Dao dao;

public Map<String,Object> listItem(Map<String,Object> map)
{
	return dao.listItem(map);
}
}
