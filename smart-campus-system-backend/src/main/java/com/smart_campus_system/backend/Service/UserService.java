package com.smart_campus_system.backend.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smart_campus_system.backend.Entity.UserEntity;
import com.smart_campus_system.backend.Repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository repository;
	
	public UserEntity add(UserEntity userEntity) {
		return repository.save(userEntity);
	}
	
	public List<UserEntity> getAllUsers(){
		return repository.findAll();
	}
	
	public UserEntity getById(int id) {
		return repository.findById(id).orElse(null);
	}
	
	public UserEntity update(int id,UserEntity userEntity) {
		UserEntity current=repository.findById(id).orElse(null);
		if(current!=null) {
			current.setName(userEntity.getName());
			current.setEmail(userEntity.getEmail());
			current.setPassword(userEntity.getPassword());
			current.setRole(userEntity.getRole());
			current.setStatus(userEntity.getStatus());
			
			return repository.save(current);
		}
		return null;
	}
	
	public void deleteById(int id) {
		repository.deleteById(id);
	}

}
