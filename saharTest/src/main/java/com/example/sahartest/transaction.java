package com.example.sahartest;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class transaction {

    @RequestMapping("/")
    public String hi(){
        return "hi guays";
    }

    @GetMapping("/{id}")
    public String helloID(@PathVariable int id){
        return String.format("hello %d ",id);
    }

}
