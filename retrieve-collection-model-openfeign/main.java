
package au.edu.cqu.wk5tutebookcollectionopenfeign;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.util.List;
import lombok.Data;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.hateoas.CollectionModel;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author wiekiang
 */

@SpringBootApplication @EnableFeignClients
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}

@Entity @Data 
class Book {
    @Id @GeneratedValue 
    private Long Id;
    
    private String name;
}

@RepositoryRestResource 
interface BookRepository extends JpaRepository<Book,Long> {
    List<Book> findByName(String name);
}

@Component @Data
class _AppInit implements ApplicationRunner {
    private final BookRepository bookRepository;
    private final BookClient bookClient;
    
    public void run(ApplicationArguments aa) {
        Book b1=new Book();
        b1.setName("Frankenstein");
        bookRepository.save(b1);
                
        Book b2=new Book();
        b2.setName("Frankenstein");
        bookRepository.save(b2);

        System.out.println(bookClient.findByName("Frankenstein").getContent());
    }
}

@FeignClient(value="bookClient",url="http://localhost:8080")
interface BookClient {
    @GetMapping("/books/search/findByName")
    CollectionModel<Book> findByName(@RequestParam String name);
}
