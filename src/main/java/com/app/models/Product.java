package com.app.models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity @Table(name = "products")
@Getter @Setter @Builder @AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private double price;
    private String description;
}
