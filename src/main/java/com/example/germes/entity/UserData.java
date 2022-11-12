package com.example.germes.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_data")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class UserData {

    @Id
    @SequenceGenerator(
            name = "user_data_sequence",
            sequenceName =  "user_data_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator =  "user_data_sequence"
    )
    private Long id;

    private String name;

    private String surname;

    private String phoneNumber;

    private String additionalInfo;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

}
