/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backingBean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import model.User;

/**
 *
 * @author DolphiX People'S
 */
@ManagedBean
public class UserBB implements Serializable {
    private User user;
    private UIComponent myForm;

    public UserBB() {
	user=new User();
    }
    
    public User getUser() {
	return user;
    }

    public void setUser(User user) {
	this.user = user;
    }

    public UIComponent getMyForm() {
	return myForm;
    }

    public void setMyForm(UIComponent myForm) {
	this.myForm = myForm;
    }
    
    public String registerUser(){
	FacesContext faceContext=FacesContext.getCurrentInstance();
	try{
//	    if(isUserValid()){
		ClientBuilder.newClient()
		.target("http://localhost:8080/kigalicabsapi/api/users/user")
		.request().post(Entity.json(user));
//	    }
	    
		
	    
	    return "login";
	}catch(Exception ex){
	    faceContext.addMessage("myForm",new FacesMessage(FacesMessage.SEVERITY_ERROR,"user not registered",ex.getMessage()));
	    return "index";
	}
    }
    public String home(){
	return "login";
    }
    
    public String login(){
	ClientBuilder.newClient()
		.target("http://localhost:8080/kigalicabsapi/api/'" + user.getUsername() + "'/'" + user.getPassword() + "'")
		.request().post(Entity.json(user));
	return "register";
    }
     public String signUp(){
	
	return "index";
    }
    private Boolean isUserValid(){
	 Integer atSign=user.getEmail().indexOf("@");
	Integer emailLength=user.getEmail().length();
	String textAfterAtSign=user.getEmail().substring(atSign+1,emailLength);
	Integer dotSign=textAfterAtSign.indexOf(".");
	Integer total=atSign+dotSign;
	Boolean result=(total-atSign)<2;
	String textAfterDotSign=user.getEmail().substring(dotSign+1,emailLength);
	Boolean afterDotSignCheck=textAfterDotSign.length()<2;
	Map<String,String> errors=new HashMap<>();
	if(user.getUsername().trim().isEmpty()){
	    errors.put("username", "username must be filled");
	}
	if(user.getEmail().trim().toLowerCase().isEmpty()){
	    errors.put("email", "Email must be filled");
	}else {
	    if(!user.getEmail().contains(".")||!user.getEmail().contains("@"))
		errors.put("email","Email must contains @ and . sign");
	    if(result)
		errors.put("email","Email must contain dot after @ sign ");
	    
	    if(afterDotSignCheck)
		errors.put("email", "Email must contain a top level domain");
	}
	if(!user.getPassword().trim().isEmpty()&&!user.getCpassword().trim().isEmpty()){
	    if(!user.getPassword().equalsIgnoreCase(user.getCpassword()))
		errors.put("password", "password must be the same");
	}
	if(!user.getPassword().trim().isEmpty()&&!user.getCpassword().trim().isEmpty()){
	    if(!user.getPassword().equalsIgnoreCase(user.getCpassword()))
		errors.put("cpassword", "password must be the same");
	    
	}
	if(errors.isEmpty()){
	    return true;
	}
	FacesContext facesContext=FacesContext.getCurrentInstance();
	errors.forEach((k,v)->facesContext.addMessage("myForm:"+k, new FacesMessage(v)));
	throw new IllegalArgumentException("one or more fields contains errors");
    }
}
