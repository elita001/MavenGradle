package model.domain;

import java.util.List;

import model.domain.fields.Stage;

public class Order {
	private int orderID;
	private Stage stage;
	private List<OrdersInfo> ordersinfo;
	private Car car;

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public List<OrdersInfo> getOrdersinfo() {
		return ordersinfo;
	}

	public void setOrdersinfo(List<OrdersInfo> ordersinfo) {
		this.ordersinfo = ordersinfo;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}
}
