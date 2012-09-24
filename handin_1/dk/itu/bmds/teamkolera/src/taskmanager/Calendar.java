/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.itu.bmds.teamkolera.src.taskmanager;

import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;


/**
 *
 * @author Mr.Green
 */
@XmlRootElement(name = "cal")
public class Calendar{
        
    @XmlElementWrapper(name = "users")
    @XmlElement(name = "user")
    public List<User> users;

    @XmlElementWrapper(name = "tasks")
    @XmlElement(name = "task")
    public List<Task> tasks;
}
