package com.cn.test;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {
	//13155688 62114472
	public static void main(String[] args) {
		List list1 = new ArrayList();
		List list2 = new ArrayList();
		Long i = 0L;
		try {
			FileOutputStream f = new FileOutputStream("E:/testFiles2");
			ObjectOutputStream s = new ObjectOutputStream(f);
			for(;i<1000000L;i++){
				Map map = new HashMap();
				map.put("id", i);
				map.put("address", "address"+i);
				map.put("logonName", "logonName"+i);
				map.put("name", "name"+i);
				map.put("password", "password"+i);
				map.put("phone", "phone"+i);
				map.put("sex", i);
				map.put("xueli", i);
				s.writeObject(map);
				s.flush();
				/*UserInfo userInfo = new UserInfo();
				userInfo.setId(i);
				userInfo.setAddress("address"+i);
				userInfo.setLogonName("logonName"+i);
				userInfo.setName("name"+i);
				userInfo.setPassword("password"+i);
				userInfo.setPhone("phone"+i);
				userInfo.setSex(i);
				userInfo.setXueli(i);
				s.writeObject(userInfo);*/
			}
			
			
		} catch (Exception e) {
			System.out.println(i);
			e.printStackTrace();
		}
	}
	
	public static class UserInfo implements java.io.Serializable{
		private Long id;
		private String name;
		private String phone;
		private String address;
		private Long sex;
		private Long xueli;
		private String logonName;
		private String password;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public Long getSex() {
			return sex;
		}
		public void setSex(Long sex) {
			this.sex = sex;
		}
		public Long getXueli() {
			return xueli;
		}
		public void setXueli(Long xueli) {
			this.xueli = xueli;
		}
		public String getLogonName() {
			return logonName;
		}
		public void setLogonName(String logonName) {
			this.logonName = logonName;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
	}
	
}
