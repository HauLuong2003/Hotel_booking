package com.HauLuong.HotelBook.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HauLuong.HotelBook.Exception.InvalidBookingRequestException;
import com.HauLuong.HotelBook.Exception.ResourceNotFoundException;
import com.HauLuong.HotelBook.Model.BookedRoom;
import com.HauLuong.HotelBook.Model.Room;
import com.HauLuong.HotelBook.Response.BookingResponse;
import com.HauLuong.HotelBook.Response.RoomResponse;
import com.HauLuong.HotelBook.Service.IBookedRoomService;
import com.HauLuong.HotelBook.Service.IRoomService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/booking")
public class BookingRoomController {
	private final IBookedRoomService BookedRoomService;
	private final IRoomService roomService;
	public BookingRoomController(IBookedRoomService BookedRoomService,IRoomService roomService) {
		this.BookedRoomService = BookedRoomService;
		this.roomService = roomService;
	}
	
	public ResponseEntity<List<BookingResponse>> getAllbookings(){
		List<BookedRoom> bookings = BookedRoomService.getAllBookings();
        List<BookingResponse> bookingResponses = new ArrayList<>();
		for(BookedRoom booking : bookings) {
			BookingResponse bookingResponse = getBookingResponse(booking);
			 bookingResponses.add(bookingResponse);
		}
		
		return ResponseEntity.ok(bookingResponses);
		
	}
	//
	 @PostMapping("/room/{roomId}/booking")
	    public ResponseEntity<?> saveBooking(@PathVariable Long roomId,
	                                         @RequestBody BookedRoom bookingRequest){
	        try{
	            String confirmationCode = BookedRoomService.saveBooking(roomId, bookingRequest);
	            return ResponseEntity.ok(
	                    "Room booked successfully, Your booking confirmation code is :"+confirmationCode);

	        }catch (InvalidBookingRequestException e){
	            return ResponseEntity.badRequest().body(e.getMessage());
	        }
	    }
	 //
	 @GetMapping("/confirmation/{confirmationCode}")
	    public ResponseEntity<?> getBookingByConfirmationCode(@PathVariable String confirmationCode) throws ResourceNotFoundException{
	        BookedRoom booking = BookedRoomService.findByBookingConfirmationCode(confirmationCode);
			BookingResponse bookingResponse = getBookingResponse(booking);
			return ResponseEntity.ok(bookingResponse);
	    }

	 private BookingResponse getBookingResponse(BookedRoom booking) {
	        Room theRoom = roomService.getRoomById(booking.getRoom().getId()).get();
	        RoomResponse room = new RoomResponse(
	                theRoom.getId(),
	                theRoom.getRoomType(),
	                theRoom.getRoomPrice());
	        return new BookingResponse(
	                booking.getBookingId(), booking.getCheckInDate(),
	                booking.getCheckOutDate(),booking.getGuestFullName(),
	                booking.getGuestEmail(), booking.getNumOfAdults(),
	                booking.getNumOfChildren(), booking.getTotalNumOfGuest(),
	                booking.getBookingConfirmationCode(), room);
	    }
}
