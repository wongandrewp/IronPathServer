package wong.andrew.webandprocessbuilder;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import wong.andrew.webandprocessbuilder.service.StorageService;

import java.awt.*;
import java.io.File;
import java.io.IOException;

@SpringBootApplication
public class WebAndProcessBuilderApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebAndProcessBuilderApplication.class, args);

	}
	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			storageService.deleteAll();
			storageService.init();
		};
	}


	public static void playMp4(File mp4File){
		try{
			Desktop.getDesktop().open(mp4File);
		} catch(IOException e){
			e.printStackTrace();
		}
	}
}
