 package com.HauLuong.HotelBook.Service;

import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.sql.rowset.serial.SerialBlob;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.HauLuong.HotelBook.Exception.ResourceNotFoundException;
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

	@Override
	public List<String> getAllTypes() {
		
		return roomRepository.findDistinctRoomTyes();
	}

	@Override
	public List<Room> getAllRooms() {
		
		return roomRepository.findAll();
	}

	@Override
	public byte[] getRoomPhotoById(Long id) throws ResourceNotFoundException {
		Optional<Room> theRoom = roomRepository.findById(id);
		if(theRoom.isEmpty()) {
			throw new ResourceNotFoundException("Sorry,Room not found!");
			
		}
		Blob photoBlob = theRoom.get().getPhoto();
		if(photoBlob != null) {
			try {
				return photoBlob.getBytes(1 ,(int)photoBlob.length());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

}
