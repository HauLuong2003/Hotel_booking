package com.HauLuong.HotelBook.Model;

import java.time.LocalDate;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookedRoom {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bookingId;
	
	@Column(name = "check_in")
	private LocalDate checkInDate;
	
	@Column(name = "check_out")
	private LocalDate checkOutDate;
	
	@Column(name = "Guest_FullName")
	private String guestFullName;
	
	@Column(name = "Guest_Email")
	private String guestEmail;
	
	@Column(name = "adults")
	private int NumOfAdults;
	
	@Column(name = "children")
	private int NumOfChildren;
	
	@Column(name = "total_guest")
	private int totalNumOfGuest;
	
	@Column(name = "confirmation_Code") 
	private String bookingConfirmationCode;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "room_id")
	private Room room;
	//tao phuong thuc tinh toan
	public void CalculateToTalNumberOfGuest() {
		this.totalNumOfGuest = this.NumOfAdults +this.NumOfChildren;
	}
	public int getNumOfAdults() {
		return NumOfAdults;
	}
	public int getNumOfChildren() {
		return NumOfChildren;
	}
	// goi den phuong thuc khi co su thay doi ve nguoi trong book
	public void setNumOfAdults(int numOfAdults) {
		NumOfAdults = numOfAdults;
		CalculateToTalNumberOfGuest();
	}
	public void setNumOfChildren(int numOfChildren) {
		NumOfChildren = numOfChildren;
		CalculateToTalNumberOfGuest();
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	public String getBookingConfirmationCode() {
		return bookingConfirmationCode;
	}
	public void setBookingConfirmationCode(String bookingConfirmationCode) {
		this.bookingConfirmationCode = bookingConfirmationCode;
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
	public int getTotalNumOfGuest() {
		return totalNumOfGuest;
	}
	public Room getRoom() {
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
	public void setTotalNumOfGuest(int totalNumOfGuest) {
		this.totalNumOfGuest = totalNumOfGuest;
	}

}
