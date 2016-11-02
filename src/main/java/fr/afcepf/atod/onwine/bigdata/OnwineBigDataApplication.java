package fr.afcepf.atod.onwine.bigdata;

import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackageClasses = OnwineBigDataApplication.class)
public class OnwineBigDataApplication {

	public static void main(String[] args) {
	    SpringApplication.run(OnwineBigDataApplication.class, args);
	}
}
