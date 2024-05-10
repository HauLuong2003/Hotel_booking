package com.HauLuong.HotelBook.Service;

import java.math.BigDecimal;

import org.springframework.web.multipart.MultipartFile;

import com.HauLuong.HotelBook.Model.Room;

public interface IRoomService {
	Room addNewRoom(MultipartFile photo,String roomType,BigDecimal roomPrice);
 
}
