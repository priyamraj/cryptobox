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
@WebServlet("/NewUserInput")//Annotation mapping
public class NewUserInput extends HttpServlet {
   public void doPost(HttpServletRequest req,HttpServletResponse res)
		   throws IOException,ServletException{
	   res.setContentType("text/html");
	   PrintWriter out=res.getWriter();
	   String _username=req.getParameter("username");
	   String _password=req.getParameter("password");
	   
	   try{
		   Connection con=OraConnection.getConnection();
		   String sql="select f1 from test";
		   PreparedStatement ptmt=con.prepareStatement(sql);
		   ResultSet rs=ptmt.executeQuery();
		   Boolean b=true;
		   while(rs.next()){
			   if((rs.getString(1)).equals(_username)){
				   b=false;
				   break;
			   }
		   }
		   if(!b){
			   out.println("Username already taken !!");
			   req.getRequestDispatcher("index.html").include(req, res);
		   }
		   else{
			   byte[] arr=_password.getBytes();
			   String b64encoded = Base64.getEncoder().encodeToString(arr);
			   String sequal="insert into test(f1,f2) values(?,?)";
			   PreparedStatement ptmt1=con.prepareStatement(sequal);
			   ptmt1.setString(1, _username);
			   ptmt1.setString(2, b64encoded);
			   ptmt1.executeUpdate();
			   HttpSession session=req.getSession();
			   Cookie ck=new Cookie("username",_username);
			   res.addCookie(ck);
			   con.close(); // new edit
			   res.sendRedirect("newData.html");
			   
		   }
		   con.close();
	   }
	   catch(Exception ex){ex.printStackTrace();}
  }
}
	   
