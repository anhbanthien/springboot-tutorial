package org.example.nobs.security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {
    @GetMapping("/open")
    public String open (){
        return "No login Required";
    }
    @GetMapping("/closed")
    public String closed (){
        return "Login IS Required";
    }

    @GetMapping("/special")
    public String special (){
        return "SPECIAL";
    }
    @GetMapping("/basic")
    public String basic (){
        return "BASIC";
    }


}
