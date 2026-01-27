package com.smart_campus_system.backend.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smart_campus_system.backend.DTO.BookingRequestDTO;
import com.smart_campus_system.backend.Entity.BookingEntity;
import com.smart_campus_system.backend.Service.BookingService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/booking")
@CrossOrigin(origins="*")
public class BookingController {

	@Autowired
	BookingService bookingservice;
	
	@PostMapping("/create")
	public BookingEntity create(@RequestBody BookingRequestDTO dto) {
		return bookingservice.create(dto);
	}
	
}
