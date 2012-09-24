package server;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;


@WebService(name="helloService", targetNamespace = "http://smds-e2012.itu.dk/webservices/basicsample",serviceName="helloService")
public class HelloService 
{
    @WebMethod()
    public String helloOperation(String name) {
            return "Hello " + name + "!";
    }
    
    @WebMethod()
    public String GetAttendantTasks(String attendantID, int serviceOption) {
        switch(serviceOption) {
            case 1:
                System.out.println("SOAP!");
                return SOAPServices.getAttendantTasks(attendantID);
            case 2:
                return RESTServices.DoRestCall("http://trustcare.itu.dk/task-manager-rest/tasks/links/attendant/" + attendantID, "GET", "");
            case 3: 
                return SOAPServices.getAttendantTasks(attendantID) 
                       + "\n" 
                       + RESTServices.DoRestCall("http://trustcare.itu.dk/task-manager-rest/tasks/links/attendant/" + attendantID, "GET", "");
        }
        
        return "";
    }
    
    @WebMethod()
    public void CreateTask(String taskXML, int serviceOption) {
        switch(serviceOption) {
            case 1:
                System.out.println("SOAP!");
                SOAPServices.createTask(taskXML);
                break;
            case 2:
                RESTServices.DoRestCall("http://trustcare.itu.dk/task-manager-rest/tasks/links/createtask", "POST", taskXML);
                break;
            case 3: 
                SOAPServices.createTask(taskXML);
                RESTServices.DoRestCall("http://trustcare.itu.dk/task-manager-rest/tasks/links/createtask", "POST", taskXML);
                break;
        }
    }
    
    @WebMethod()
    public void DeleteTask(String taskID, int serviceOption) {
        switch(serviceOption) {
            case 1:
                System.out.println("SOAP!");
                SOAPServices.deleteTask(taskID);
                break;
            case 2:
                RESTServices.DoRestCall("http://trustcare.itu.dk/task-manager-rest/tasks/DeleteTask?taskId=" + taskID, "DELETE", "");
                break;
            case 3: 
                SOAPServices.deleteTask(taskID);
                RESTServices.DoRestCall("http://trustcare.itu.dk/task-manager-rest/tasks/DeleteTask?taskId=" + taskID, "DELETE", "");
                break;
        }
    }
    /**
    * Publish the service end point.
    * @param args not used.
    */
    public static void main(String[] args) 
    {
            Endpoint.publish("http://localhost:8085/handin_2/", new HelloService());
    }
}