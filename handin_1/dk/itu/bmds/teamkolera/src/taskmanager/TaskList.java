package dk.itu.bmds.teamkolera.src.taskmanager;

import java.util.List;
import java.util.LinkedList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "tasks")
public class TaskList{
        
    @XmlElementWrapper(name = "tasks")
    @XmlElement(name = "task")
    public List<Task> tasks;

} 
