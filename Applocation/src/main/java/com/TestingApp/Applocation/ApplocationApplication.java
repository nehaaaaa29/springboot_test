package com.TestingApp.Applocation;

import com.TestingApp.Applocation.services.DataService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@RequiredArgsConstructor
@SpringBootApplication
public class ApplocationApplication implements CommandLineRunner {
//	private final DataService dataService;
	@Value("${my.variable}")
	private String myvariable;

	public static void main(String[] args) {
		SpringApplication.run(ApplocationApplication.class, args);
	}
	@Override
	public void run(String... args)throws Exception{
		System.out.println("my variable: "+myvariable);
	//	System.out.println("The data is: "+dataService.getData());

	}
	@Override
	public void run(String... args)throws Exception{
		System.out.println("my variable: "+myvariable);
		//	System.out.println("The data is: "+dataService.getData());

	}

}
