package com.exampleaa.SimpleCRUD.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    private String type;

    @Column(nullable = false)
    private int level;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private double height;

    @Column(nullable = false)
    private double weight;

    private boolean isLegendary;


}
