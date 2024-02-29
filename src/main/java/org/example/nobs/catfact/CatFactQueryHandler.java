package org.example.nobs.catfact;

import org.example.nobs.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class CatFactQueryHandler implements Query<Void,CatfactDTO> {
    private final RestTemplate restTemplate;
    public CatFactQueryHandler (RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Autowired
    private  CatRepository catRepository;




    @Override
    public ResponseEntity<CatfactDTO> execute(Void input) {
        try {
            Catfact catfact =  restTemplate.getForObject("https://catfact.ninja/fact",Catfact.class);
            //save to database a copy of json
            catRepository.save(new CatfactEntity(catfact));

            CatfactDTO catfactDTO = new CatfactDTO(catfact.getFact());
            return ResponseEntity.ok(catfactDTO);
        }catch (Exception e){
             e.printStackTrace();
        }
        return null;
    }
}
