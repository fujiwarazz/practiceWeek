package com.peels;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Author peelsannaw
 * @create 26/06/2023 10:07
 */
@SpringBootApplication
@Slf4j
@EnableAsync
@EnableCaching
@EnableScheduling
public class WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class,args);
        System.out.println(" ________  ___  ___  ________  ________  _______   ________   ________      \n" +
                "|\\   ____\\|\\  \\|\\  \\|\\   ____\\|\\   ____\\|\\  ___ \\ |\\   ____\\ |\\   ____\\     \n" +
                "\\ \\  \\___|\\ \\  \\\\\\  \\ \\  \\___|\\ \\  \\___|\\ \\   __/|\\ \\  \\___|_\\ \\  \\___|_    \n" +
                " \\ \\_____  \\ \\  \\\\\\  \\ \\  \\    \\ \\  \\    \\ \\  \\_|/_\\ \\_____  \\\\ \\_____  \\   \n" +
                "  \\|____|\\  \\ \\  \\\\\\  \\ \\  \\____\\ \\  \\____\\ \\  \\_|\\ \\|____|\\  \\\\|____|\\  \\  \n" +
                "    ____\\_\\  \\ \\_______\\ \\_______\\ \\_______\\ \\_______\\____\\_\\  \\ ____\\_\\  \\ \n" +
                "   |\\_________\\|_______|\\|_______|\\|_______|\\|_______|\\_________\\\\_________\\\n" +
                "   \\|_________|                                      \\|_________\\|_________|\n");
    }
}
