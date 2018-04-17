package com.example.Kafka_Project.FileReader;

import com.example.Kafka_Project.Sender.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.management.MXBean;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

import static java.lang.System.out;

@Component
public class FileReader {

    @Autowired
    private Sender sender;

    public void readContent(String pathFile) throws IOException {
            Files.readAllLines(Paths.get("/home/jwisniowski/Desktop/Notify/Kuba"))
                    .stream()
                    .map(line -> line.split("\\s+"))
                    .flatMap(Arrays::stream)
                    .forEach(word->sender.send("helloworld.t", word));
    }
}

