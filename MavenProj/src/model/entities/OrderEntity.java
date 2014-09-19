package model.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import model.entities.fields.Status;

@Entity(name = "orders")
public class OrderEntity {
	@Id
	@Column(name = "orderID", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int orderID;

	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private Status status;

	@OneToMany(mappedBy = "order", targetEntity = OrdersInfoEntity.class)
	private List<OrdersInfoEntity> ordersinfo;

	@ManyToOne
	@JoinColumn(name = "carID", referencedColumnName = "carID")
	private CarEntity car;

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<OrdersInfoEntity> getOrdersinfo() {
		return ordersinfo;
	}

	public void setOrdersinfo(List<OrdersInfoEntity> ordersinfo) {
		this.ordersinfo = ordersinfo;
	}

	public CarEntity getCar() {
		return car;
	}

	public void setCar(CarEntity car) {
		this.car = car;
	}
}
