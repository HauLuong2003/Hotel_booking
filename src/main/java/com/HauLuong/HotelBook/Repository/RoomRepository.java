package com.HauLuong.HotelBook.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.HauLuong.HotelBook.Model.Room;

public interface RoomRepository extends JpaRepository<Room, Long>{
	@Query("SELECT DISTINCT r.roomType FROM room r")
	List<String> findDistinctRoomTyes();
}
