 package com.HauLuong.HotelBook.Service;

import java.math.BigDecimal;
import java.sql.Blob;

import javax.sql.rowset.serial.SerialBlob;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.HauLuong.HotelBook.Model.Room;
import com.HauLuong.HotelBook.Repository.RoomRepository;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements IRoomService{
	private RoomRepository roomRepository;
	
	public RoomServiceImpl(RoomRepository roomRepository) {
		super();
		this.roomRepository = roomRepository;
	}

	@Override
	public Room addNewRoom(MultipartFile photo, String roomType,BigDecimal roomPrice) {
		Room room = new Room();
		room.setRoomType(roomType);
		room.setRoomPrice(roomPrice);
		try {
		if(!photo.isEmpty()) {
			byte[] photoBytes = photo.getBytes();
			Blob photoBlob = new SerialBlob(photoBytes);
			room.setPhoto(photoBlob);
		}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return roomRepository.save(room);
	}

}
