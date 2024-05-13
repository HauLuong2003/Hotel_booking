package com.HauLuong.HotelBook.Controller;

import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.apache.catalina.connector.Response;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import com.HauLuong.HotelBook.Exception.PhotoRetrievalException;
import com.HauLuong.HotelBook.Exception.ResourceNotFoundException;
import com.HauLuong.HotelBook.Model.BookedRoom;
import com.HauLuong.HotelBook.Model.Room;
import com.HauLuong.HotelBook.Response.BookingResponse;
import com.HauLuong.HotelBook.Response.RoomResponse;
import com.HauLuong.HotelBook.Service.IBookedRoomService;
import com.HauLuong.HotelBook.Service.IRoomService;


import lombok.RequiredArgsConstructor;

@CrossOrigin("http://localhost:8080")
@RestController
@RequiredArgsConstructor
@RequestMapping("/rooms")
public class RoomController {
	private IBookedRoomService bookedRoomService;
	private IRoomService roomService;
	
	public RoomController(IRoomService roomService,IBookedRoomService bookedRoomService) {
		super();
		this.roomService = roomService;
		this.bookedRoomService = bookedRoomService;
	}

	@PostMapping("/add/new-room")
	public ResponseEntity<RoomResponse> addNewRoom(@RequestParam("photo") MultipartFile photo,
									@RequestParam("roomType")String roomType, 
									@RequestParam("roomPrice")	BigDecimal roomPrice) throws SQLException, IOException{
		Room saveRoom = roomService.addNewRoom(photo,roomType,roomPrice);
		RoomResponse response = new RoomResponse(saveRoom.getId(),saveRoom.getRoomType(),saveRoom.getRoomPrice());
		return ResponseEntity.ok(response);	
	}
	@GetMapping("/room/types")
	public List<String> getRoomTypes(){
		
		return 	roomService.getAllTypes();
	}
	public ResponseEntity<List<RoomResponse>> getAllRoom() throws ResourceNotFoundException{
		List<Room> rooms = roomService.getAllRooms();
		List<RoomResponse> roomResponses = new ArrayList<>();
		for(Room room : rooms) {
			byte[] photoByte = roomService.getRoomPhotoById(room.getId());
			if(photoByte != null && photoByte.length > 0) {
				String base64Photo = Base64.encodeBase64String(photoByte);
				RoomResponse roomResponse = getRoomResponse(room);
				roomResponse.setPhoto(base64Photo);
				roomResponses.add(roomResponse);
			}
		}
		return ResponseEntity.ok(roomResponses);
	}
	// xoa phong
	@DeleteMapping("/delete/room/{roomId}")
	public ResponseEntity<Void> deleteRoom(@PathVariable("roomId") Long roomId){
		roomService.deleteRoom(roomId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
	}
	// update phong
	@PutMapping("/update/{roomId}")
	public ResponseEntity<RoomResponse> updatRoom(@PathVariable Long roomId,@RequestParam(required = false) String roomType,@RequestParam(required = false) BigDecimal roomPrice,@RequestParam(required = false) MultipartFile photo) throws SQLException, IOException, ResourceNotFoundException {
		
		byte[] photoBytes = photo != null && !photo.isEmpty()? photo.getBytes(): roomService.getRoomPhotoById(roomId);
		Blob photoBlob = photoBytes != null && photoBytes.length >0 ? new SerialBlob(photoBytes):null;
		Room theRoom = roomService.updateRoom(roomId,roomType,roomPrice,photoBytes);
		RoomResponse roomResponse = getRoomResponse(theRoom);
		
		return ResponseEntity.ok(roomResponse);
		
	}
	 @GetMapping("/room/{roomId}")
	    public ResponseEntity<Optional<RoomResponse>> getRoomById(@PathVariable Long roomId) throws ResourceNotFoundException{
	        Optional<Room> theRoom = roomService.getRoomById(roomId);
	        return theRoom.map(room -> {
	            RoomResponse roomResponse = getRoomResponse(room);
	            return  ResponseEntity.ok(Optional.of(roomResponse));
	        }).orElseThrow(() -> new ResourceNotFoundException("Room not found"));
	    }

	private RoomResponse getRoomResponse(Room room) {
		List<BookedRoom> bookings = getAllBookingsByRoomId(room.getId());
		List<BookingResponse> bookingInfo = (List<BookingResponse>) bookings.stream()
				.map(booking -> new BookingResponse(booking.getBookingId(), booking.getCheckInDate(), booking.getCheckOutDate(), booking.getBookingConfirmationCode()));
		byte[] photoBytes = null;
		Blob photoBlob = room.getPhoto();
		if(photoBlob != null) {
			try {
				photoBytes = photoBlob.getBytes(1,(int) photoBlob.length());
				
			}catch(SQLException e) {
				throw new PhotoRetrievalException("Erro retrieving photo");
			}
		}
		return new RoomResponse(room.getId(), room.getRoomType(), room.getRoomPrice(),room.isBooked(),photoBytes,bookingInfo);
		
	}
	private List<BookedRoom> getAllBookingsByRoomId(Long id){
		return bookedRoomService.getAllBookingByRoomId(id);
		
	}
}
