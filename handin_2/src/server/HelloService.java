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
                return SOAPServices.GetAttendantTasks(attendantID);
            case 2:
                return "F U BITCH!";
            case 3: 
                return "Morten er en skildpade\n Og dybt forelsket en i havmåge... (som hedder Gertrud)";
        }
        
        return "";
    }
    
    @WebMethod()
    public void CreateTask(String taskXML, int serviceOption) {
        
    }
    
    @WebMethod()
    public void DeleteTask(String taskID, int serviceOption) {
        
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