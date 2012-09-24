/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import common.Utils;
import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.Name;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;

/**
 *
 * @author Nicolai
 */
public class SOAPServices {
    
    private static QName service;
    private static QName port;
    private static String endpointAddress;
    private static Service webservice;
    
    private static boolean isInitiated = false;    
        
    private static void init() {
        service             = new QName("http://tempuri.org/", "TaskManagerService");
        port                = new QName("http://tempuri.org/", "BasicHttpBinding_ITaskManagerService");
        endpointAddress     = "http://trustcare.itu.dk/task-manager-soap/TaskManagerService.svc";
        webservice          = Service.create(service);
        webservice.addPort(port, SOAPBinding.SOAP11HTTP_BINDING, endpointAddress);
        
        isInitiated = true;
    }
    
    public static String GetAttendantTasks(String attendantID)
    {
        if(!isInitiated) { init(); }
        return invokeMethod("GetAttendantTasks", attendantID);
    }
    
    private static String invokeMethod(String method, String arg)
    {
        try {
            MessageFactory factory =
                    MessageFactory.newInstance(SOAPConstants.SOAP_1_1_PROTOCOL);
            SOAPMessage request = factory.createMessage();
            SOAPPart soap = request.getSOAPPart();
            SOAPEnvelope envelope = soap.getEnvelope();
            SOAPBody body = envelope.getBody();
            SOAPElement content = body.addBodyElement(
                    new QName("http://tempuri.org/",
                    method,
                    "kolera"));
            
            /*SOAPHeader head = envelope.getHeader();
            
            String x = "http://tempuri.org/";  
            Name headername = envelope.createName("http://itu.dk/smds-e2012/lab/week-04/ITaskManagerService/GetAttendantTasks");  
            SOAPHeaderElement soapaction = head.addHeaderElement(new QName("http://tempuri.org/",
                    method,
                    "kolera")); 
            soapaction.addTextNode(x); 
            
                    
            System.out.println("Header");
            System.out.println(head);
            System.out.println("");
            
            */
            SOAPElement name;

            name = content.addChildElement("arg0");
            name.setTextContent(arg);
            

            Utils.print(request);

            Dispatch<SOAPMessage> dispatch =
                    webservice.createDispatch(port, SOAPMessage.class,
                    Service.Mode.MESSAGE);
            SOAPMessage response = dispatch.invoke(request);
            String text = response.getSOAPBody().getTextContent();

            System.out.println("Result: " + text);
            return text;
            
            //Utils.print(response);

            //System.out.println(text);

        } catch (SOAPException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return "";
    }
    
}
