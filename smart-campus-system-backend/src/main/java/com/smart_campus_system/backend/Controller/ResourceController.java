package com.smart_campus_system.backend.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smart_campus_system.backend.Entity.ResourcesEntity;
import com.smart_campus_system.backend.Service.ResourceService;

@RestController
@RequestMapping("/api/resource")
@CrossOrigin(origins="*")
public class ResourceController {
	@Autowired
	ResourceService service;
	
	@PostMapping("/add")
	public ResourcesEntity add(@RequestBody ResourcesEntity resources) {
		return service.add(resources);
	}
	
	@GetMapping("/all")
	public List<ResourcesEntity> getAll(){
		return service.findall();
	}
	
	@GetMapping("/{id}")
	public ResourcesEntity findById(@PathVariable int id) {
		return service.findById(id);
	}
	
	@PutMapping("/update/{id}")
	public ResourcesEntity updateEntity(@PathVariable int id,@RequestBody ResourcesEntity new_data) { 
		return service.updateEntity(id, new_data);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteById(@PathVariable int id) {
		service.deleteById(id);
	}
}
