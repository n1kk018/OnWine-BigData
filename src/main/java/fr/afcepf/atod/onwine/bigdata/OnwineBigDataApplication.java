package fr.afcepf.atod.onwine.bigdata;

import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class OnwineBigDataApplication {

	public static void main(String[] args) {
	    ConfigurableApplicationContext context = SpringApplication.run(OnwineBigDataApplication.class, args);
	    try {
            //context.getBean(TestData.class).run();
        } catch (BeansException paramE) {
            paramE.printStackTrace();
        } catch (Exception paramE) {
            paramE.printStackTrace();
        }
	}
}
