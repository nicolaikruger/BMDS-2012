/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

/**
 *
 * @author Nicolai
 */
public class SOAPServices {

    public static String getAttendantTasks(java.lang.String attendantId) {
        org.tempuri.TaskManagerService service = new org.tempuri.TaskManagerService();
        org.tempuri.ITaskManagerService port = service.getBasicHttpBindingITaskManagerService();
        return port.getAttendantTasks(attendantId);
    }

    public static String createTask(java.lang.String taskXml) {
        org.tempuri.TaskManagerService service = new org.tempuri.TaskManagerService();
        org.tempuri.ITaskManagerService port = service.getBasicHttpBindingITaskManagerService();
        return port.createTask(taskXml);
    }

    public static String deleteTask(java.lang.String taskId) {
        org.tempuri.TaskManagerService service = new org.tempuri.TaskManagerService();
        org.tempuri.ITaskManagerService port = service.getBasicHttpBindingITaskManagerService();
        return port.deleteTask(taskId);
    }
    
    
}
