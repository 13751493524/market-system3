package com.cn.common;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;

/**
 * ���������һЩ�಻��Ҫ�Զ�װ�䣬����װ���˵ģ������ò���Ҫ�Զ�װ���class
 * @author GJB
 *
 */
@ComponentScan(value = "com.cn.controller")
@Configuration
@EnableAutoConfiguration
public class MyConfiguration {

}
