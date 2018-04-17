package com.example.Kafka_Project;

import com.example.Kafka_Project.FileReader.FileReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class KafkaProjectApplication {

    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext context =
                SpringApplication.run(KafkaProjectApplication.class, args);
        context.getBean(FileReader.class).readContent("/home/jwisniowski/Desktop/Notify");
    }
}
