package com.HauLuong.HotelBook.Service;

import java.util.List;

import com.HauLuong.HotelBook.Model.BookedRoom;

public interface IBookedRoomService {

	List<BookedRoom> getAllBookingByRoomId(Long id);

}
