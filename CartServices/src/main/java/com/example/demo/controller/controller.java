package com.example.demo.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.cart_list;
import com.example.demo.entity.cart_repo;
import com.example.demo.entity.item_list;
import com.example.demo.service.Service;

@RestController
@CrossOrigin
@RequestMapping("rest")
public class controller {
	@Autowired
	com.example.demo.entity.item_list_repo item_list_repo;
	
	@Autowired
	cart_repo cart_repo;
	
	
	@Autowired
	Service service;
	
	@RequestMapping(value="getAll",method = RequestMethod.GET)
	public Map<String,Object> getAll()
	{
		
		Map<String,Object> result=new HashMap<>();
	try {	
		List<item_list> itemlist = item_list_repo.findAll();
		
		System.out.println(itemlist);
		if(itemlist.size()>0)
		{
		result.put("Data", itemlist);
		result.put("staus", true);
		}
		else {
			result.put("staus", false);
			result.put("message", "Data Not Present");
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			result.put("staus", false);
			result.put("message", e.getMessage());
		}
		return result;
		
	}
	@RequestMapping(value="listNewItem",method = RequestMethod.POST)
	public Map<String,Object> listItem(@RequestBody Map<String,Object> map)
	{
				return service.listItem(map);
		
	}
	@PutMapping("/update/{id}")
    public ResponseEntity<item_list> update(@RequestBody item_list newItem, @PathVariable Long id) {
    	 Optional<item_list> updatingDATA = item_list_repo.findById(id);
    	 if (updatingDATA.isPresent()) {
    		 item_list _tutorial = updatingDATA.get();
    	      _tutorial.setItem_name(newItem.getItem_name());
    	      _tutorial.setItem_price(newItem.getItem_price());
    	      return new ResponseEntity<>(item_list_repo.save(_tutorial), HttpStatus.OK);
    	    } else {
    	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    	    }
 
       
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") long id) {
      try {
    	  item_list_repo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
      }
    }
    @RequestMapping(value="listCart",method = RequestMethod.POST)
	public Map<String,Object> listCart(@RequestBody List<cart_list> list)
	{
    	Map<String,Object> result=new HashMap<String, Object>();
    	try{List<cart_repo> lst=Arrays.asList();
    	Iterable<cart_list> cart=cart_repo.saveAll(list);
    	for (cart_list emp : cart) {
           System.out.println(emp);
        }
    	result.put("status", HttpStatus.OK);
    	result.put("value", cart);
    	}
    	catch(Exception e)
    	{ 	result.put("status", HttpStatus.EXPECTATION_FAILED);
    	   result.put("message", e.getMessage());
    		e.printStackTrace();
    	}
		return result;
				
		
	}
    @RequestMapping(value="getAllOrders",method = RequestMethod.GET)
   	public Map<String,Object> getAllOrders()
   	{
       Map<String,Object> result=new HashMap<>();
		try {
		List<cart_list> cartList = cart_repo.findAll();
		
		System.out.println(cartList);
		if(cartList.size()>0)
		{
		result.put("Data", cartList);
		result.put("staus", true);
		}
		else {
			result.put("staus", false);
			result.put("message", "Data Not Present");
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			result.put("staus", false);
			result.put("message", e.getMessage());
		}
		return result;
   	}
}
