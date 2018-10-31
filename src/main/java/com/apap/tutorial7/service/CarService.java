package com.apap.tutorial7.service;

import java.util.List;
import java.util.Optional;

import com.apap.tutorial7.model.CarModel;
import com.apap.tutorial7.model.DealerModel;

public interface CarService {
	CarModel addCar (CarModel car);
	void deleteCar (CarModel car);
	void deleteAllCar (List<CarModel> list);
	void updateCar (Long id, CarModel car);
	Optional<CarModel> getCarDetailById(Long id);
	List<CarModel> viewAllCar();
}
