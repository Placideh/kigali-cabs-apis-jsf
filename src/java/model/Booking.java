/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author DolphiX People'S
 */
public class Booking {
    private String bookId;
    private String carOwner;
    private String plateNo;
    private String location;
    private Date bookingTime;

    public Booking() {
    }

    public Booking(String bookId, String carOwner, String plateNo, String location, Date bookingTime) {
	this.bookId = bookId;
	this.carOwner = carOwner;
	this.plateNo = plateNo;
	this.location = location;
	this.bookingTime = bookingTime;
    }

    public String getBookId() {
	return bookId;
    }

    public void setBookId(String bookId) {
	this.bookId = bookId;
    }

    public String getCarOwner() {
	return carOwner;
    }

    public void setCarOwner(String carOwner) {
	this.carOwner = carOwner;
    }

    public String getPlateNo() {
	return plateNo;
    }

    public void setPlateNo(String plateNo) {
	this.plateNo = plateNo;
    }

    public String getLocation() {
	return location;
    }

    public void setLocation(String location) {
	this.location = location;
    }

    public Date getBookingTime() {
	return bookingTime;
    }

    public void setBookingTime(Date bookingTime) {
	this.bookingTime = bookingTime;
    }

    @Override
    public String toString() {
	return "Booking{" + "bookId=" + bookId + ", carOwner=" + carOwner + ", plateNo=" + plateNo + ", location=" + location + ", bookingTime=" + bookingTime + '}';
    }
    
}
