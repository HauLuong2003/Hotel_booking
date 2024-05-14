package com.HauLuong.HotelBook.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.HauLuong.HotelBook.Model.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
		Optional<Role> findByName(String role);
	    boolean existsByName(String role);
}
