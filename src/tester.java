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
@WebServlet("/tester")//Annotation mapping
public class tester extends HttpServlet {
   public void doGet(HttpServletRequest req,HttpServletResponse res)
		   throws IOException,ServletException{
	   res.setContentType("text/html");
	   PrintWriter out=res.getWriter();
	   HttpSession session=req.getSession(false);
	   Cookie ck[]=req.getCookies();
	   if(ck==null)
		   out.print("empty");
	   else{
		   for(Cookie ckk:ck)
			   out.println(ckk.getName()+" "+ckk.getValue());
	   }
	   if(session==null)
		   out.print("yayyyyyy");
	   else
		   out.print("so sad");
  }
}
	   
