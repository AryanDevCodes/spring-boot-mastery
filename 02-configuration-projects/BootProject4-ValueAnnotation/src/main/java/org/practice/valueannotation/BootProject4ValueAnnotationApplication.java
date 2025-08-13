package org.practice.valueannotation;

import org.practice.valueannotation.beans.BillGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class BootProject4ValueAnnotationApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(BootProject4ValueAnnotationApplication.class, args);
        BillGenerator billGenerator = context.getBean("billGenerator",BillGenerator.class);
        System.out.println(billGenerator);
    }

}
