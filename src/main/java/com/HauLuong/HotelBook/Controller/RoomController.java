package com.HauLuong.HotelBook.Controller;

import java.math.BigDecimal;

import java.sql.SQLException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.HauLuong.HotelBook.Model.Room;
import com.HauLuong.HotelBook.Response.RoomResponse;
import com.HauLuong.HotelBook.Service.IRoomService;

import io.jsonwebtoken.io.IOException;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rooms")
public class RoomController {
	
	private IRoomService roomService;
	
	public RoomController(IRoomService roomService) {
		super();
		this.roomService = roomService;
	}

	@PostMapping("/add/new-room")
	public ResponseEntity<RoomResponse> addNewRoom(@RequestParam("photo") MultipartFile photo,
									@RequestParam("roomType")String roomType, 
									@RequestParam("roomPrice")	BigDecimal roomPrice) throws SQLException, IOException{
		Room saveRoom = roomService.addNewRoom(photo,roomType,roomPrice);
		RoomResponse response = new RoomResponse(saveRoom.getId(),saveRoom.getRoomType(),saveRoom.getRoomPrice());
		return ResponseEntity.ok(response);	
	}
}
