package com.example.germes.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_orders")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class UserOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    private String description;

    private String carType;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cargo_id", referencedColumnName = "id")
    private Cargo cargo;

    private String startAddress;

    private String endAddress;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfDispatch;

    private Double distance;

    private Double duration;

    private Double deliveryCost;

    private Boolean isClosed;

    @OneToOne(mappedBy = "userOrder")
    @JoinColumn(name = "driver_order_id", referencedColumnName = "id")
    private DriverOrder driverOrder;

}
