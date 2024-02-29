package org.example.nobs.catfact;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "cat_fact")
@Data
public class CatfactEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String catFactJson;

    public CatfactEntity(Catfact catfact) {
        this.catFactJson = convertToJson(catfact);
    }

    public CatfactEntity() {

    }
    //Serialization
    private String convertToJson(Catfact catfact) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(catfact);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);

        }
    }
    //Deserialization
    public Catfact convertToCatFact () throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(catFactJson,Catfact.class);
    }
}