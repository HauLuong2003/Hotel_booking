package com.HauLuong.HotelBook.Service;

public interface IUserService {
	 User registerUser(User user);
	 List<User> getUsers();
	 void deleteUser(String email);
	 User getUser(String email);
}
