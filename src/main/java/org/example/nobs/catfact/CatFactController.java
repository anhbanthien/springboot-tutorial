package org.example.nobs.catfact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/catfact")
public class CatFactController {
    @Autowired
    private CatFactQueryHandler catFactQueryHandler;
    @GetMapping
    public ResponseEntity<CatfactDTO> getCatfact (){
        return catFactQueryHandler.execute(null);
    }
}
