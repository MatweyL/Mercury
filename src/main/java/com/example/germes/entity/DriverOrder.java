package com.example.germes.entity;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_order_id", referencedColumnName = "id")
    private UserOrder userOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    private Driver driver;

}
