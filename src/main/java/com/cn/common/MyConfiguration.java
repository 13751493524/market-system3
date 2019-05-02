package com.cn.common;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;

/**
 * 如果发现有一些类不需要自动装配，但是装配了的，则配置不需要自动装配的class
 * @author GJB
 *
 */
@ComponentScan(value = "com.cn.controller")
@Configuration
@EnableAutoConfiguration
public class MyConfiguration {

}
