/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package server;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

/**
 *
 * @author Mr.Green
 */
@XmlRootElement(name = "task")
public class Task{
    
    @XmlAttribute(name="id")
    private String id;    
    @XmlAttribute(name="status")
    private String status;    
    @XmlAttribute(name="date")    
    private String date;    
    @XmlAttribute(name="name")
    private String name;    
    @XmlAttribute(name="required")
    private String required;
    @XmlElement(name="description")
    private String description;    
    @XmlElement(name="attendants")
    public String attendants;
    @XmlElement(name="conditions")
    private String conditions;
    @XmlElement(name="responses")
    private String responses;
    @XmlElement(name="role")
    private String role;

    public Task(){}
    
    public Task(String id, String status, String date, String name, String description){
        this.id = id;
        this.status = status;
        this.date = date;
        this.name = name;
        this.description = description;
    }
    public String getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getDescription(){
        return description;
    }    
    public void setStatus(String status){
        this.status = status;
    }

    public String getStat() {
	return status;
    }

    public void setRequired(boolean r) {
	if (r){
		required = "true";
	} else {
		required = "false";
	}
    }	

    public String getRequired() {
	if (required.equals("true")){
		//return true;
	}	
	//return false;
	return required;
    }

    public String[]  getResponses() {
	if (responses.equals("")) {
		return new String[0];
	}

	String[] ret = responses.split(",");
	for (int i=0; i<ret.length; i++) {
		ret[i] = ret[i].trim();
	}
	return ret;
    }

    public String[]  getConditions() {
	if (conditions.equals("")) {
		return new String[0];		
	}

	String[] ret = conditions.split(",");
	for (int i=0; i<ret.length; i++) {
		ret[i] = ret[i].trim();
	}
	return ret;
    }

    public String getRole(){
	return role;
    }
   // public void setAttendant(Attendant attendant){
   //     this.attendants = attendant;
   // }
}
