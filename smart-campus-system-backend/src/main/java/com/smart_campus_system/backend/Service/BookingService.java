package com.smart_campus_system.backend.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart_campus_system.backend.DTO.BookingRequestDTO;
import com.smart_campus_system.backend.Entity.BookingEntity;
import com.smart_campus_system.backend.Entity.BookingStatus;
import com.smart_campus_system.backend.Entity.ResourcesEntity;
import com.smart_campus_system.backend.Entity.UserEntity;
import com.smart_campus_system.backend.Repository.BookingRepository;
import com.smart_campus_system.backend.Repository.ResourceRepository;
import com.smart_campus_system.backend.Repository.UserRepository;

@Service
public class BookingService {
	@Autowired
	BookingRepository repository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	ResourceRepository resourceRepository;
	
	public BookingEntity create(BookingRequestDTO dto){
		UserEntity userEntity=userRepository.findById(dto.getUserId()).orElseThrow();
		
		ResourcesEntity resourceEntity=resourceRepository.findById(dto.resourceId).orElseThrow();
		
		BookingEntity current=new BookingEntity();
		current.setUser(userEntity);
		current.setResource(resourceEntity);
		current.setBookingDate(dto.getBookingDate());
		current.setStartTime(dto.getStartTime());
		current.setEndTime(dto.getEndTime());
		current.setStatus(BookingStatus.PENDING);
		return repository.save(current);
		
	}
	
	public BookingEntity get(int id) {
		return repository.findById(id).orElse(null);
	}
	
	public List<BookingEntity> getall(){
		return repository.findAll();
	}
	
	

}
