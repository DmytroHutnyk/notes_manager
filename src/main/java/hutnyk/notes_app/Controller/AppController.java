package hutnyk.notes_app.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
@RequestMapping("/")

public class AppController {

    @GetMapping("/")
    public String index(){
        return "index";
    }


    @GetMapping("/menu")
    public String menu(){
        return "app/menu";
    }


}
