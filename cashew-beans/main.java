package au.edu.cqu.wk5tutecashewbeans;

import lombok.Data;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


/**
 *
 * @author wiekiang
 */

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}

class Cracker {
    static private int nuts=0;
    void crk() { nuts++; if (nuts>100) { nuts=nuts/0; } }
    void clr() { nuts=0; }
}

@Configuration 
class _CrackerBean {
    @Bean Cracker _cracker() {
    return new Cracker();
    }
}

@Data @Service 
class CrackerService {
    private final Cracker cracker;
    private int nuts=0;
    
    void crack() {
        nuts++;
        if (nuts>99) { cracker.clr(); }
        cracker.crk();
    }
}

@Component @Data
class _AppInit implements ApplicationRunner {
    private final CrackerService crackerService;
    
    public void run(ApplicationArguments aa) {
        for (int i=0; i<1000; i++) { crackerService.crack(); }
    }
}
