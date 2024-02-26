package org.example.nobs.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "street")
    private String street;
    @Column(name = "city")
    private String city;
    @Column(name = "state")
    private String state;

    @ManyToMany(mappedBy = "addresses")
    @JsonIgnore
    private List<Customer> customer;
    //stack over flow ?
    //how to solve: DTO or JSON IGNORE
    //private Long customerId;
}
