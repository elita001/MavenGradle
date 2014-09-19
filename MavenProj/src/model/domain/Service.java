package model.domain;

import java.util.List;

public class Service {
	private int currentPrice;
	private List<OrdersInfo> ordersinfo;
	private int serviceID;
	private String name;

	public int getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(int currentPrice) {
		this.currentPrice = currentPrice;
	}

	public List<OrdersInfo> getOrdersinfo() {
		return ordersinfo;
	}

	public void setOrdersinfo(List<OrdersInfo> ordersinfo) {
		this.ordersinfo = ordersinfo;
	}

	public int getServiceID() {
		return serviceID;
	}

	public void setServiceID(int serviceID) {
		this.serviceID = serviceID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
