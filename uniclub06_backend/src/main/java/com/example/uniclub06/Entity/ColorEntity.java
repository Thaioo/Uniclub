package com.example.uniclub06.Entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity(name = "color")
public class ColorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="name")
    private String name;
    @OneToMany(mappedBy = "color")
    private List<VariantEntity> variants;
}
