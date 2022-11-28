package com.example.merkury.entity;

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
    @SequenceGenerator(
            name = "cargo_sequence",
            sequenceName = "cargo_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "cargo_sequence"
    )
    private Long id;

    private String cargoType;

    private Double weight;

    private Double length;

    private Double height;

    private Double width;

    @OneToOne(mappedBy = "cargo")
    @JoinColumn(name = "user_order_id", referencedColumnName = "id")
    private UserOrder userOrder;

}
