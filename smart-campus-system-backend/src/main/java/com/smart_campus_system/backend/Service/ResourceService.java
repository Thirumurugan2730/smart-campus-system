package com.smart_campus_system.backend.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart_campus_system.backend.Entity.ResourcesEntity;
import com.smart_campus_system.backend.Repository.ResourceRepository;

@Service
public class ResourceService {
	@Autowired
	ResourceRepository repository;
	
	public ResourcesEntity add(ResourcesEntity data) {
		return repository.save(data);
	}
	
	public List<ResourcesEntity> findall(){
		return repository.findAll();
	}
	
	public ResourcesEntity findById(int id) {
		return repository.findById(id).orElse(null);
	}
	
	public ResourcesEntity updateEntity(int id,ResourcesEntity new_data) {
		ResourcesEntity existing=repository.findById(id).orElse(null);
		if(existing!=null) {
			existing.setName(new_data.getName());
			existing.setType(new_data.getType());
			existing.setCapacity(new_data.getCapacity());
			existing.setResourcestatus(new_data.getResourcestatus());
			existing.setLocation(new_data.getLocation());
			
			return repository.save(existing);
		}
		return null;
	}
	
	public void deleteById(int id) {
		 repository.deleteById(id);
	}
}
