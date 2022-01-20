/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backingBean;

import java.io.Serializable;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import model.Booking;
import model.Car;
import model.User;

/**
 *
 * @author DolphiX People'S
 */
@ManagedBean
public class CarBB implements Serializable {
    
    private Car car;
    private User user;
    private String bookingId;
    private Booking booking;
    private String parkIt;
    private UIComponent myForm;
    
    public CarBB() {
	car=new Car();
	user=new User();
	booking=new Booking();
    }

    public Car getCar() {
	return car;
    }

    public void setCar(Car car) {
	this.car = car;
    }

    public User getUser() {
	return user;
    }

    public void setUser(User user) {
	this.user = user;
    }

    public String getBookingId() {
	return bookingId;
    }

    public void setBookingId(String bookingId) {
	this.bookingId = bookingId;
    }

    public Booking getBooking() {
	return booking;
    }

    public void setBooking(Booking booking) {
	this.booking = booking;
    }

    public String getParkIt() {
	return parkIt;
    }

    public void setParkIt(String parkIt) {
	this.parkIt = parkIt;
    }
    
    
    
     public UIComponent getMyForm() {
	return myForm;
    }

    public void setMyForm(UIComponent myForm) {
	this.myForm = myForm;
    }
    public String registerBooking(){
	 Booking bookings=new Booking();
	bookingId=randomString();
	 
	bookings.setBookId(bookingId);
	bookings.setBookingTime(new Date());
	bookings.setPlateNo(booking.getPlateNo());
	bookings.setCarOwner(booking.getCarOwner());
	bookings.setLocation(booking.getLocation());
	ClientBuilder.newClient()
		.target("http://localhost:8080/kigalicabsapi/api/booking")
		.request().post(Entity.json(bookings));
	return "register";
    }
    private static String randomString() {

	String characters = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	String str = "";
	char[] mynewCharacters = characters.toCharArray();
	Integer generatedCodeLength = 10;
	for (int i = 0; i < generatedCodeLength; i++) {
	    int index = (int) (Math.random() * 61);
	    String newString = characters.substring(index, characters.length() - 1);
	    str += mynewCharacters[newString.length()];
	}
	return str;
    }
    public String getAdminView(){
	FacesContext faceContext = FacesContext.getCurrentInstance();
	try {
	    //getting booking details from bookingId returned to the view
	     booking=ClientBuilder.newClient()
		.target("http://localhost:8080/kigalicabsapi/api/booking/"+parkIt)
		.request().get(Booking.class);
	     System.out.println(booking);
	     if(booking!=null){
		//getting username from view and return whole user
		 user=ClientBuilder.newClient()
		    .target("http://localhost:8080/kigalicabsapi/api/users/"+booking.getCarOwner())
		    .request().get(User.class);
		 System.out.println("getting a user:"+user);
		 if(user!=null){
		     Double payment=200.0;
		     //setting car properties and persist to the Database via Api
		    car.setUser(user);
		    car.setPlateNo(booking.getPlateNo());
		    car.setLocation(booking.getLocation());
		    car.setPayment(payment);
		    car.setBookingId(booking.getBookId());
		    car.setBookedTime(booking.getBookingTime());
		    car.setStartTime(new Date());
		    car.setEndTime(new Date());
		    car.setStatus("carParkedIn");
		    ClientBuilder.newClient()
			.target("http://localhost:8080/kigalicabsapi/api/cars")
			.request().post(Entity.json(car));
		     System.out.println(car);
		    return "admin";
		 }
		 
	     }
	     FacesContext facesContext = FacesContext.getCurrentInstance();
	    facesContext.addMessage("myForm"+":bookingId", new FacesMessage("Invalid Booking Token"));
	     return "park";
	     
	} catch (Exception ex) {
	    faceContext.addMessage("myForm:"+"bookingId", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid Booking Token", ex.getMessage()));
	    return "index";
	}
    }
   
    public String redirectToEdit(){
	return "edit";
    }
    public String updateCar(){
	 //getting username from view and return whole user
	     user=ClientBuilder.newClient()
		.target("http://localhost:8080/kigalicabsapi/api/users/"+user.getUsername())
		.request().get(User.class);
	     
	    //getting booking details from bookingId returned to the view
	     booking=ClientBuilder.newClient()
		.target("http://localhost:8080/kigalicabsapi/api/booking/"+parkIt)
		.request().get(Booking.class);
	     
	     //setting car properties and persist to the Database via Api
	    car.setUser(user);
	    car.setBookedTime(booking.getBookingTime() );
	    car.setStartTime(new Date());
	    car.setEndTime(new Date());
	    car.setStatus("carParkedIn");
	    ClientBuilder.newClient()
		.target("http://localhost:8080/kigalicabsapi/api/cars")
		.request().post(Entity.json(car));
	return "admin";
    }
   
    public String removeCar(){
	ClientBuilder.newClient()
		.target("http://localhost:8080/kigalicabsapi/api/cars/"+car.getPlateNo())
		.request().delete();
	return "admin";
    }
    public String parkCarFromBooking(){
	
	return "park";
    }
   
   
    
}
