package com.example.online.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodOrder {
	
	private Integer orderId;

	@NotBlank(message="customer name is required")
	private String customerName;
	@NotBlank(message="food item cannnot be empty")
	private String foodItem;
	@NotNull(message="must give quantity")
	@Min(value=1,message="Quantity must be atleast  1")
	private Integer quantity;
	@NotNull(message="Price is mandiatory")
	private Double price;

	
	
	

}
