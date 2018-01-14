package webtest;
import java.sql.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
@WebServlet("/JavaCon")
public class JavaCon extends HttpServlet {
       public static Connection con=null;
       private JavaCon(){
    	   
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

	   
