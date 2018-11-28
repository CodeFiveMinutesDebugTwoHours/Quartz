package com.zxf.quartz;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainTest {

    public static void main(String[] args) {
        System.out.println("Test start."+Thread.currentThread().getName());
        ApplicationContext context = new ClassPathXmlApplicationContext("quartz-job-config.xml");
        //如果配置文件中将startQuertz bean的lazy-init设置为false 则不用实例化
        //context.getBean("startQuertz");
        System.out.println("Test end..");
    }
}
