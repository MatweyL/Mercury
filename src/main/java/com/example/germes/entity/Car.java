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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
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
