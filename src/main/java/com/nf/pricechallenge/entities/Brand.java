package com.nf.pricechallenge.entities;

import javax.persistence.*;

@Entity
@Table(name = "BRANDS")
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "BRAND", nullable = false)
    private String brand;
}
