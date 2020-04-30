package diy.controller;

import diy.annotaion.Controller;
import diy.annotaion.RequestMapping;

@Controller
@RequestMapping("/index")
public class IndexController {

    @RequestMapping("/love")
    public String love(){
        return "love";
    }

    @RequestMapping("/good")
    public String good(){
        return "good";
    }
}
