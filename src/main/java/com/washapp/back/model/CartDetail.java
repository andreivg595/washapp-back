package com.washapp.back.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cartdetail")
public class CartDetail implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
    @JoinColumn(name = "FK_CART", referencedColumnName = "id", nullable = false)
	private Cart cart;
	
	@ManyToOne
	@JoinColumn(name = "FK_PRODUCT", referencedColumnName = "id", nullable = false)
	private Product product;
	
	@Column(name = "quantity")
	private long quantity;
}
