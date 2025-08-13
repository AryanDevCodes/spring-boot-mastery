package org.practice.runnerimplementation.runners;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AlertServiceRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("AlertServiceRunner.run  the command Line args are :");
        for (String arg : args) {
            System.out.println(arg);
        }

    }
}
