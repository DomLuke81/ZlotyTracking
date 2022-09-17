package pl.domluke.zlotytracking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class ZlotyTrackingApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZlotyTrackingApplication.class, args);
    }

}
