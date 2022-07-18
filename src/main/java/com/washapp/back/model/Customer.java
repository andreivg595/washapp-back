package com.washapp.back.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor 
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "customer")
public class Customer extends AbstractPerson {

	private static final long serialVersionUID = 1L;

	@Column(name = "phone")
	private String phone;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "cp")
	private String cp;
	
	@JsonIgnore
	@OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
	private Cart cart;
}
