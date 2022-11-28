package com.example.merkury.service;

import com.example.merkury.entity.CarType;
import com.example.merkury.entity.CarTypePrice;
import com.example.merkury.repo.CarTypePriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarTypePriceService {

    @Autowired
    private final CarTypePriceRepository carTypePriceRepository;

    public CarTypePriceService(CarTypePriceRepository carTypePriceRepository) {
        this.carTypePriceRepository = carTypePriceRepository;
        initCarPricePerKmTableDate();
    }

    public List<CarTypePrice> findAllCarTypePrices() {
        return carTypePriceRepository.findAll();
    }

    public void saveAll(List<CarTypePrice> carTypePrices) {
        carTypePriceRepository.saveAll(carTypePrices);
    }

    private void initCarPricePerKmTableDate() {
        if (carTypePriceRepository.findAll().size() == 0) {
            for (CarType carType : CarType.values()) {
                CarTypePrice carTypePrice = new CarTypePrice();
                carTypePrice.setCarType(carType.getDisplayValue());
                carTypePrice.setCostPerKm(carType.getCostPerKm());
                carTypePriceRepository.save(carTypePrice);
            }
        }
    }

}
