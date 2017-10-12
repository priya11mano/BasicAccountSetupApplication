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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author HOME PC
 */
public class DeleteServlet extends HttpServlet 
{
    /**
     * Handles the HTTP <code>POST</code> method.
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
        
        String user_name = request.getParameter("User_name");
        String email_id = request.getParameter("Email_ID");
        
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
            
            String cmd =" delete from REGISTERTABLE where USERNAME=? AND EMAILID=? ";
            PreparedStatement stmt = con.prepareStatement(cmd);
            
            stmt.setString(1,user_name);
            stmt.setString(2,email_id);
            
            int i = stmt.executeUpdate();
            if(i > 0)
            {
                RequestDispatcher rd;                                                                                                                                                       
                rd = request.getRequestDispatcher("Login.jsp");
                rd.forward(request,response);
            }
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(DeleteServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

    
