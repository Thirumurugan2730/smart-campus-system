package com.smart_campus_system.backend.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart_campus_system.backend.DTO.BookingRequestDTO;
import com.smart_campus_system.backend.Entity.BookingEntity;
import com.smart_campus_system.backend.Entity.BookingStatus;
import com.smart_campus_system.backend.Entity.ResourcesEntity;
import com.smart_campus_system.backend.Entity.Role;
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
		UserEntity user =userRepository.findById(dto.getUserId()).orElseThrow();
		ResourcesEntity resource =resourceRepository.findById(dto.getResourceId()).orElseThrow();
		
		int newPriority=getpriority(user.getRole());
		
		List<BookingEntity> conflicts =repository.findConflictBookings(dto.getResourceId(),dto.getBookingDate(),
				                                   dto.getStartTime(),dto.getEndTime());
		BookingEntity newBooking =new BookingEntity();
		newBooking.setUser(user);
		newBooking.setResource(resource);
		newBooking.setStartTime(dto.getStartTime());
		newBooking.setEndTime(dto.getEndTime());
		newBooking.setPurpose(dto.getPurpose());
		newBooking.setStatus(BookingStatus.PENDING);
		
		if(conflicts.isEmpty()){
			repository.save(newBooking);
		}
		for(BookingEntity oldBooking :conflicts) {
			UserEntity u=oldBooking.getUser();
			int oldPriority=getpriority(user.getRole());
			
			if(newPriority<oldPriority) {
				newBooking.setStatus(BookingStatus.REJECTED);
			}
			else if(newPriority==oldPriority) {
				newBooking.setStatus(BookingStatus.PENDING);
			}else {
				oldBooking.setStatus(BookingStatus.REJECTED);
				newBooking.setStatus(BookingStatus.PENDING);
				repository.save(oldBooking);
			}
			
		}
		return repository.save(newBooking);
		
	}
	
	public BookingEntity get(int id) {
		return repository.findById(id).orElse(null);
	}
	
	public List<BookingEntity> getall(){
		return repository.findAll();
	}
	int getpriority(Role role) {
		if(role.equals(Role.ADMIN)) {
			return 3;
		}else if(role.equals(Role.FACULTY)) {
			return 2;
		}else {
			return 1;
		}
	}
	
	

}
