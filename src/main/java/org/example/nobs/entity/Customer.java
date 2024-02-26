package org.example.nobs.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name="customer")
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    // relationship one to one
    //@OneToOne(cascade = CascadeType.ALL)
    //relationship one to many
    //@JoinColumn(name = "address_id")
    //relationship many to many
    @ManyToMany
    @JoinTable(
            name = "customer_address",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "address_id")
    )
    private List<Address> addresses;
}
