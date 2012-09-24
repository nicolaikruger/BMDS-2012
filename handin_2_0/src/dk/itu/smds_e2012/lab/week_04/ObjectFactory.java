
package dk.itu.smds_e2012.lab.week_04;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the dk.itu.smds_e2012.lab.week_04 package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetAllTasksResponseGetAllTasksResult_QNAME = new QName("http://itu.dk/smds-e2012/lab/week-04/", "GetAllTasksResult");
    private final static QName _GetAttendantTasksResponseGetAttendantTasksResult_QNAME = new QName("http://itu.dk/smds-e2012/lab/week-04/", "GetAttendantTasksResult");
    private final static QName _CreateTaskTaskXml_QNAME = new QName("http://itu.dk/smds-e2012/lab/week-04/", "taskXml");
    private final static QName _GetTaskTaskId_QNAME = new QName("http://itu.dk/smds-e2012/lab/week-04/", "taskId");
    private final static QName _DeleteTaskResponseDeleteTaskResult_QNAME = new QName("http://itu.dk/smds-e2012/lab/week-04/", "DeleteTaskResult");
    private final static QName _GetTaskResponseGetTaskResult_QNAME = new QName("http://itu.dk/smds-e2012/lab/week-04/", "GetTaskResult");
    private final static QName _CreateTaskResponseCreateTaskResult_QNAME = new QName("http://itu.dk/smds-e2012/lab/week-04/", "CreateTaskResult");
    private final static QName _GetAttendantTasksAttendantId_QNAME = new QName("http://itu.dk/smds-e2012/lab/week-04/", "attendantId");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: dk.itu.smds_e2012.lab.week_04
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DeleteTaskResponse }
     * 
     */
    public DeleteTaskResponse createDeleteTaskResponse() {
        return new DeleteTaskResponse();
    }

    /**
     * Create an instance of {@link GetAttendantTasksResponse }
     * 
     */
    public GetAttendantTasksResponse createGetAttendantTasksResponse() {
        return new GetAttendantTasksResponse();
    }

    /**
     * Create an instance of {@link CreateTaskResponse }
     * 
     */
    public CreateTaskResponse createCreateTaskResponse() {
        return new CreateTaskResponse();
    }

    /**
     * Create an instance of {@link GetTaskResponse }
     * 
     */
    public GetTaskResponse createGetTaskResponse() {
        return new GetTaskResponse();
    }

    /**
     * Create an instance of {@link GetTask }
     * 
     */
    public GetTask createGetTask() {
        return new GetTask();
    }

    /**
     * Create an instance of {@link GetAttendantTasks }
     * 
     */
    public GetAttendantTasks createGetAttendantTasks() {
        return new GetAttendantTasks();
    }

    /**
     * Create an instance of {@link DeleteTask }
     * 
     */
    public DeleteTask createDeleteTask() {
        return new DeleteTask();
    }

    /**
     * Create an instance of {@link CreateTask }
     * 
     */
    public CreateTask createCreateTask() {
        return new CreateTask();
    }

    /**
     * Create an instance of {@link GetAllTasksResponse }
     * 
     */
    public GetAllTasksResponse createGetAllTasksResponse() {
        return new GetAllTasksResponse();
    }

    /**
     * Create an instance of {@link GetAllTasks }
     * 
     */
    public GetAllTasks createGetAllTasks() {
        return new GetAllTasks();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://itu.dk/smds-e2012/lab/week-04/", name = "GetAllTasksResult", scope = GetAllTasksResponse.class)
    public JAXBElement<String> createGetAllTasksResponseGetAllTasksResult(String value) {
        return new JAXBElement<String>(_GetAllTasksResponseGetAllTasksResult_QNAME, String.class, GetAllTasksResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://itu.dk/smds-e2012/lab/week-04/", name = "GetAttendantTasksResult", scope = GetAttendantTasksResponse.class)
    public JAXBElement<String> createGetAttendantTasksResponseGetAttendantTasksResult(String value) {
        return new JAXBElement<String>(_GetAttendantTasksResponseGetAttendantTasksResult_QNAME, String.class, GetAttendantTasksResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://itu.dk/smds-e2012/lab/week-04/", name = "taskXml", scope = CreateTask.class)
    public JAXBElement<String> createCreateTaskTaskXml(String value) {
        return new JAXBElement<String>(_CreateTaskTaskXml_QNAME, String.class, CreateTask.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://itu.dk/smds-e2012/lab/week-04/", name = "taskId", scope = GetTask.class)
    public JAXBElement<String> createGetTaskTaskId(String value) {
        return new JAXBElement<String>(_GetTaskTaskId_QNAME, String.class, GetTask.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://itu.dk/smds-e2012/lab/week-04/", name = "DeleteTaskResult", scope = DeleteTaskResponse.class)
    public JAXBElement<String> createDeleteTaskResponseDeleteTaskResult(String value) {
        return new JAXBElement<String>(_DeleteTaskResponseDeleteTaskResult_QNAME, String.class, DeleteTaskResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://itu.dk/smds-e2012/lab/week-04/", name = "GetTaskResult", scope = GetTaskResponse.class)
    public JAXBElement<String> createGetTaskResponseGetTaskResult(String value) {
        return new JAXBElement<String>(_GetTaskResponseGetTaskResult_QNAME, String.class, GetTaskResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://itu.dk/smds-e2012/lab/week-04/", name = "CreateTaskResult", scope = CreateTaskResponse.class)
    public JAXBElement<String> createCreateTaskResponseCreateTaskResult(String value) {
        return new JAXBElement<String>(_CreateTaskResponseCreateTaskResult_QNAME, String.class, CreateTaskResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://itu.dk/smds-e2012/lab/week-04/", name = "taskId", scope = DeleteTask.class)
    public JAXBElement<String> createDeleteTaskTaskId(String value) {
        return new JAXBElement<String>(_GetTaskTaskId_QNAME, String.class, DeleteTask.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://itu.dk/smds-e2012/lab/week-04/", name = "attendantId", scope = GetAttendantTasks.class)
    public JAXBElement<String> createGetAttendantTasksAttendantId(String value) {
        return new JAXBElement<String>(_GetAttendantTasksAttendantId_QNAME, String.class, GetAttendantTasks.class, value);
    }

}
