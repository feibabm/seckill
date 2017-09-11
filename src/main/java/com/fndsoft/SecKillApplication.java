package com.fndsoft;

import com.fndsoft.pool.BackendUpdateNumberTask;
import com.fndsoft.pool.ExecutorPool;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;

import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * Created by feibabm on 2017/9/6 0006.
 */
@SpringBootApplication
@ComponentScan("com.fndsoft")
public class SecKillApplication {

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext run = SpringApplication.run(SecKillApplication.class, args);
        BackendUpdateNumberTask bean = run.getBean(BackendUpdateNumberTask.class);
        ExecutorPool.submit(bean);
    }
}
