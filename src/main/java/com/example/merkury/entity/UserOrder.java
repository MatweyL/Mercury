package com.example.merkury.entity;

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
    @SequenceGenerator(
            name = "user_order_sequence",
            sequenceName = "user_order_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_order_sequence"
    )
    private Long id;

    private String description;

    private String carType;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cargo_id", referencedColumnName = "id")
    private Cargo cargo;

    private String startAddress;

    private String endAddress;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dateOfDispatch;

    private Double distance;

    private Double duration;

    private Double deliveryCost;

    private Boolean isClosed;

    private String status;

    @OneToOne(mappedBy = "userOrder", fetch = FetchType.EAGER)
    @JoinColumn(name = "driver_order_id", referencedColumnName = "id")
    private DriverOrder driverOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public String toStringInConsole() {
        return "UserOrder{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", carType='" + carType + '\'' +
                ", cargo:" + (cargo != null) +
                ", startAddress='" + startAddress + '\'' +
                ", endAddress='" + endAddress + '\'' +
                ", dateOfDispatch=" + dateOfDispatch +
                ", distance=" + distance +
                ", duration=" + duration +
                ", deliveryCost=" + deliveryCost +
                ", isClosed=" + isClosed +
                ", driverOrder=" + (driverOrder != null) +
                '}';
    }
}
