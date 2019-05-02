package com.cn.entity;

import java.util.Set;
import java.util.TreeSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User implements java.io.Serializable,java.lang.Comparable{
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(nullable = false)
	private String userName;
	
	@Column(nullable = false)
	private String password;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public static void main(String[] args) {
		User user1 = new User();
		user1.setId(1L);
		User user2 = new User();
		user2.setId(1L);
		Set set = new TreeSet();
		set.add(user1);
		set.add(user2);
		System.out.println(set.size());
	}
	public int compareTo(Object obj) {
		if (this == obj)
			return 0;
		if (obj == null)
			return -1;
		if (getClass() != obj.getClass())
			return -1;
		User other = (User) obj;
		if(id.compareTo(other.id)==0){
			return 0;
		}else if (userName.equals(other.userName)){
			return 0;
		}
		return -1;
	}
}
