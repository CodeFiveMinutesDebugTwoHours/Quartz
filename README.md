# Quartz
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:context="http://www.springframework.org/schema/context"
    xmlns:aop="http://www.springframework.org/schema/aop"
    xmlns:tx="http://www.springframework.org/schema/tx"
    xsi:schemaLocation="http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-4.3.xsd
        http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-4.3.xsd
        http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx-4.3.xsd
        http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.3.xsd">
 
    <!--     ****************************************配置跑批**************************************-->
            <!-- 线程执行器配置，用于任务注册 --> 
     <bean id="executor" class="org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor">  
     <property name="corePoolSize" value="10" />  
     <property name="maxPoolSize" value="100" />  
     <property name="queueCapacity" value="500" />  
    </bean>  
<!--     
    ============= 业务对象=============                         将需要添加定时任务调度的类配置到这里 -->
     <bean id="articleBatchRun" class="top.wys.developerclub.batchprocess.ArticleBatchRun" />
     
     <!-- ============= 调度业务=============  -->
    <bean id="articleBatchRunService" class="org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean">
        <!-- 要调用的对象 -->
        <property name="targetObject" ref="articleBatchRun" />
        <!-- 要执行的方法名称 -->
        <property name="targetMethod" value="count" />
        <!-- 如果前一个任务还没有结束第二个任务不会启动 false -->
        <property name="concurrent" value="false" />
    </bean>
     
     
    <!-- ============= 调度触发器 ============= -->
    <bean id="articleBatchRunTrigger" class="org.springframework.scheduling.quartz.CronTriggerFactoryBean">
        <property name="jobDetail" ref="articleBatchRunService" />
                            <!-- 每隔3秒钟审批一次 -->
        <property name="cronExpression" value="0/3 * * * * ?" />
    </bean>
     
    <!-- ============= 调度工厂 =============  -->
    <bean id="schedulerBean" class="org.springframework.scheduling.quartz.SchedulerFactoryBean" lazy-init="false">
        <property name="triggers">
            <list>
                <!-- add by 郑明亮 2017年2月12日19:58:13 用于定时爬取文章 -->
              <ref bean="articleBatchRunTrigger"/> 
             
            </list>
        </property>
    </bean>
     
 
</beans>
