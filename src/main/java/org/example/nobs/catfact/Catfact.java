package org.example.nobs.catfact;

import lombok.Data;
import org.springframework.context.annotation.Bean;


@Data
public class Catfact {
    private String fact;
    private int length;
}
