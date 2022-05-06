package com.example.germes.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "driver")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    private String name;

    private String surname;

    private Boolean isBusy;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    private Car car;
}
