/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * 10-09-2012
 * @author Mr.Green
 */
@XmlRootElement(name = "user")
public class User{
    @XmlAttribute(name = "id")
    private String id;
    
    @XmlElement(name="name")
    private String name;
    
    @XmlElement(name="password")
    private String password;
    
    public User(){}
    
    public String getId() {
	return id;
    }

    @Override
    public String toString() {
	return id + ", " + name + ", " + password;
    }

    public String getName(){
	return name;
    }

    public User(String id, String name, String password){
        this.id = id;
        this.name = name;
        this.password = password;
    }
}
