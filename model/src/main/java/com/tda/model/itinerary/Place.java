package com.tda.model.itinerary;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.Size;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "PLACE")
public class Place {
	private Long id;

	@NotNull
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date arrivalDate;

	@NotEmpty(message = "Debe ingresar la provincia")
	@Size(max = 200)
	private String province;

	@NotEmpty(message = "Debe ingresar la ciudad")
	@Size(max = 200)
	private String city;

	@NotEmpty(message = "Debe ingresar la localidad o barrio")
	@Size(max = 200)
	private String neighbourhood;

	@Size(max = 200)
	private String additionalInfo;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PLACE_ID")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getNeighbourhood() {
		return neighbourhood;
	}

	public void setNeighbourhood(String neighbourhood) {
		this.neighbourhood = neighbourhood;
	}

	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	@Override
	public String toString() {
		return "Place [id=" + id + ", arrivalDate=" + arrivalDate
				+ ", province=" + province + ", city=" + city
				+ ", neighbourhood=" + neighbourhood + ", additionalInfo="
				+ additionalInfo + "]";
	}

}
