package com.zxf.quartz;

import java.util.Date;

public class QuartzJob {
    public void work() {
        System.out.println("测试Quartz的定时任务调度！！！时间："+new Date());
    }
}
