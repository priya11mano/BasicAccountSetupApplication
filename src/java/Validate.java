
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author HOME PC
 */
public class Validate 
{
    public static boolean checkUser(String name , String email , String pwd) throws SQLException
    {
        boolean st = false ;
        try
        {
            try {
                Class.forName("org.apache.derby.jdbc.ClientDriver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(register.class.getName()).log(Level.SEVERE, null, ex);
            }

            String URL ="jdbc:derby://localhost:1527/RegistrationDetails";
        
             //Establishing Connection with the database
            Connection con;
            con = DriverManager.getConnection(URL);
        
            String cmd =" select * from REGISTERTABLE where USERNAME=? AND EMAIL=? AND PASSWORD=? ";
            PreparedStatement stmt = con.prepareStatement(cmd);
        
            stmt.setString(1,name);
            stmt.setString(2,email);
            stmt.setString(3,pwd);
        
            ResultSet rs = stmt.executeQuery();
            st = rs.next();
        }
        catch(Exception ex)
        {
            Logger.getLogger(PasswordChangingServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return st;
    }
}
