package com.HauLuong.HotelBook.Service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.HauLuong.HotelBook.Exception.ResourceNotFoundException;
import com.HauLuong.HotelBook.Model.Room;

import java.io.IOException;

public interface IRoomService {
	 Room addNewRoom(MultipartFile photo, String roomType, BigDecimal roomPrice) throws SQLException, IOException;

	    List<String> getAllRoomTypes();

	    List<Room> getAllRooms();

	    byte[] getRoomPhotoByRoomId(Long roomId) throws SQLException;

	    void deleteRoom(Long roomId);

	    Room updateRoom(Long roomId, String roomType, BigDecimal roomPrice, byte[] photoBytes);

	    Optional<Room> getRoomById(Long roomId);

	    List<Room> getAvailableRooms(LocalDate checkInDate, LocalDate checkOutDate, String roomType);

}
