/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.itu.bmds.teamkolera.src.taskmanager;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

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
    @XmlElement(name="description")
    private String description;    
    @XmlElement(name="attendant")
    public Attendant attendant;  
    
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
    public void setAttendant(Attendant attendant){
        this.attendant = attendant;
    }
}
