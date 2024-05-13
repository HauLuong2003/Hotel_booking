package com.HauLuong.HotelBook.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.HauLuong.HotelBook.Model.BookedRoom;
import com.HauLuong.HotelBook.Repository.BookedRoomRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookedRoomServiceImpl implements IBookedRoomService {
	private BookedRoomRepository bookingRepository;
	  private  IRoomService roomService;
	  public BookedRoomServiceImpl( BookedRoomRepository bookingRepository,IRoomService roomService) {
		  this.bookingRepository= bookingRepository;
		  this.roomService =roomService;
	  }
	@Override
	public List<BookedRoom> getAllBookingByRoomId(Long id) {
		
		return null;
	}

	@Override
	public void cancelBooking(Long bookingId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String saveBooking(Long roomId, BookedRoom bookingRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BookedRoom findByBookingConfirmationCode(String confirmationCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BookedRoom> getAllBookings() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BookedRoom> getBookingsByUserEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

}
