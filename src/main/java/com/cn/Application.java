package com.cn;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.cn.common.MyConfiguration;

/**
 * @RestControllerע���൱��@ResponseBody �� @Controller����һ�������
 * 1) ���ֻ��ʹ��@RestControllerע��Controller����Controller�еķ����޷�����jspҳ�棬����html��
 * ���õ���ͼ������ InternalResourceViewResolver�������ã����ص����ݾ���Return ������ݡ�
 * 
 * 2) �����Ҫ���ص�ָ��ҳ�棬����Ҫ�� @Controller�����ͼ������InternalResourceViewResolver���С�
    �����Ҫ����JSON��XML���Զ���mediaType���ݵ�ҳ�棬����Ҫ�ڶ�Ӧ�ķ����ϼ���@ResponseBodyע�⡣
 * @author GJB
 *
 *@SpringBootApplicationע�����൱��ʹ��@Configuration�� @EnableAutoConfiguration�Լ�@ComponentScan�����ǵ�Ĭ����
 *
 *@ComponentScan(value="url")��ɨ��@Controllerע���µ���
 */
//@RestController//�൱��ֻ����json����xml��Щ�����ܷ���һ����ͼ������
//@EnableAutoConfiguration//���ע�͸���Spring Boot��������ӵ�jar������ϵ���²⡱����Ҫ�������Spring
@SpringBootApplication(scanBasePackages = {"com.cn.controller"}) 
public class Application extends SpringBootServletInitializer {
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}
	public static void main(String[] args) {
		
		SpringApplication.run(Application.class, args);
		
		ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(MyConfiguration.class);

        /*String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanName : beanDefinitionNames) {
            System.out.println("beanName: " + beanName);
        }*/
        
        System.out.println("===========hread.currentThread().getContextClassLoader()=====>"+Thread.currentThread().getContextClassLoader().getResource(""));
       
	}
}
