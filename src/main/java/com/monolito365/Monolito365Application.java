package com.monolito365;



import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobListener;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.quartz.SchedulerFactoryBeanCustomizer;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.monolito365.scheduler.ProyectoJob;
import com.monolito365.scheduler.QuartzJobListener;

@SpringBootApplication
@EnableScheduling
public class Monolito365Application extends SpringBootServletInitializer {
	
	public static void main(String[] args) {
		SpringApplication.run(Monolito365Application.class, args);
	}
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(Monolito365Application.class);
    }
	
	
	  
	  
	  
  @Bean(name = "ProyectoJob")
  public JobDetail proyectoJob() {
    return JobBuilder.newJob(ProyectoJob.class)
                     .withIdentity("ProyectoJob", "ms365")
                     .withDescription("Proyecto Job")
                     .storeDurably()
                     .build();
  }

  @Bean
  public Trigger proyectoTrigger(@Qualifier("ProyectoJob") JobDetail job) {
    return TriggerBuilder.newTrigger().forJob(job)
                         .withIdentity("ProyectoTrigger", "ms365")
                         .withDescription("Proyecto Trigger")
                         //.withSchedule(CronScheduleBuilder.cronSchedule("0 0/2 * 1/1 * ? *")) // 2 Minutos
                         .withSchedule(CronScheduleBuilder.cronSchedule("0 0 4 1/1 * ? *")) // Todos los dias a las 4 am
                         .startNow()
                         .build();
  }

  @Bean(name = "QuartzJobListener")
  public JobListener quartzListener() {
    return new QuartzJobListener();
  }

  @Bean
  public SchedulerFactoryBeanCustomizer schedulerConfiguration(@Qualifier("QuartzJobListener") JobListener listener) {
    return bean -> {
      bean.setGlobalJobListeners(listener);
    };
  }

//	public static void main(String[] args) {
//		SpringApplication.run(Monolito365Application.class, args);
//	}
//	@Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        return builder.sources(Monolito365Application.class);
//    }
}
