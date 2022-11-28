package com.example.merkury.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "driver_orders")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class DriverOrder {

    @Id
    @SequenceGenerator(
            name = "driver_order_sequence",
            sequenceName = "driver_order_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "driver_order_sequence"
    )
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_order_id", referencedColumnName = "id")
    private UserOrder userOrder;

    @ManyToOne(fetch = FetchType.EAGER)
    private Driver driver;

}
