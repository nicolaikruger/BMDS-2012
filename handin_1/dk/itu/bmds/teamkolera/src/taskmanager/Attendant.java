/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.itu.bmds.teamkolera.src.taskmanager;

import java.util.List;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Mr.Green
 */
@XmlRootElement(name="attendant")
public class Attendant {
    @XmlAttribute(name="ids")
    public String attendants;
}
