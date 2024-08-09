package au.edu.cqu.wk5tutebookcollectionthymeleaf;

import io.micrometer.common.lang.NonNull;
import java.util.List;
import lombok.Data;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


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

@Data
class Book {
    private final String isbn;
    private final String name;
}

@Controller
class BookController {
    @GetMapping(value = "/")
    public String _home(Model model) {
        Book frank = new Book("42", "Frankenstein");
        Book pride = new Book("441", "Pride and Prejudice");
        List<Book> books = List.of(frank,pride);
        model.addAttribute("books",books);
        return "index.html";
    }
 
    @PostMapping(value="/uibooks/{isbn}")
    @ResponseBody
    public String _updateBook(@PathVariable String isbn,@ModelAttribute Book book) {
        return "Updated "+isbn+" with "+book.getName()+" <a href='/'>Home</a>";
    }
 
    @GetMapping(value="/uibooks")//?isbn={isbn}
    @ResponseBody
    public String _deleteBook(@RequestParam String isbn) {
        return "Deleted "+isbn+". <a href='/'>Home</a>";
    }
}
