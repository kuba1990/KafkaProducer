package com.example.Kafka_Project;

import com.example.Kafka_Project.FileReader.FileReader;
import com.example.Kafka_Project.FileReader.WatcherDirectory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class KafkaProjectApplication {

    public static void main(String[] args) throws InterruptedException, IOException {

        ConfigurableApplicationContext context =
                SpringApplication.run(KafkaProjectApplication.class, args);
        context.getBean(WatcherDirectory.class).watch();
    }
}
