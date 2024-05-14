package com.HauLuong.HotelBook.Service;

import java.util.List;

import com.HauLuong.HotelBook.Exception.ResourceNotFoundException;
import com.HauLuong.HotelBook.Model.BookedRoom;

public interface IBookingService {
	void cancelBooking(Long bookingId);
	List<BookedRoom> getAllBookingByRoomId(Long id);
	String saveBooking(Long roomId, BookedRoom bookingRequest);

	BookedRoom findByBookingConfirmationCode(String confirmationCode) throws ResourceNotFoundException;

	List<BookedRoom> getAllBookings();

	List<BookedRoom> getBookingsByUserEmail(String email);
}
