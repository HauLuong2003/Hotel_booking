package com.HauLuong.HotelBook.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.HauLuong.HotelBook.Model.User;

public interface UserRepository extends JpaRepository<User, Long> {
		boolean existsByEmail(String email);

	    void deleteByEmail(String email);

	   Optional<User> findByEmail(String email);
}
