package com.example.Kafka_Project.FileReader;

import com.example.Kafka_Project.Sender.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Component
public class FileReader {

    private static List<String> fileName = new ArrayList<>();

    @Autowired
    private Sender sender;

    private PathConfiguration pathConfiguration;

    public FileReader(PathConfiguration pathConfiguration) {
        this.pathConfiguration = pathConfiguration;
    }

    public void readContent() throws IOException {

        long count = getNumberFileInDirectory();
        for (int a = 0; a < count; a++) {
            Files.readAllLines(Paths.get(getFileName().get(a)))
                    .stream()
                    .map(line -> line.split("\\s+"))
                    .flatMap(Arrays::stream)
                    .forEach(word -> sender.send("helloworld.t", word));
        }
    }


    public void readContentNewFile(String pathFile) throws IOException {

        Files.readAllLines(Paths.get(pathFile))
                .stream()
                .map(line -> line.split("\\s+"))
                .flatMap(Arrays::stream)
                .forEach(word->sender.send("helloworld.t", word));
    }


    private long getNumberFileInDirectory() throws IOException {
        return Files.list(Paths.get(getDirectory()))
                .filter(p -> p.toFile().isFile())
                .count();
    }

    public List<String> getFileName() throws IOException {
        File f = new File(getDirectory());

        File[] files = f.listFiles();
        String pathName = null;
        for (File file : files) {
            fileName.add(file.getCanonicalPath().toString());
        }
        return fileName;
    }

    private String getDirectory() {
        String myPath = pathConfiguration.getPaths().get(0);
        return myPath;
    }

}