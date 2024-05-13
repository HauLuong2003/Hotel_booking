package com.HauLuong.HotelBook.Service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.HauLuong.HotelBook.Exception.ResourceNotFoundException;
import com.HauLuong.HotelBook.Model.Room;

public interface IRoomService {
	Room addNewRoom(MultipartFile photo,String roomType,BigDecimal roomPrice);
	List<String> getAllTypes();
	List<Room> getAllRooms();
	byte[] getRoomPhotoById(Long id) throws ResourceNotFoundException;
}
