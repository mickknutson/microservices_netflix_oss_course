package com.baselogic.domain;

import java.io.Serializable;

public class Order implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private long id;
	private String product;
	private String customerName;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}

	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	@Override
	public String toString() {
		return "Order #:" + id + ", Product:" + product + ", Customer:"
				+ customerName;
	}
}
