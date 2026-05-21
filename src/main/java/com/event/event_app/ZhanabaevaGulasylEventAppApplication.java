package com.event.event_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ZhanabaevaGulasylEventAppApplication {
	public static void main(String[] args) {
		SpringApplication.run(ZhanabaevaGulasylEventAppApplication.class, args);
	}
}