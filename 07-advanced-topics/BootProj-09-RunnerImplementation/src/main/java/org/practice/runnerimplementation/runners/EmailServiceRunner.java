package org.practice.runnerimplementation.runners;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Component
public class EmailServiceRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("EmailServiceRunner.run");
        System.out.println("NonOption args "+ args.getNonOptionArgs());
        System.out.println("Option Args Val "+ args.getOptionNames());
        System.out.println("Sources args "+ Arrays.toString(args.getSourceArgs()));

    }
}
