/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backingBean;

import java.io.Serializable;
import java.util.Date;
import javax.faces.bean.ManagedBean;
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
public class EditBB implements Serializable{
    Car car;
//    User user;
    private String username;
    private String email;
    private String password;

    public EditBB() {
	car=new Car();
//	user=new User();
    }

    public Car getCar() {
	return car;
    }

    public void setCar(Car car) {
	this.car = car;
    }

    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

   
    public String edit(){
	
	return "edit";
    }
    public String getToAdmin(){
	return "admin";
    }
    
    public String editInfo(){
	Car cars=new Car();
	cars=ClientBuilder.newClient()
		.target("http://localhost:8080/kigalicabsapi/api/cars/"+car.getPlateNo())
		.request().get(Car.class);
	
	System.out.println(cars);
	User user2=new User();
	//getting username from view and return whole user
	user2.setUsername(username);
	user2.setEmail(email);
	user2.setPassword(password);
	user2.setCpassword(password);
	System.out.println("new user"+user2);
	cars.setUser(user2);
	System.out.println("new Car:"+cars);
	ClientBuilder.newClient()
		.target("http://localhost:8080/kigalicabsapi/api/users/user")
		.request().put(Entity.json(user2));
	
	ClientBuilder.newClient()
		.target("http://localhost:8080/kigalicabsapi/api/cars")
		.request().put(Entity.json(cars));
	
	return "admin";
    }
    public String cancelEdit(){
	return "admin";
    }
    
    
}
