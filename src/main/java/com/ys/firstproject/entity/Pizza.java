package com.ys.firstproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private int price;
}
// post : create(api/pizza)
// get : one(api/pizza/{id})
// get : list(api/pizzas)
// patch : update(api/pizza/{id})
// delete : delete(api/pizza/{id})