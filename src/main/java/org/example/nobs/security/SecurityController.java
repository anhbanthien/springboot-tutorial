package org.example.nobs.security;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {
//    @GetMapping("/open")
//    public String open (){
//        return "No login Required";
//    }
//    @GetMapping("/closed")
//    public String closed (){
//        return "Login IS Required";
//    }

    @PreAuthorize("hasRole('SPECIAL')")
    @GetMapping("/special")
    public String special (){
        return "SPECIAL";
    }
    @PreAuthorize("hasRole('SPECIAL') or hasRole('BASIC')")
    @GetMapping("/basic")
    public String basic (){
        return "BASIC";
    }


}
