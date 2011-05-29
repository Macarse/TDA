package com.tda.model.utils;

import java.util.Date;

import javax.validation.constraints.Digits;

import org.springframework.format.annotation.DateTimeFormat;

public class ConfigReport {

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dateFrom;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dateTo;

	private ExportFormat format;

	@Digits(fraction = 0, integer = 2)
	private Integer ageFrom;

	@Digits(fraction = 0, integer = 2)
	private Integer ageTo;

	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public ExportFormat getFormat() {
		return format;
	}

	public void setFormat(ExportFormat format) {
		this.format = format;
	}

	public Integer getAgeFrom() {
		return ageFrom;
	}

	public void setAgeFrom(Integer ageFrom) {
		this.ageFrom = ageFrom;
	}

	public Integer getAgeTo() {
		return ageTo;
	}

	public void setAgeTo(Integer ageTo) {
		this.ageTo = ageTo;
	}
}