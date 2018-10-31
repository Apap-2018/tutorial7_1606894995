package com.apap.tutorial7.controller;


import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.apap.tutorial7.model.CarModel;
import com.apap.tutorial7.model.DealerModel;
import com.apap.tutorial7.rest.Setting;
import com.apap.tutorial7.service.CarService;
import com.apap.tutorial7.service.DealerService;

@RestController
@RequestMapping("/car")
public class CarController {
	@Autowired
	private CarService carService;
	
	@Autowired
	private DealerService dealerService;
	
	
	
	@PostMapping(value = "/add")
	private CarModel addCarSubmit(@RequestBody CarModel car) {
		
		return carService.addCar(car);
	}
	
	@GetMapping(value = "/{carId}")
	private CarModel viewCar(@PathVariable("carId") long carId, Model model) {
		CarModel mobilnya = carService.getCarDetailById(carId).get();
		mobilnya.setDealer(null);
		return mobilnya;
	}
	
	@DeleteMapping(value = "/delete")
	private String deleteCar (@RequestParam("carId") long id, Model model ) {
		CarModel car = carService.getCarDetailById(id).get();
		carService.deleteCar(car);
		return "Car has been deleted";
	}
	
	@PutMapping(value ="/{id}")
	private String updateCarSubmit (
			@PathVariable (value = "id") long id,
			@RequestParam(value = "brand", required=false) String brand,
			@RequestParam(value = "type", required=false) String type,
			@RequestParam(value = "price", required=false) Long price,
			@RequestParam(value = "amount", required=false) Integer amount,
			@RequestParam(value = "idDealer", required=false) Long idDealer
			) {
		System.out.println("masuk sini dengan id->"+id);
		CarModel car = (CarModel) carService.getCarDetailById(id).get();
		System.out.println("print type obj car->"+car.getType());
		if (car.equals(null)) {
			return "Couldn't find your car";
		}
		
		if(brand != null) {
			car.setBrand(brand);
		}
		if (type!= null) {
			car.setType(type);
		}
		if (price!=null) {
			car.setPrice(price);
		}
		if(amount!=null) {
			car.setAmount(amount);
		}
		if (idDealer!=null) {
			DealerModel dealer = dealerService.getDealerDetailById(idDealer).get();
			car.setDealer(dealer);
		}
		carService.updateCar(id, car);
		return "update success";
		
	}
	
	@GetMapping()
	private List<CarModel> viewAllCar(Model model) {
		List<CarModel> listCar = carService.viewAllCar();
		for (CarModel car : listCar) {
			car.setDealer(null);
		}
		return listCar;
	}
}
