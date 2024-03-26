package com.ys.firstproject.dto;

import com.ys.firstproject.entity.Coffee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
public class CoffeeForm {

    private Long id;
    private String name;
    private Integer price;;

    public Coffee toEntity() {
        return new Coffee(id, name, price);
    }

}
