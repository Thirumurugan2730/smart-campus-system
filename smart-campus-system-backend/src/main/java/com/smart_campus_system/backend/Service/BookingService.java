package com.smart_campus_system.backend.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart_campus_system.backend.DTO.BookingRequestDTO;
import com.smart_campus_system.backend.Entity.BookingEntity;
import com.smart_campus_system.backend.Repository.BookingRepository;

@Service
public class BookingService {
	@Autowired
	BookingRepository repository;
	
	public BookingEntity create(BookingRequestDTO dto){
		return repository.save(dto);
	}
	
	public BookingEntity get(int id) {
		return repository.findById(id).orElse(null);
	}
	
	public List<BookingEntity> getall(){
		return repository.findAll();
	}
	
	

}
