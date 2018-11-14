package thalia.atec.thaliaPrototipo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import thalia.atec.thaliaPrototipo.UploadFile.FileStorageProperties;

@EnableAutoConfiguration
@EnableConfigurationProperties({
	FileStorageProperties.class
})

@SpringBootApplication
public class ThaliaPrototipoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThaliaPrototipoApplication.class, args);
		
		
		
		
	}
}
