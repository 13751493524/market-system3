package com.cn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.entity.RiskAssessor;
import com.cn.service.AccountService;

//@Service
public class DatabaseAccountServiceImpl implements AccountService{
	private final RiskAssessor riskAssessor;
	
	//@Autowired//有一个构造函数则可以省略
	public DatabaseAccountServiceImpl(RiskAssessor riskAssessor) {
		this.riskAssessor = riskAssessor;
	}
}
