package com.app.models;

import jakarta.persistence.*;
import lombok.*;

@Entity @Table(name = "products")
@Getter @Setter @Builder @AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private double price;
    private String description;
}
