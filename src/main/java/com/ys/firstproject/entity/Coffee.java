package com.ys.firstproject.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Coffee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private Integer price;

    public void patch(Coffee coffee) {
        if(coffee.getName() != null){
            this.name = coffee.getName();
        }
        if(coffee.getPrice() != null){
            this.price = coffee.getPrice();
        }
    }

}
