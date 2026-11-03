package com.example.online.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.online.model.FoodOrder;
import com.example.online.service.FoodOrderService;

@RestController
public class FoodOrderController {
	
	@Autowired
	private FoodOrderService service;
	
	@GetMapping("/disOrders")
	public List<FoodOrder> showOrders() {
		return service.showOrders();
	}
	
	@GetMapping("/searchid/{id}")
	 public FoodOrder serachById(@PathVariable int id) {
		 return service.searchById(id);
	 }
	@GetMapping("/searchItem/{foodItem}")
	public FoodOrder serachByItem(@PathVariable String foodItem) {
		return service.searchByItem(foodItem);
	}
	@PostMapping("/insert")
	public FoodOrder addOrder(@RequestBody FoodOrder order) {
		return service.addOrder(order);
	}
	
	@PutMapping("/update/{id}")
	public FoodOrder updateOrder(
	        @PathVariable int id,
	        @RequestBody FoodOrder order) {

	    return service.updateOrder(id, order);
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteOrder(@PathVariable int id) {

	    return service.deleteOrder(id);
	}
	@PatchMapping("/patch/{id}")
	public FoodOrder patchOrder(@PathVariable int id,@RequestBody FoodOrder order) {
		return service.patchOrder(id, order);
	}


}
