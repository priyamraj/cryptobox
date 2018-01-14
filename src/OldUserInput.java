import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
@WebServlet("/OldUserInput")//Annotation mapping
public class OldUserInput extends HttpServlet {
   public void doPost(HttpServletRequest req,HttpServletResponse res)
		   throws IOException,ServletException{
	   res.setContentType("text/html");
	   PrintWriter out=res.getWriter();
	   String _username=req.getParameter("username");
	   String _password=req.getParameter("password");
	   try{
		   Connection con=OraConnection.getConnection();
		   String sql="select f2 from test where f1='"+_username+"'";
		   PreparedStatement ptmt=con.prepareStatement(sql);
		   ResultSet rs=ptmt.executeQuery();
		   byte arr[]=_password.getBytes();
		   String encryptedPassword=Base64.getEncoder().encodeToString(arr);
		   Boolean b=false;
		   while(rs.next()){
			   if((rs.getString(1)).equals(encryptedPassword)){
				   b=true;
				   break;
			   }
		   }
		   if(!b){
			   out.println("Wrong Username or Password !!");
			   req.getRequestDispatcher("index.html").include(req, res);
		   }
		   else{
			   HttpSession session=req.getSession();
			   Cookie ck=new Cookie("username",_username);
			   res.addCookie(ck);
			   con.close(); // new edit
			   res.sendRedirect("mediate.html");
		   }
		   con.close();
	   }
	   catch(Exception ex){ex.printStackTrace();}
  }
}
	   
