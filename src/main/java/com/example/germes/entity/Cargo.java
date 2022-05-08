package com.example.germes.entity;

import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "cargos")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Cargo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    private String cargoType;

    private Double weight;

    private Double length;

    private Double height;

    private Double width;

    @OneToOne(mappedBy = "cargo")
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private UserOrder order;

}
