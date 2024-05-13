 package com.HauLuong.HotelBook.Service;

import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

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

	@Override
	public void deleteRoom(Long roomId) {
		Optional<Room> theRoom = roomRepository.findById(roomId);
		if(theRoom.isPresent()) {
			roomRepository.deleteById(roomId);
		}
	}

	@Override
	public Room updateRoom(Long roomId, String roomType, BigDecimal roomPrice, byte[] photoBytes) throws ResourceNotFoundException {
		Room room = roomRepository.findById(roomId).orElseThrow(()-> new ResourceNotFoundException("room not found"));

		try {
			if(roomType != null) {
				room.setRoomType(roomType);
			}
			if(roomPrice != null) {
				room.setRoomPrice(roomPrice);
			}
			if(photoBytes != null && photoBytes.length > 0) {
				room.setBookings(null);
			}
			room.setPhoto(new SerialBlob(photoBytes));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return roomRepository.save(room);
	}

	@Override
	public Optional<Room> getRoomById(Long roomId) {
		
		return Optional.of(roomRepository.findById(roomId).get());
	}

	@Override
	public List<Room> getAvailableRooms(LocalDate checkInDate, LocalDate checkOutDate, String roomType) {
		// TODO Auto-generated method stub
        return roomRepository.findAvailableRoomsByDatesAndType(checkInDate, checkOutDate, roomType);
	}
	
}
