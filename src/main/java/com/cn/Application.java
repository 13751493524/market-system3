package com.cn;
import java.util.EnumSet;

import javax.servlet.DispatcherType;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import com.cn.controller.LoginFilter;

/**
 * @RestController注解相当于@ResponseBody ＋ @Controller合在一起的作用
 * 1) 如果只是使用@RestController注解Controller，则Controller中的方法无法返回jsp页面，或者html，
 * 配置的视图解析器 InternalResourceViewResolver不起作用，返回的内容就是Return 里的内容。
 * 
 * 2) 如果需要返回到指定页面，则需要用 @Controller配合视图解析器InternalResourceViewResolver才行。
    如果需要返回JSON，XML或自定义mediaType内容到页面，则需要在对应的方法上加上@ResponseBody注解。
 * @author GJB
 *
 *@SpringBootApplication注释是相当于使用@Configuration， @EnableAutoConfiguration以及@ComponentScan与他们的默认属
 *
 *@ComponentScan(value="url")会扫描@Controller注释下的类
 */
//@RestController//相当于只返回json或者xml这些，不能返回一个视图解析器
//@EnableAutoConfiguration//这个注释告诉Spring Boot根据你添加的jar依赖关系“猜测”你想要如何配置Spring
@SpringBootApplication(scanBasePackages = {"com.cn"}) 
public class Application extends SpringBootServletInitializer {
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		/*ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(MyConfiguration.class);

        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanName : beanDefinitionNames) {
            System.out.println("beanName: " + beanName);
        }*/
       
	}
	/**
	 * 地址拦截器
	 * @return
	 */
	@Bean
	public FilterRegistrationBean myFilter() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(new LoginFilter());
		registration.setDispatcherTypes(EnumSet.allOf(DispatcherType.class));
		return registration;
	}
}
