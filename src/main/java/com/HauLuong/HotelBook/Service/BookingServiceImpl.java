package com.HauLuong.HotelBook.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.HauLuong.HotelBook.Exception.InvalidBookingRequestException;
import com.HauLuong.HotelBook.Exception.ResourceNotFoundException;
import com.HauLuong.HotelBook.Model.BookedRoom;
import com.HauLuong.HotelBook.Model.Room;
import com.HauLuong.HotelBook.Repository.BookingRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements IBookedRoomService {
	private BookingRepository bookingRepository;
	  private  IRoomService roomService;
	  public BookingServiceImpl( BookingRepository bookingRepository,IRoomService roomService) {
		  this.bookingRepository= bookingRepository;
		  this.roomService =roomService;
	  }
	@Override
	public List<BookedRoom> getAllBookingByRoomId(Long id) {
		
		 return bookingRepository.findByRoomId(id);	
	}

	@Override
	public void cancelBooking(Long bookingId) {
		// TODO Auto-generated method stub
	    bookingRepository.deleteById(bookingId);
	}

	@Override
	public String saveBooking(Long roomId, BookedRoom bookingRequest) {
		if (bookingRequest.getCheckOutDate().isBefore(bookingRequest.getCheckInDate())){
            throw new InvalidBookingRequestException("Check-in date must come before check-out date");
        }
        Room room = roomService.getRoomById(roomId).get();
        List<BookedRoom> existingBookings = room.getBookings();
        boolean roomIsAvailable = roomIsAvailable(bookingRequest,existingBookings);
        if (roomIsAvailable){
            room.addBooking(bookingRequest);
            bookingRepository.save(bookingRequest);
        }else{
            throw  new InvalidBookingRequestException("Sorry, This room is not available for the selected dates;");
        }
        return bookingRequest.getBookingConfirmationCode();
    }



	@Override
	public BookedRoom findByBookingConfirmationCode(String confirmationCode) throws ResourceNotFoundException {
	    return bookingRepository.findByBookingConfirmationCode(confirmationCode)
                .orElseThrow(() -> new ResourceNotFoundException("No booking found with booking code :"+confirmationCode));
	}

	@Override
	public List<BookedRoom> getAllBookings() {
		// TODO Auto-generated method stub
		 return bookingRepository.findAll();
	}

	@Override
	public List<BookedRoom> getBookingsByUserEmail(String email) {
		// TODO Auto-generated method stub
		  return bookingRepository.findByGuestEmail(email);
	}
	private boolean roomIsAvailable(BookedRoom bookingRequest, List<BookedRoom> existingBookings) {
        return existingBookings.stream()
                .noneMatch(existingBooking ->
                        bookingRequest.getCheckInDate().equals(existingBooking.getCheckInDate())
                                || bookingRequest.getCheckOutDate().isBefore(existingBooking.getCheckOutDate())
                                || (bookingRequest.getCheckInDate().isAfter(existingBooking.getCheckInDate())
                                && bookingRequest.getCheckInDate().isBefore(existingBooking.getCheckOutDate()))
                                || (bookingRequest.getCheckInDate().isBefore(existingBooking.getCheckInDate())

                                && bookingRequest.getCheckOutDate().equals(existingBooking.getCheckOutDate()))
                                || (bookingRequest.getCheckInDate().isBefore(existingBooking.getCheckInDate())

                                && bookingRequest.getCheckOutDate().isAfter(existingBooking.getCheckOutDate()))

                                || (bookingRequest.getCheckInDate().equals(existingBooking.getCheckOutDate())
                                && bookingRequest.getCheckOutDate().equals(existingBooking.getCheckInDate()))

                                || (bookingRequest.getCheckInDate().equals(existingBooking.getCheckOutDate())
                                && bookingRequest.getCheckOutDate().equals(bookingRequest.getCheckInDate()))
                );
	}
}
