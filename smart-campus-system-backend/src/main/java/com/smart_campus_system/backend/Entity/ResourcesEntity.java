package com.smart_campus_system.backend.Entity;

import java.time.LocalDateTime;
import java.time.LocalTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name="resources")
@Entity
public class ResourcesEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="resources_id")
	private int resourcesID;
	
	@Column(nullable=false,unique=true)
	private String name;
	
	@Column(nullable=false)
	@Enumerated(EnumType.STRING)
	private Type type;
	
	@Column(nullable=false)
	private int capacity;
	
	@Column(nullable=false)
	@Enumerated(EnumType.STRING)
	private ResourceStatus resourcestatus;
	
	@Column(nullable=false)
	private String location;
	
	@Column(name="created_at")
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@Column(name="updated_at")
	@UpdateTimestamp
	private LocalDateTime updatedAt;

	public int getResourcesID() {
		return resourcesID;
	}

	public void setResourcesID(int resourcesID) {
		this.resourcesID = resourcesID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public ResourceStatus getResourcestatus() {
		return resourcestatus;
	}

	public void setResourcestatus(ResourceStatus resourcestatus) {
		this.resourcestatus = resourcestatus;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
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
