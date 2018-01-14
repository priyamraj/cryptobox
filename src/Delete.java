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
@WebServlet("/Delete")//Annotation mapping
public class Delete extends HttpServlet {
   public void doGet(HttpServletRequest req,HttpServletResponse res)
		   throws IOException,ServletException{
	   res.setContentType("text/html");
	   PrintWriter out=res.getWriter();
	   String _tag=req.getParameter("tag");
	   String _data=req.getParameter("data");
	   int _key=Integer.parseInt(req.getParameter("key"));
	   Cookie ck[]=req.getCookies();
	   String username=ck[1].getValue();
	   try{
		   Connection con=OraConnection.getConnection();
		   String sql="delete from storage where username='"+username+"' and tag='"+_tag+"'";
		   PreparedStatement ptmt=con.prepareStatement(sql);
		   ptmt.executeUpdate();
		   con.close();
		   res.sendRedirect("Display.jsp");
	   }
	   catch(Exception ex){ex.printStackTrace();}
  }
}
	   
