package com.ys.firstproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Member {

    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String email;
    @Column
    private String password;

}
