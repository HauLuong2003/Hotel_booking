package com.HauLuong.HotelBook.Service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.HauLuong.HotelBook.Exception.UserAlreadyExistsException;
import com.HauLuong.HotelBook.Model.User;
import com.HauLuong.HotelBook.Model.Role;

import com.HauLuong.HotelBook.Repository.RoleRepository;
import com.HauLuong.HotelBook.Repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
	 private  UserRepository userRepository;
	    private  PasswordEncoder passwordEncoder;
	    private  RoleRepository roleRepository;
	@Override
	public User registerUser(User user) {
		if (userRepository.existsByEmail(user.getEmail())){
            throw new UserAlreadyExistsException(user.getEmail() + " already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        System.out.println(user.getPassword());
        Role userRole = roleRepository.findByName("ROLE_USER").get();
        user.setRoles(Collections.singletonList(userRole));
        return userRepository.save(user);
	}

	@Override
	public List<User> getUsers() {
		
		   return userRepository.findAll();
	}

	@Override
	public void deleteUser(String email) {
		User theUser = getUser(email);
        if (theUser != null){
            userRepository.deleteByEmail(email);
        }
		
	}

	@Override
	public User getUser(String email) {
	     return userRepository.findByEmail(email)
	                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
	}

}
