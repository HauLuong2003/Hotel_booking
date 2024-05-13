package com.HauLuong.HotelBook.Response;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponse {
	private Long bookingId;
	private LocalDate checkInDate;
	private LocalDate checkOutDate;
	private String guestFullName;
	private String guestEmail;
	private int NumOfAdults;
	private int NumOfChildren;
	private int totalNumOfGuest;
	private String bookingConfirmationCode;
	private RoomResponse room;
	public BookingResponse(Long bookingId, LocalDate checkInDate, LocalDate checkOutDate,
			String bookingConfirmationCode) {
		super();
		this.bookingId = bookingId;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.bookingConfirmationCode = bookingConfirmationCode;
	}
	
	public BookingResponse(Long bookingId, LocalDate checkInDate, LocalDate checkOutDate, String guestFullName,
			String guestEmail, int numOfAdults, int numOfChildren, int totalNumOfGuest, String bookingConfirmationCode,
			RoomResponse room) {
		super();
		this.bookingId = bookingId;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.guestFullName = guestFullName;
		this.guestEmail = guestEmail;
		NumOfAdults = numOfAdults;
		NumOfChildren = numOfChildren;
		this.totalNumOfGuest = totalNumOfGuest;
		this.bookingConfirmationCode = bookingConfirmationCode;
		this.room = room;
	}

	public Long getBookingId() {
		return bookingId;
	}
	public LocalDate getCheckInDate() {
		return checkInDate;
	}
	public LocalDate getCheckOutDate() {
		return checkOutDate;
	}
	public String getGuestFullName() {
		return guestFullName;
	}
	public String getGuestEmail() {
		return guestEmail;
	}
	public int getNumOfAdults() {
		return NumOfAdults;
	}
	public int getNumOfChildren() {
		return NumOfChildren;
	}
	public int getTotalNumOfGuest() {
		return totalNumOfGuest;
	}
	public String getBookingConfirmationCode() {
		return bookingConfirmationCode;
	}
	public RoomResponse getRoom() {
		return room;
	}
	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}
	public void setCheckInDate(LocalDate checkInDate) {
		this.checkInDate = checkInDate;
	}
	public void setCheckOutDate(LocalDate checkOutDate) {
		this.checkOutDate = checkOutDate;
	}
	public void setGuestFullName(String guestFullName) {
		this.guestFullName = guestFullName;
	}
	public void setGuestEmail(String guestEmail) {
		this.guestEmail = guestEmail;
	}
	public void setNumOfAdults(int numOfAdults) {
		NumOfAdults = numOfAdults;
	}
	public void setNumOfChildren(int numOfChildren) {
		NumOfChildren = numOfChildren;
	}
	public void setTotalNumOfGuest(int totalNumOfGuest) {
		this.totalNumOfGuest = totalNumOfGuest;
	}
	public void setBookingConfirmationCode(String bookingConfirmationCode) {
		this.bookingConfirmationCode = bookingConfirmationCode;
	}
	public void setRoom(RoomResponse room) {
		this.room = room;
	}

}
