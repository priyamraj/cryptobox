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
@WebServlet("/RemoveProfile")//Annotation mapping
public class RemoveProfile extends HttpServlet {
   public void doGet(HttpServletRequest req,HttpServletResponse res)
		   throws IOException,ServletException{
	   res.setContentType("text/html");
	   PrintWriter out=res.getWriter();
	   Cookie ck[]=req.getCookies();
	   String username=ck[1].getValue();
	   try{
		   Connection con=OraConnection.getConnection();
		   String sql="delete from storage where username='"+username+"'";
		   PreparedStatement ptmt=con.prepareStatement(sql);
		   ptmt.executeUpdate();
		   String sequal="delete from test where f1='"+username+"'";
		   PreparedStatement ptmt2=con.prepareStatement(sequal);
		   ptmt2.executeUpdate();
		   con.close();
		   res.sendRedirect("LogUserOut");
	   }
	   catch(Exception ex){ex.printStackTrace();}
  }
}
	   
