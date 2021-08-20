package com.example.firstspringproject;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.*;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

@Configuration
//@EnableScheduling
//@EnableAsync
public class MySchedular implements SchedulingConfigurer{
    @Async()
    @Scheduled(fixedRate = 1000, initialDelay = 3000)
    public void scheduleFixedDelayTask() throws InterruptedException {
        System.out.println("one proccess waiting");
        Thread.sleep(2000);
        System.out.println(
                "Fixed delay task - " + System.currentTimeMillis() / 1000);
    }

    @Scheduled(cron = "*/10 * * * * *")
    public void scheduleWithCron() {
        System.out.println("every ten second" + System.currentTimeMillis() / 1000);
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.addTriggerTask(() -> System.out.println("dynamic scheduler ..."+ Calendar.getInstance().getTime()),
            triggerContext -> {
                Calendar nextExecutionTime = new GregorianCalendar();
                Date lastActualExecutionTime = triggerContext.lastActualExecutionTime();
                nextExecutionTime.setTime(lastActualExecutionTime != null ? lastActualExecutionTime : new Date());
                nextExecutionTime.add(Calendar.MILLISECOND, getNewExecutionTime());
                return nextExecutionTime.getTime();
            });
    }

    private int getNewExecutionTime() {
        //Load Your execution time from database or property file
        Random random = new Random();
        return random.nextInt(6000) + 1000;
    }
}