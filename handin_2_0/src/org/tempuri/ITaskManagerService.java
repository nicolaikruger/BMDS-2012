
package org.tempuri;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6-1b01 
 * Generated source version: 2.2
 * 
 */
@WebService(name = "ITaskManagerService", targetNamespace = "http://itu.dk/smds-e2012/lab/week-04/")
@XmlSeeAlso({
    com.microsoft.schemas._2003._10.serialization.ObjectFactory.class,
    dk.itu.smds_e2012.lab.week_04.ObjectFactory.class
})
public interface ITaskManagerService {


    /**
     * 
     * @return
     *     returns java.lang.String
     */
    @WebMethod(operationName = "GetAllTasks", action = "http://itu.dk/smds-e2012/lab/week-04/ITaskManagerService/GetAllTasks")
    @WebResult(name = "GetAllTasksResult", targetNamespace = "http://itu.dk/smds-e2012/lab/week-04/")
    @RequestWrapper(localName = "GetAllTasks", targetNamespace = "http://itu.dk/smds-e2012/lab/week-04/", className = "dk.itu.smds_e2012.lab.week_04.GetAllTasks")
    @ResponseWrapper(localName = "GetAllTasksResponse", targetNamespace = "http://itu.dk/smds-e2012/lab/week-04/", className = "dk.itu.smds_e2012.lab.week_04.GetAllTasksResponse")
    public String getAllTasks();

    /**
     * 
     * @param attendantId
     * @return
     *     returns java.lang.String
     */
    @WebMethod(operationName = "GetAttendantTasks", action = "http://itu.dk/smds-e2012/lab/week-04/ITaskManagerService/GetAttendantTasks")
    @WebResult(name = "GetAttendantTasksResult", targetNamespace = "http://itu.dk/smds-e2012/lab/week-04/")
    @RequestWrapper(localName = "GetAttendantTasks", targetNamespace = "http://itu.dk/smds-e2012/lab/week-04/", className = "dk.itu.smds_e2012.lab.week_04.GetAttendantTasks")
    @ResponseWrapper(localName = "GetAttendantTasksResponse", targetNamespace = "http://itu.dk/smds-e2012/lab/week-04/", className = "dk.itu.smds_e2012.lab.week_04.GetAttendantTasksResponse")
    public String getAttendantTasks(
        @WebParam(name = "attendantId", targetNamespace = "http://itu.dk/smds-e2012/lab/week-04/")
        String attendantId);

    /**
     * 
     * @param taskId
     * @return
     *     returns java.lang.String
     */
    @WebMethod(operationName = "GetTask", action = "http://itu.dk/smds-e2012/lab/week-04/ITaskManagerService/GetTask")
    @WebResult(name = "GetTaskResult", targetNamespace = "http://itu.dk/smds-e2012/lab/week-04/")
    @RequestWrapper(localName = "GetTask", targetNamespace = "http://itu.dk/smds-e2012/lab/week-04/", className = "dk.itu.smds_e2012.lab.week_04.GetTask")
    @ResponseWrapper(localName = "GetTaskResponse", targetNamespace = "http://itu.dk/smds-e2012/lab/week-04/", className = "dk.itu.smds_e2012.lab.week_04.GetTaskResponse")
    public String getTask(
        @WebParam(name = "taskId", targetNamespace = "http://itu.dk/smds-e2012/lab/week-04/")
        String taskId);

    /**
     * 
     * @param taskXml
     * @return
     *     returns java.lang.String
     */
    @WebMethod(operationName = "CreateTask", action = "http://itu.dk/smds-e2012/lab/week-04/ITaskManagerService/CreateTask")
    @WebResult(name = "CreateTaskResult", targetNamespace = "http://itu.dk/smds-e2012/lab/week-04/")
    @RequestWrapper(localName = "CreateTask", targetNamespace = "http://itu.dk/smds-e2012/lab/week-04/", className = "dk.itu.smds_e2012.lab.week_04.CreateTask")
    @ResponseWrapper(localName = "CreateTaskResponse", targetNamespace = "http://itu.dk/smds-e2012/lab/week-04/", className = "dk.itu.smds_e2012.lab.week_04.CreateTaskResponse")
    public String createTask(
        @WebParam(name = "taskXml", targetNamespace = "http://itu.dk/smds-e2012/lab/week-04/")
        String taskXml);

    /**
     * 
     * @param taskId
     * @return
     *     returns java.lang.String
     */
    @WebMethod(operationName = "DeleteTask", action = "http://itu.dk/smds-e2012/lab/week-04/ITaskManagerService/DeleteTask")
    @WebResult(name = "DeleteTaskResult", targetNamespace = "http://itu.dk/smds-e2012/lab/week-04/")
    @RequestWrapper(localName = "DeleteTask", targetNamespace = "http://itu.dk/smds-e2012/lab/week-04/", className = "dk.itu.smds_e2012.lab.week_04.DeleteTask")
    @ResponseWrapper(localName = "DeleteTaskResponse", targetNamespace = "http://itu.dk/smds-e2012/lab/week-04/", className = "dk.itu.smds_e2012.lab.week_04.DeleteTaskResponse")
    public String deleteTask(
        @WebParam(name = "taskId", targetNamespace = "http://itu.dk/smds-e2012/lab/week-04/")
        String taskId);

}