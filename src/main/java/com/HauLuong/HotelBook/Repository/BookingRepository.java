package com.HauLuong.HotelBook.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.HauLuong.HotelBook.Model.BookedRoom;

public interface BookingRepository extends JpaRepository<BookedRoom,Long>{
	List<BookedRoom> findByRoomId(Long roomId);

	 Optional<BookedRoom> findByBookingConfirmationCode(String confirmationCode);

	    List<BookedRoom> findByGuestEmail(String email);
}
