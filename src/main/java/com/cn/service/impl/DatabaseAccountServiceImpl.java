package com.cn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.entity.RiskAssessor;
import com.cn.service.AccountService;

//@Service
public class DatabaseAccountServiceImpl implements AccountService{
	private final RiskAssessor riskAssessor;
	
	//@Autowired//��һ�����캯�������ʡ��
	public DatabaseAccountServiceImpl(RiskAssessor riskAssessor) {
		this.riskAssessor = riskAssessor;
	}
}
