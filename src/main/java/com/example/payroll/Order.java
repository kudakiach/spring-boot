package com.example.payroll;

import jakarta.persistence.GeneratedValue;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="CUSTOMER_ORDER")
public class Order {
	
	private @Id @GeneratedValue Long id;
	
	private String description;
	private Status status;
	
	Order(){}

	public Order(String description, Status status) {
		super();
		this.description = description;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		status = status;
	}

	@Override
	public int hashCode() {
		return Objects.hash(status, description, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Order))
			return false;
		Order other = (Order) obj;
		return Objects.equals(status, other.status) && Objects.equals(description, other.description)
				&& Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", description=" + description + ", Status=" + status + "]";
	}
	
	
	
}
