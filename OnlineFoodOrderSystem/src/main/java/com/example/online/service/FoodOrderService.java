package com.example.online.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.online.exception.OrderNotFoundException;
import com.example.online.model.FoodOrder;

@Service
public class FoodOrderService {
	
	 private  List<FoodOrder> orderlist=new ArrayList<>(
			 Arrays.asList(new FoodOrder(101,"pavan","biryani",1,200.0),
					 new FoodOrder(102,"Adithya","chapathi",2,100.0)));
	 
	 public List<FoodOrder> showOrders() {
		 
		 return orderlist;
	 }
	 
	 public FoodOrder searchById(int id) {
		 for(FoodOrder o:orderlist) {
			 if(o.getOrderId()==id) {
				 return o;
			 }
		 }
		  throw new OrderNotFoundException("given id has no order placed");
		 
	 }
	 
	 public FoodOrder searchByItem(String foodItem) {
		 for(FoodOrder o:orderlist) {
			 if(o.getFoodItem().equalsIgnoreCase(foodItem)) {
				 return o;
			 }
		 }
		  throw new OrderNotFoundException("FoodItem has not ordered");
		 
	 }
	 
	 private int nextid=103;
	 public FoodOrder addOrder(FoodOrder order) {
		 order.setOrderId(nextid++);
		 orderlist.add(order);
		 return order;
	 }
	 
	 public FoodOrder updateOrder(int id, FoodOrder order) {

		    FoodOrder oldOrder = searchById(id);

		    oldOrder.setCustomerName(order.getCustomerName());
		    oldOrder.setFoodItem(order.getFoodItem());
		    oldOrder.setQuantity(order.getQuantity());
		    oldOrder.setPrice(order.getPrice());

		    return oldOrder;
		}
	 
	 public String deleteOrder(int id) {

		    FoodOrder order = searchById(id);

		    orderlist.remove(order);

		    return "Order deleted successfully";
		}
	 
	 public FoodOrder patchOrder(int id, FoodOrder order) {

		     FoodOrder oldOrder=searchById(id);

		    if (order.getCustomerName() != null) {
		        oldOrder.setCustomerName(order.getCustomerName());
		    }

		    if (order.getFoodItem() != null) {
		        oldOrder.setFoodItem(order.getFoodItem());
		    }

		    if (order.getQuantity() != null) {
		        oldOrder.setQuantity(order.getQuantity());
		    }

		    if (order.getPrice() != null) {
		        oldOrder.setPrice(order.getPrice());
		    }

		    return oldOrder;
		} 

}
