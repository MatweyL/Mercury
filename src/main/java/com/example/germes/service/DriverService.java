package com.example.germes.service;

import com.example.germes.entity.Driver;
import com.example.germes.entity.DriverOrder;
import com.example.germes.repo.DriverOrderRepository;
import com.example.germes.repo.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DriverService {

    private final static Date DEFAULT_FREE_START_DATE = new Date(30, Calendar.JANUARY, 1);
    private final static Date DEFAULT_FREE_END_DATE = new Date(200, Calendar.JANUARY, 1);

    @Autowired
    private final DriverRepository driverRepository;

    @Autowired
    private final DriverOrderRepository driverOrderRepository;

    public DriverService(DriverRepository driverRepository, DriverOrderRepository driverOrderRepository) {
        this.driverRepository = driverRepository;
        this.driverOrderRepository = driverOrderRepository;
    }

    public Driver getById(Long driverId) {
        return driverRepository.getById(driverId);
    }

    public List<Driver> findAll() {
        return driverRepository.findAll();
    }

    public Driver save(Driver driver) {
        driver.getCar().setDriver(driver);
        return driverRepository.save(driver);
    }

    public void deleteById(Long driverId) {
        driverRepository.deleteById(driverId);
    }

    public void setDriverIsBusy(Driver driver, boolean isBusy) {
        driver.setIsBusy(isBusy);
    }

    public List<Driver> getAvailableDriversWithCarTypeOnDate(String carType, Date dateOfDispatch, double duration) {
        Date dateOfArrival = Date.from(dateOfDispatch.toInstant().plusSeconds(Math.round(duration * 60)));
        List<Driver> availableDrivers = new ArrayList<>();
        List<Driver> driversWithTargetCarType = driverRepository.findAllByCar_carType(carType);
        for (Driver driver: driversWithTargetCarType) {
            List<List<Date>> driverFreeIntervals =  getDriverFreeTimeIntervals(driver.getId());
            for (List<Date> freeInterval: driverFreeIntervals) {
                if (dateOfDispatch.after(freeInterval.get(0)) && dateOfArrival.before(freeInterval.get(1))) {
                    availableDrivers.add(driver);
                    //System.out.println(freeInterval.get(0) + " <= " + dateOfDispatch + "; " + dateOfArrival + " <= " + freeInterval.get(1));
                    break;
                }
            }
        }
        return availableDrivers;
    }

    private List<List<Date>> getDriverFreeTimeIntervals(Long driverId) {
        List<DriverOrder> driverOrders = driverOrderRepository.findAllByDriver_Id(driverId);
        driverOrders.sort(new Comparator<DriverOrder>() {
            @Override
            public int compare(DriverOrder o1, DriverOrder o2) {
                return o1.getUserOrder().getDateOfDispatch().compareTo(o2.getUserOrder().getDateOfDispatch());
            }
        });
        List<List<Date>> driverFreeTimeIntervals = new ArrayList<>();
        driverFreeTimeIntervals.add((new ArrayList<>(List.of(new Date[]{DEFAULT_FREE_START_DATE, DEFAULT_FREE_END_DATE}))));
        for (DriverOrder driverOrder: driverOrders) {
            Date endFreeIntervalDate = driverOrder.getUserOrder().getDateOfDispatch();
            Long durationSeconds = Math.round(driverOrder.getUserOrder().getDuration() * 60);
            Date startFreeIntervalDate = Date.from(driverOrder.getUserOrder().getDateOfDispatch().toInstant().plusSeconds(durationSeconds));
            driverFreeTimeIntervals.get(driverFreeTimeIntervals.size() - 1).set(1, endFreeIntervalDate);
            List<Date> freeInterval = (new ArrayList<>(List.of(new Date[]{startFreeIntervalDate, DEFAULT_FREE_END_DATE})));
            driverFreeTimeIntervals.add(freeInterval);
        }
        return  driverFreeTimeIntervals;
    }

}
