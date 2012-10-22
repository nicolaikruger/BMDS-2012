/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.List;
import java.util.LinkedList;
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
    private List<User> users;

    @XmlElementWrapper(name = "tasks")
    @XmlElement(name = "task")
    private List<Task> tasks;

    public boolean hasTask(Task inT) {
	if (tasks == null){
		tasks = new LinkedList<Task>();
		return false;
	}
	for (Task t : tasks) {
		if (t.getId() == inT.getId()){
			return true;
		}
	}
	return false;
    }

    //currently accepts invalid users
    public boolean addTask(Task t) {
	if (hasTask(t)) {
		return false;
	}
	tasks.add(t);
	return true;
    }

    public boolean hasUser(User inU) {
	for(User u : users) {
		if(u.getId() == inU.getId()) {
			return true;
		}
	}
		return false;
    }

    public TaskList userSched(User u) {
	List<Task> retList = new LinkedList<Task>();
	for(Task t : tasks){
		if (t.attendants != null && 
				t.attendants.contains(u.getName())) { 
			retList.add(t);
		}
	}
	TaskList ret = new TaskList();
	ret.tasks = retList;
	return ret;
    }

    public boolean del(Task inT) {
	for(Task t : tasks) {
		if (inT.getId().equals(t.getId())){ //inefficient and ugly
			tasks.remove(t);
			return true;
		}
	}
	return false;
    }

    public String replace(Task t) {
	boolean hasT = del(t);
	tasks.add(t);
	//String ret = hasT ? "task replaced" : "task added";
	return hasT ? "task replaced" : "task added";
    }

    public List<Task> getList(){
	List<Task> ret = new LinkedList<Task>();
	for(Task t : tasks){
		ret.add(t);
	}
	return ret;
    }
}
