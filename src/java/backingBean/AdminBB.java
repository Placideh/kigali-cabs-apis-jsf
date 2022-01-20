/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backingBean;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import model.Car;

/**
 *
 * @author DolphiX People'S
 */
@ManagedBean
public class AdminBB implements Serializable {
    Car car;
    private String plateNo;
    
    List<Car>cars;
    public AdminBB() {
	car=new Car();
	cars=ClientBuilder.newClient()
		.target("http://localhost:8080/kigalicabsapi/api/cars")
		.request().get(new GenericType<List<Car>>(){});
	
    }

    public Car getCar() {
	return car;
    }

    public void setCar(Car car) {
	this.car = car;
    }

    public String getPlateNo() {
	return plateNo;
    }

    public void setPlateNo(String plateNo) {
	this.plateNo = plateNo;
    }

    public List<Car> getCars() {
	return cars;
    }

    public void setCars(List<Car> cars) {
	this.cars = cars;
    }
    
    
    public Collection<Car> listAllCars(){
	return ClientBuilder.newClient()
		.target("http://localhost:8080/kigalicabsapi/api/cars")
		.request().get(new GenericType<List<Car>>(){});
    }
    public String updateCarOrUser(){
	ClientBuilder.newClient()
		.target("http://localhost:8080/kigalicabsapi/api/cars")
		.request().put(Entity.json(car));
	
	return "admin";
    }
    
    public String removeCar(){
	ClientBuilder.newClient()
		.target("http://localhost:8080/kigalicabsapi/api/cars/"+car.getPlateNo())
		.request().delete();
	return "admin";
    }
     public String getPay(){
	ClientBuilder.newClient()
		.target("http://localhost:8080/kigalicabsapi/api/cars/pay")
		.request().put(Entity.json(car));
	return "admin";
    }
    public String userPayment(){
	/*
	    here when a user paid his/her status will be parked In so 
	that we may allow him again to park again which means that 
	in the database duplication is allowed 
	it is where many logical business will be taken cared of 
	*/
	ClientBuilder.newClient()
		.target("http://localhost:8080/kigalicabsapi/api/cars/pay")
		.request().put(Entity.json(car));
	
	return "admin";
    }
   public String searchByPlateno(){
       ClientBuilder.newClient()
		.target("http://localhost:8080/kigalicabsapi/api/cars/pay")
		.request().put(Entity.json(car));
       return "admin";
   }
   public String search(){
       plateNo = plateNo.toUpperCase();
       if (plateNo.matches("[A-Z0-9]*")) {

	   cars = cars.stream().filter(x -> {
	       x = x;
	       return x.getPlateNo().startsWith(plateNo);
	   })
		   .collect(Collectors.toList());

       }
       return null;
   }
   
	
  
}
