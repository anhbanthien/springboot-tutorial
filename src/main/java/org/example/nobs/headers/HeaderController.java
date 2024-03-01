package org.example.nobs.headers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeaderController {
    @GetMapping("/header")
    public String getRegionalRequest(@RequestHeader(defaultValue = "usa") String region){
        if (region.equals("usa")) return "usa";
        if (region.equals("can")) return "can";
        return "out side support";

    }
}
