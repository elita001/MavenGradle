package model.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "services")
public class ServiceEntity {
	@Id
	@Column(name = "serviceID", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int serviceID;

	@Column(name = "name", length = 50)
	private String name;

	@Column(name = "curPrice")
	private int curPrice;

	@OneToMany(mappedBy = "service", targetEntity = OrdersInfoEntity.class)
	private List<OrdersInfoEntity> ordersinfo;

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

	public int getCurPrice() {
		return curPrice;
	}

	public void setCurPrice(int curPrice) {
		this.curPrice = curPrice;
	}

	public List<OrdersInfoEntity> getOrdersinfo() {
		return ordersinfo;
	}

	public void setOrdersinfo(List<OrdersInfoEntity> ordersinfo) {
		this.ordersinfo = ordersinfo;
	}
}
