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
public class Car {
    private Integer carId;
    private User user;
    private String plateNo;
    private String location;
    private String bookingId;
    private Date bookedTime;
    private Date startTime;
    private Date endTime;
    private String status;
    private Double payment;

    public Car() {
    }

    public Car(Integer carId, User user, String plateNo, String location, String bookingId, Date bookedTime, Date startTime, Date endTime, String status, Double payment) {
	this.carId = carId;
	this.user = user;
	this.plateNo = plateNo;
	this.location = location;
	this.bookingId = bookingId;
	this.bookedTime = bookedTime;
	this.startTime = startTime;
	this.endTime = endTime;
	this.status = status;
	this.payment = payment;
    }

    public Car(User user, String plateNo, String location, String bookingId, Date bookedTime, Date startTime, Date endTime, String status, Double payment) {
	this.user = user;
	this.plateNo = plateNo;
	this.location = location;
	this.bookingId = bookingId;
	this.bookedTime = bookedTime;
	this.startTime = startTime;
	this.endTime = endTime;
	this.status = status;
	this.payment = payment;
    }

    public Integer getCarId() {
	return carId;
    }

    public void setCarId(Integer carId) {
	this.carId = carId;
    }

    public User getUser() {
	return user;
    }

    public void setUser(User user) {
	this.user = user;
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

    public String getBookingId() {
	return bookingId;
    }

    public void setBookingId(String bookingId) {
	this.bookingId = bookingId;
    }

    public Date getBookedTime() {
	return bookedTime;
    }

    public void setBookedTime(Date bookedTime) {
	this.bookedTime = bookedTime;
    }

    public Date getStartTime() {
	return startTime;
    }

    public void setStartTime(Date startTime) {
	this.startTime = startTime;
    }

    public Date getEndTime() {
	return endTime;
    }

    public void setEndTime(Date endTime) {
	this.endTime = endTime;
    }

    public String getStatus() {
	return status;
    }

    public void setStatus(String status) {
	this.status = status;
    }

    public Double getPayment() {
	return payment;
    }

    public void setPayment(Double payment) {
	this.payment = payment;
    }

    @Override
    public String toString() {
	return "Car{" + "carId=" + carId + ", user=" + user + ", plateNo=" + plateNo + ", location=" + location + ", bookingId=" + bookingId + ", bookedTime=" + bookedTime + ", startTime=" + startTime + ", endTime=" + endTime + ", status=" + status + ", payment=" + payment + '}';
    }

    
   
}
