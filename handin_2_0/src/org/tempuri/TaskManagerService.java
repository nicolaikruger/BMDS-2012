
package org.tempuri;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6-1b01 
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "TaskManagerService", targetNamespace = "http://tempuri.org/", wsdlLocation = "http://trustcare.itu.dk/task-manager-soap/TaskManagerService.svc?wsdl")
public class TaskManagerService
    extends Service
{

    private final static URL TASKMANAGERSERVICE_WSDL_LOCATION;
    private final static WebServiceException TASKMANAGERSERVICE_EXCEPTION;
    private final static QName TASKMANAGERSERVICE_QNAME = new QName("http://tempuri.org/", "TaskManagerService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://trustcare.itu.dk/task-manager-soap/TaskManagerService.svc?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        TASKMANAGERSERVICE_WSDL_LOCATION = url;
        TASKMANAGERSERVICE_EXCEPTION = e;
    }

    public TaskManagerService() {
        super(__getWsdlLocation(), TASKMANAGERSERVICE_QNAME);
    }

    public TaskManagerService(WebServiceFeature... features) {
        super(__getWsdlLocation(), TASKMANAGERSERVICE_QNAME, features);
    }

    public TaskManagerService(URL wsdlLocation) {
        super(wsdlLocation, TASKMANAGERSERVICE_QNAME);
    }

    public TaskManagerService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, TASKMANAGERSERVICE_QNAME, features);
    }

    public TaskManagerService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public TaskManagerService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns ITaskManagerService
     */
    @WebEndpoint(name = "BasicHttpBinding_ITaskManagerService")
    public ITaskManagerService getBasicHttpBindingITaskManagerService() {
        return super.getPort(new QName("http://tempuri.org/", "BasicHttpBinding_ITaskManagerService"), ITaskManagerService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ITaskManagerService
     */
    @WebEndpoint(name = "BasicHttpBinding_ITaskManagerService")
    public ITaskManagerService getBasicHttpBindingITaskManagerService(WebServiceFeature... features) {
        return super.getPort(new QName("http://tempuri.org/", "BasicHttpBinding_ITaskManagerService"), ITaskManagerService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (TASKMANAGERSERVICE_EXCEPTION!= null) {
            throw TASKMANAGERSERVICE_EXCEPTION;
        }
        return TASKMANAGERSERVICE_WSDL_LOCATION;
    }

}
