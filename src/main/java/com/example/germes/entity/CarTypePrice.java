package com.example.germes.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "car_type_prices")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class CarTypePrice {

    @Id
    @SequenceGenerator(
            name = "car_type_price_sequence",
            sequenceName = "car_type_price_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "car_type_price_sequence"
    )
    private Long id;

    private String carType;

    private double costPerKm;

}
