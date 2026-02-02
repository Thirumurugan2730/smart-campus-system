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

import com.smart_campus_system.backend.DTO.BookingRequestDTO;
import com.smart_campus_system.backend.Entity.BookingEntity;
import com.smart_campus_system.backend.Service.BookingService;



@RestController
@RequestMapping("/api/booking")
@CrossOrigin(origins="*")
public class BookingController {

	@Autowired
	BookingService bookingservice;
	
	@GetMapping("/get/all")
	public List<BookingEntity> getAll(){
		return bookingservice.getAll();
	}
	
	@GetMapping("/get/{id}")
	public BookingEntity getById(@PathVariable int id) {
		return bookingservice.getById(id);
	}
	
	@PostMapping("/create")
	public BookingEntity create(@RequestBody BookingRequestDTO dto) {
		return bookingservice.create(dto);
	}
	
	@PutMapping("/update/{id}")
	public BookingEntity update(@PathVariable int id,@RequestBody BookingRequestDTO dto) {
		return bookingservice.update(id,dto);	
	}
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable int id) {
		 bookingservice.delete(id);
	}
	
}
