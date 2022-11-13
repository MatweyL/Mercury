package com.example.germes.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "drivers")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Driver {

    @Id
    @SequenceGenerator(
            name = "driver_sequence",
            sequenceName = "driver_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "driver_sequence"
    )
    private Long id;

    private String name;

    private String surname;

    private Boolean isBusy;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    private Car car;


    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL)
    private List<DriverOrder> driverOrders;

    public String toStringInConsole() {
        return id + "; " + name + "; " + surname + "; " + isBusy + "; " + car.toStringInConsole();
    }

}
