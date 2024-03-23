package com.example.custommetricsdemo.infrastructure;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "ORDER")
@Table(name = "ORDERS")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "item_name", nullable = false, unique = true)
    private String itemName;

    public Order(String itemName) {
        this.itemName = itemName;
    }
}
