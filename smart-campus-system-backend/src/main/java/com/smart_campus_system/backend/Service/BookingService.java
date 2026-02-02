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
	
	public List<BookingEntity> getAll(){
		return repository.findAll();
	}
	
	public BookingEntity getById(int id) {
		return repository.findById(id).orElseThrow();
	}
	
	public BookingEntity create(BookingRequestDTO dto){
		
		System.out.println("UserId from DTO = " + dto.getUserId());
		System.out.println("ResourceId from DTO = " + dto.getResourceId());
		
		UserEntity user =userRepository.findById(dto.getUserId()).orElseThrow();
		ResourcesEntity resource =resourceRepository.findById(dto.getResourceId()).orElseThrow();

		
		int newPriority=getpriority(user.getRole());
		
		List<BookingEntity> conflicts =repository.findConflictBookings(dto.getResourceId(),dto.getBookingDate(),
				                                   dto.getStartTime(),dto.getEndTime());
		BookingEntity newBooking =new BookingEntity();
		newBooking.setUser(user);
		newBooking.setResource(resource);
		newBooking.setBookingDate(dto.getBookingDate());
		newBooking.setStartTime(dto.getStartTime());
		newBooking.setEndTime(dto.getEndTime());
		newBooking.setPurpose(dto.getPurpose());
		newBooking.setStatus(BookingStatus.PENDING);
		
		if(conflicts.isEmpty()){
			repository.save(newBooking);
		}
		for(BookingEntity oldBooking :conflicts) {
			UserEntity u=oldBooking.getUser();
			int oldPriority=getpriority(u.getRole());
			
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
	
	int getpriority(Role role) {
		if(role.equals(Role.ADMIN)) {
			return 3;
		}else if(role.equals(Role.FACULTY)) {
			return 2;
		}else {
			return 1;
		}
	}
	
	public BookingEntity update(int id,BookingRequestDTO dto) {
		BookingEntity data=repository.findById(id).orElseThrow();
		UserEntity user=userRepository.findById(dto.getUserId()).orElseThrow();
		ResourcesEntity resource=resourceRepository.findById(dto.getResourceId()).orElseThrow();
		
		data.setUser(user);
		data.setResource(resource);
		data.setBookingDate(dto.getBookingDate());
		data.setStartTime(dto.getStartTime());
		data.setEndTime(dto.getEndTime());
		data.setPurpose(dto.getPurpose());
		
		return repository.save(data);
		
	}
	
	public void delete(int id) {
		repository.deleteById(id);
	}
	

}
