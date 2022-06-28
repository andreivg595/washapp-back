package com.washapp.back.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor 
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "employee")
public class Employee extends AbstractPerson {

	@Column(name = "nif")
	private String nif;
	
	@Column(name = "ss")
	private String ss;

	
}
