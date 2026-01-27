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

import com.smart_campus_system.backend.Entity.UserEntity;
import com.smart_campus_system.backend.Service.UserService;



@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins="*")
public class UserController {

	@Autowired
	UserService service;
	
	 	@PostMapping("/add")
		    public UserEntity addUser(@RequestBody UserEntity user) {
		        return service.add(user);
		    }

	   
	    @GetMapping("/all")
		    public List<UserEntity> getAllUsers() {
		        return service.getAllUsers();
		    }

	    // Get user by id
	    @GetMapping("/{id}")
	    public UserEntity getUserById(@PathVariable int id) {
	        return service.getById(id);
	    }

	    // Update user
	    @PutMapping("/update/{id}")
	    public UserEntity updateUser(@PathVariable int id,
	                                 @RequestBody UserEntity user) {
	        return service.update(id, user);
	    }

	    // Delete user
	    @DeleteMapping("/delete/{id}")
	    public String deleteUser(@PathVariable int id) {
	        service.deleteById(id);
	        return "User deleted successfully";
	    }
}

