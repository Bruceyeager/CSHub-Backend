package com.smu.model;

import lombok.*;

import javax.persistence.*;
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "organization")
public class Organization {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;

	@Column(name="name")
	private String name;

	@Column(name="address")
	private String address;

	@Column(name="city")
	private String city;

	@Column(name="state")
	private String state;

	@Column(name="zip_code")
	private Integer zipCode;

	@Column(name="short_description")
	private String shortDescription;

	@Column(name="long_description")
	private String longDescription;

	@Column(name="fields")
	private String fields;

	@Column(name="URL")
	private String url;
}
