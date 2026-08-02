package com.crm.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.crm.enums.FollowupStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name="follow_ups")
public class FollowUp {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;
	
	private LocalDateTime followUpDateTime;
	
	@Enumerated(EnumType.STRING)
	private FollowupStatus status;
	
	@Column(nullable=false)
	private String notes;
	
	@CreationTimestamp
	@Column(nullable=false)
	private LocalDateTime createdAt;
	
	@UpdateTimestamp
	@Column(nullable=false)
	private LocalDateTime updatedAt;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public LocalDateTime getFollowUpDateTime() {
		return followUpDateTime;
	}

	public void setFollowUpDateTime(LocalDateTime followUpDateTime) {
		this.followUpDateTime = followUpDateTime;
	}

	public FollowupStatus getStatus() {
		return status;
	}

	public void setStatus(FollowupStatus status) {
		this.status = status;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	
}
