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
    User user;

    public EditBB() {
	car=new Car();
	user=new User();
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
    
    
    
    public String editInfo(){

	car=ClientBuilder.newClient()
		.target("http://localhost:8080/kigalicabsapi/api/cars/edit/" + car.getPlateNo())
		.request().get(Car.class);
	
	
	//getting username from view and return whole user
	User user=new User();
	user = ClientBuilder.newClient()
		.target("http://localhost:8080/kigalicabsapi/api/users/" + car.getUser().getUsername())
		.request().get(User.class);
	user.setUsername(car.getUser().getUsername());
	user.setEmail(car.getUser().getEmail());
	user.setPassword(car.getUser().getPassword());
	user.setCpassword(car.getUser().getPassword());
	car.setUser(user);
	
	ClientBuilder.newClient()
		.target("http://localhost:8080/kigalicabsapi/api/users/user")
		.request().put(Entity.json(user));
	
	ClientBuilder.newClient()
		.target("http://localhost:8080/kigalicabsapi/api/cars")
		.request().put(Entity.json(car));
	
	return "admin";
    }
    public String cancelEdit(){
	return "admin";
    }
    
    
}
