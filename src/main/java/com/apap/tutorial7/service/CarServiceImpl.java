package com.apap.tutorial7.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.tutorial7.model.CarModel;
import com.apap.tutorial7.model.DealerModel;
import com.apap.tutorial7.repository.CarDb;

@Service
@Transactional
public class CarServiceImpl implements CarService{
	@Autowired
	private CarDb carDb;

	@Override
	public CarModel addCar(CarModel car) {
		carDb.save(car);
		return car;
		
	}

	@Override
	public void deleteCar(CarModel car) {
		carDb.delete(car);
	}
	
	@Override
	public Optional<CarModel> getCarDetailById(Long id) {
		return carDb.findById(id);
	}

	@Override
	public void deleteAllCar(List<CarModel> list) {
		carDb.deleteAll(list);
	}

	@Override
	public void updateCar(Long id,CarModel car) {
		CarModel carAsli = carDb.getOne(id);
		carAsli.setAmount(car.getAmount());
		carAsli.setBrand(car.getBrand());
		carAsli.setPrice(car.getPrice());
		carAsli.setType(car.getType());
		carAsli.setDealer(car.getDealer());
		carDb.save(car);
		
	}

	@Override
	public List<CarModel> viewAllCar() {
		return carDb.findAll();
	}

	
}
