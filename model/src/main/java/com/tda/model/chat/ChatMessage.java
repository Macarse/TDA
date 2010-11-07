package com.tda.model.chat;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class ChatMessage implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String fromWhom;
	private String toWhom;
	private String message;
	
	@Past
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date sent;

	private Integer recd;

	public ChatMessage() {
	}
	
	public ChatMessage(String fromWhom, String toWhom, String message){
		setFromWhom(fromWhom);
		setToWhom(toWhom);
		setMessage(message);
		setRecd(0);
		setSent(new Date());
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFromWhom() {
		return fromWhom;
	}

	public void setFromWhom(String fromWhom) {
		this.fromWhom = fromWhom;
	}

	public String getToWhom() {
		return toWhom;
	}

	public void setToWhom(String toWhom) {
		this.toWhom = toWhom;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getSent() {
		return sent;
	}

	public void setSent(Date sent) {
		this.sent = sent;
	}

	public Integer getRecd() {
		return recd;
	}

	public void setRecd(Integer recd) {
		this.recd = recd;
	}
	
}
