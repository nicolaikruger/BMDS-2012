package TokenService;

import java.util.Date;
import java.util.Hashtable;
import javax.naming.*;
import javax.naming.directory.*;
import lib.Connection;
import lib.Encryption;
        
public class TokenService {
    private Hashtable ht;
    private Encryption e;
    
    public TokenService(){
        ht = new Hashtable(11);
        ht.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
	ht.put("com.sun.jndi.ldap.connect.timeout", "1000");
        ht.put(Context.PROVIDER_URL, "ldap://ldap2.itu.dk:389/");
        ht.put(Context.SECURITY_AUTHENTICATION, "simple"); 
        
	e= new Encryption();

        run();
    }
    
    private void run() {
        Connection con = new Connection(7777);
        while(true) {
            String recieveMsg = con.receive();
            String[] recieveInfo = recieveMsg.split(",");
            String username = recieveInfo[0];
            String password = recieveInfo[1];
            
            String response = getMsg(username, password);
            con.respond(recieveMsg, response);
        }
    }
    
    private boolean login(String userId, String password){
        ht.put(Context.SECURITY_PRINCIPAL, "uid=" + userId + ",ou=People,dc=itu,dc=dk");
        
        ht.put(Context.SECURITY_CREDENTIALS, password);
        
        try{
            DirContext dcx = new InitialDirContext(ht);
            
            dcx.close();
            
            return true;
        }
        catch(NamingException e)
        {
            return false;
        }
    }
    
    private String getMsg(String userId, String pass) {
	KeyChain kc = new KeyChain();
	String key = kc.getKey(userId);
        String password = e.decrypt(key, pass); 
        String returnMsg = "Error: Could not authenticate!";
        if(login(userId, password)) {
            String role = RoleFactory.getRole(userId);
            long timeStamp = System.currentTimeMillis() + (300000); // 5 min.
            returnMsg = e.encrypt(key, e.encrypt(kc.getKey("server"), role + "," + timeStamp)); 
        }
        return returnMsg;
    }
    
    public static void main(String[] args)
    {
        TokenService ts = new TokenService();
        System.out.println(ts.login("", ""));
    }
}
