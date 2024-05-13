package com.HauLuong.HotelBook.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.HauLuong.HotelBook.Exception.ResourceNotFoundException;
import com.HauLuong.HotelBook.Model.Room;

public interface IRoomService {
	Room addNewRoom(MultipartFile photo,String roomType,BigDecimal roomPrice);
	List<String> getAllTypes();
	List<Room> getAllRooms();
	byte[] getRoomPhotoById(Long id) throws ResourceNotFoundException;
	void deleteRoom (Long roomId);
	Room updateRoom(Long roomId, String roomType, BigDecimal roomPrice, byte[] photoBytes) throws ResourceNotFoundException;
	Optional<Room> getRoomById(Long roomId);
    List<Room> getAvailableRooms(LocalDate checkInDate, LocalDate checkOutDate, String roomType);

}
