package com.HauLuong.HotelBook.Service;

import java.util.List;

import com.HauLuong.HotelBook.Model.User;

public interface IUserService {
	 User registerUser(User user);
	 List<User> getUsers();
	 void deleteUser(String email);
	 User getUser(String email);
}
