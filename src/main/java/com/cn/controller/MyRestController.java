package com.cn.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cn.entity.Customer;
import com.cn.entity.User;

@RestController
@Configuration
@RequestMapping(value="/users1")
public class MyRestController {

	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public User getUser(@PathVariable Long id) {
		System.out.println("======id====>"+id);
		User user = new User();
		user.setId(id);
		user.setUserName("userName"+id);
		user.setPassword("password");
		return user;
	}

	@RequestMapping(value="/{id}/customers", method=RequestMethod.GET)
	List<Customer> getUserCustomers(@PathVariable Long id) {
		System.out.println("=======id======>"+id);
		Customer customer = new Customer();
		List list = new ArrayList();
		customer.setId(1L);
		list.add(customer);
		return list;
	}

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public User deleteUser(@PathVariable Long id) {
		System.out.println("=======id=====>"+id);
		User user = new User();
		user.setId(id);
		user.setUserName("userName"+id);
		user.setPassword("password");
		return user;
	}

}
