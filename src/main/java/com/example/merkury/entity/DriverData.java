package com.example.merkury.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "driver_data")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class DriverData {

    @Id
    @SequenceGenerator(
            name = "driver_data_sequence",
            sequenceName =  "driver_data_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator =  "driver_data_sequence"
    )
    private Long id;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "driver_id", referencedColumnName = "id")
    private Driver driver;

    private Boolean is_verified;

    private Boolean is_active;

    public Boolean isAbleToGetOrders() {
        return is_active && is_verified;
    }

}
