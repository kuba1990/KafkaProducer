package com.example.Kafka_Project.FileReader;

import com.example.Kafka_Project.KafkaProjectApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

@Component
public class WatcherDirectory {

    @Autowired
    private FileReader fileReader;

    @Autowired
    PathConfiguration pathConfiguration;

    public void watch() throws IOException, InterruptedException {
        WatchService service = FileSystems.getDefault().newWatchService();
        Path path = Paths.get(getPath());
        path.register(service, StandardWatchEventKinds.ENTRY_CREATE);

        while (true) {
            WatchKey key = service.take();    // retrieve the watchkey
            for (WatchEvent event : key.pollEvents()) {
                Path fileName = (Path) event.context();
                System.out.println(event.kind().name());
                //System.out.println("/home/jwisniowski/Desktop/Notify/"+fileName);
                fileReader.readContentNewFile(getPath()+"/"+fileName);

            }
            key.reset();
        }
    }

    private String getPath(){
        return pathConfiguration.getPaths().get(0);

    }

}
