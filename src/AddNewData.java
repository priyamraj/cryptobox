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
@WebServlet("/AddNewData")//Annotation mapping
public class AddNewData extends HttpServlet {
   public void doPost(HttpServletRequest req,HttpServletResponse res)
		   throws IOException,ServletException{
	   res.setContentType("text/html");
	   PrintWriter out=res.getWriter();
	   HttpSession session=req.getSession(false); 
	   
	   
	   if(session==null){
		   out.println("Please Login first !!");
		   req.getRequestDispatcher("index.html").include(req, res);
	   }
	   
	   String _tag=req.getParameter("tag");
	   String _data=req.getParameter("data");
	   int _key=Integer.parseInt(req.getParameter("key"));
	   
	   Cookie ck[]=req.getCookies();
	   String _username=ck[1].getValue();
	   String randomSpace="icreatedthiswebappandiloveitsosososmuch";
	   
	   try{
		   Connection con=OraConnection.getConnection();
		   String sql="insert into storage values(?,?,?,?)";
		   PreparedStatement ptmt=con.prepareStatement(sql);
		   String new_data=randomSpace.substring(0,_key)+_data;
		   String encryptedData=Base64.getEncoder().encodeToString(
				   new_data.getBytes());
		   ptmt.setString(1, _username);
		   ptmt.setString(2, _tag);
		   ptmt.setString(3, encryptedData);
		      ptmt.setInt(4, _key);
		   ptmt.executeUpdate();
		   //send to main profile page
		   con.close();
		   Cookie ck1=new Cookie("username",_username);
		   res.addCookie(ck1);
		   res.sendRedirect("Display.jsp");
	   }
	   catch(Exception ex){ex.printStackTrace();}
  }
}
	   
