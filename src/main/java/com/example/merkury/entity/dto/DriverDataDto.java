package com.example.merkury.entity.dto;

import com.example.merkury.entity.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class DriverDataDto {

    private User user;

    private Driver driver;

    private Boolean is_verified;

    private Boolean is_active;

    public static DriverDataDto fromDriverData(DriverData driverData) {
        DriverDataDto driverDataDto = new DriverDataDto();
        if (driverData != null) {
            driverDataDto.setUser(driverData.getUser());
            driverDataDto.setDriver(driverData.getDriver());
            driverDataDto.setIs_active(driverData.getIs_active());
            driverDataDto.setIs_verified(driverData.getIs_verified());
        } else {
            driverDataDto.setDriver(new Driver());
            driverDataDto.getDriver().setCar(new Car());
        }
        return driverDataDto;
    }
}
