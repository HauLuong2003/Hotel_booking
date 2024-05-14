package com.HauLuong.HotelBook.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.HauLuong.HotelBook.Model.User;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

	@Override
	public User registerUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUser(String email) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User getUser(String email) {
		// TODO Auto-generated method stub
		return null;
	}

}
