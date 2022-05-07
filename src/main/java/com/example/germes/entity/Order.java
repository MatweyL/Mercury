package com.example.germes.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "order")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

}
