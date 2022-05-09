package com.example.germes.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "cars")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Car {

    @Id
    @SequenceGenerator(
            name = "car_sequence",
            sequenceName = "car_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "car_sequence"
    )
    private Long id;

    private String brand;

    private String carNumber;

    private String carType;

    private Double pricePerKm;

    @OneToOne(mappedBy = "car")
    @JoinColumn(name = "driver_id", referencedColumnName = "id")
    private Driver driver;

    public String toStringInConsole() {
        boolean isDriverSet = driver != null;
        return id + "; " + brand + "; " + carNumber + "; " + carType + "; " + pricePerKm + "; driver: " + isDriverSet;
    }
}
