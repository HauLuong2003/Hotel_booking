package com.HauLuong.HotelBook.Response;

import java.math.BigDecimal;

import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
public class RoomResponse {
	private Long id;
	private String roomType;
	private BigDecimal roomPrice;
	private boolean isBooked = false;
	private String photo;
	private List<BookingResponse> bookings;
	public RoomResponse(Long id, String roomType, BigDecimal roomPrice) {
		super();
		this.id = id;
		this.roomType = roomType;
		this.roomPrice = roomPrice;
	}
	public RoomResponse(Long id, String roomType, BigDecimal roomPrice
			, boolean isBooked, byte[] photoBytes,List<BookingResponse> bookings) {
		super();
		this.id = id;
		this.roomType = roomType;
		this.roomPrice = roomPrice;
		this.isBooked = isBooked;
		this.photo = photoBytes != null  ? Base64.encodeBase64String(photoBytes): null;
		this.bookings = bookings;
	}
	public Long getId() {
		return id;
	}
	public String getRoomType() {
		return roomType;
	}
	public BigDecimal getRoomPrice() {
		return roomPrice;
	}
	public boolean isBooked() {
		return isBooked;
	}
	public String getPhoto() {
		return photo;
	}
	public List<BookingResponse> getBookings() {
		return bookings;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public void setRoomPrice(BigDecimal roomPrice) {
		this.roomPrice = roomPrice;
	}
	public void setBooked(boolean isBooked) {
		this.isBooked = isBooked;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public void setBookings(List<BookingResponse> bookings) {
		this.bookings = bookings;
	}
	 
}
