package com.HauLuong.HotelBook.Exception;

public class RoleAlreadyExistException extends RuntimeException{
	public RoleAlreadyExistException(String message) {
        super(message);
    }
}
