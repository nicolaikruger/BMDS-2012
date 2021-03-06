package client;

import common.Utils;
import javax.xml.namespace.QName;
import javax.xml.soap.*;
import javax.xml.ws.Dispatch;
import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;

public class HelloServiceClient {

    public static void main(String[] args) {


        QName service =
                new QName("http://smds-e2012.itu.dk/webservices/basicsample", "helloService");
        QName port =
                new QName("http://smds-e2012.itu.dk/webservices/basicsample", "helloServicePort");
        String endpointAddress = "http://localhost:8085/handin_2/";
        Service webservice = Service.create(service);
        webservice.addPort(port, SOAPBinding.SOAP11HTTP_BINDING, endpointAddress);

        try {
            MessageFactory factory =
                    MessageFactory.newInstance(SOAPConstants.SOAP_1_1_PROTOCOL);
            SOAPMessage request = factory.createMessage();
            SOAPPart soap = request.getSOAPPart();
            SOAPEnvelope envelope = soap.getEnvelope();
            SOAPBody body = envelope.getBody();
            SOAPElement content = body.addBodyElement(
                    new QName("http://smds-e2012.itu.dk/webservices/basicsample",
                    "GetAttendantTasks",
                    "itu"));

            SOAPElement name;

            name = content.addChildElement("arg0");
            name.setTextContent("Rao");
            
            name = content.addChildElement("arg1");
            name.setTextContent("1");


            Utils.print(request);

            Dispatch<SOAPMessage> dispatch =
                    webservice.createDispatch(port, SOAPMessage.class,
                    Service.Mode.MESSAGE);
            SOAPMessage response = dispatch.invoke(request);
            String text = response.getSOAPBody().getTextContent();

            Utils.print(response);

            System.out.println(text);

        } catch (SOAPException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}