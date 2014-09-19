package model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "ordersinfo")
public class OrdersInfoEntity {
	@Id
	@Column(name = "infoID", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int infoID;

	@Column(name = "price")
	private int price;

	@ManyToOne
	@JoinColumn(name = "orderID", referencedColumnName = "orderID", nullable = false, insertable = false, updatable = false)
	private OrderEntity order;

	@ManyToOne
	@JoinColumn(name = "serviceID", referencedColumnName = "serviceID", nullable = false, insertable = false, updatable = false)
	private ServiceEntity service;

	public int getInfoID() {
		return infoID;
	}

	public void setInfoID(int infoID) {
		this.infoID = infoID;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public OrderEntity getOrder() {
		return order;
	}

	public void setOrder(OrderEntity order) {
		this.order = order;
	}

	public ServiceEntity getService() {
		return service;
	}

	public void setService(ServiceEntity service) {
		this.service = service;
	}
}
