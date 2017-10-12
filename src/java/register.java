/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author HOME PC
 */
public class register extends HttpServlet 
{

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        String URL ="jdbc:derby://localhost:1527/RegistrationDetails";
        
        String first_name = request.getParameter("First_name");
        String last_name = request.getParameter("Last_name");
        String user_name = request.getParameter("User_name");
        String email_id = request.getParameter("Email_ID");
        String phone_num = request.getParameter("Phone_number");
        String addr = request.getParameter("Address");
        String post_code = request.getParameter("Postal_code");
        String pwd = request.getParameter("Password");
        
        PrintWriter out = response.getWriter();
        try 
        {     
            try {
                Class.forName("org.apache.derby.jdbc.ClientDriver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(register.class.getName()).log(Level.SEVERE, null, ex);
            }
            //Establishing Connection with the Database
            Connection con;
            con = DriverManager.getConnection(URL);
            
            String cmd =" insert into REGISTERTABLE values(?,?,?,?,?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement(cmd);
            
            stmt.setString(1,first_name);
            stmt.setString(2,last_name);
            stmt.setString(3,user_name);
            stmt.setString(4,email_id);
            stmt.setString(5,phone_num);
            stmt.setString(6,addr);
            stmt.setString(7,post_code);
            stmt.setString(8,pwd);
            
            int i = stmt.executeUpdate();
            if(i <= 0) 
            {
                out.println("Successfully Registered");
            }
            else
            {
                out.println("Registration unsuccessful");
            }
           
        }
        catch(SQLException ex)
        {
            Logger.getLogger(PasswordChangingServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
