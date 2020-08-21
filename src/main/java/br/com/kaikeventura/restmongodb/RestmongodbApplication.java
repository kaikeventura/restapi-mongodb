package br.com.kaikeventura.restmongodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class RestmongodbApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestmongodbApplication.class, args);
	}

}
