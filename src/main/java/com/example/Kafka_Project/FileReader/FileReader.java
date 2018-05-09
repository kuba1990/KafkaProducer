package com.example.Kafka_Project.FileReader;

import com.example.Kafka_Project.Sender.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;



@Component
public class FileReader {

    @Autowired
    private Sender sender;

    public void readContentNewFile(String pathFile) throws IOException {

        Files.readAllLines(Paths.get(pathFile))
                .stream()
                .map(line -> line.split("\\s+"))
                .flatMap(Arrays::stream)
                .forEach(word->sender.send("helloworld.t", word));
    }

}