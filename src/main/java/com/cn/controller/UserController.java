package com.cn.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cn.common.contorller.BaseController;
import com.cn.entity.Customer;
import com.cn.entity.User;
import com.cn.service.UserService;

@RestController
@Configuration
@RequestMapping(value="/users")
public class UserController extends BaseController{
	@Resource
    private UserService userService;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public User getUser(@PathVariable Long id) {
		User user = new User();
		return user;
	}

	@RequestMapping(value="/{id}/customers", method=RequestMethod.POST)
	List<Customer> getUserCustomers(@PathVariable Long id) {
		System.out.println("=======id======>"+id);
		Customer customer = new Customer();
		List list = new ArrayList();
		customer.setId(1L);
		list.add(customer);
		return list;
	}
	
	@PostMapping(value="/addUser")
	public Map addUser(@RequestBody User user,HttpServletRequest request) throws InterruptedException{
		Map map = new HashMap();
		Thread.sleep(2000);
		User sessionUser = (User)request.getSession().getAttribute("user");
		synchronized (sessionUser) {
			User existUser = userService.getUserByUserName(user);
			if(existUser != null){
				map.put("msg", "该用户已存在！");
			}else{
				userService.addUser(user);
				map.put("msg", "注册成功！");
			}
		}
		return map;
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
