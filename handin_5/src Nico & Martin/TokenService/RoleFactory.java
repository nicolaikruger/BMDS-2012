/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TokenService;

import java.util.HashMap;

public class RoleFactory {
    private static HashMap<String, String> roles;
    
    private static void initilize()
    {
        roles = new HashMap();
        roles.put("hilde", "teacher");
        roles.put("rao", "teacher");
        roles.put("naiz", "ta");
        roles.put("rahr", "ta");
        roles.put("jesan", "ta");
    }
    
    public static String getRole(String userName){
        if(roles == null) initilize();
        
        return roles.containsKey(userName) ? roles.get(userName) : "student";
    }
}