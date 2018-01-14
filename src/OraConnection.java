import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
@WebServlet("/OraConnection")
public class OraConnection extends HttpServlet {
       public static Connection con=null;
       private OraConnection(){
    	   
       }
       public static Connection getConnection(){
    	   try{
    		   Class.forName("oracle.jdbc.driver.OracleDriver");
    		   con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root");
    	   }
    	   catch(Exception e){e.printStackTrace();}
    	   return con;
       }
   }

	   
