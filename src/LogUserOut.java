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
@WebServlet("/LogUserOut")//Annotation mapping
public class LogUserOut extends HttpServlet {
   public void doGet(HttpServletRequest req,HttpServletResponse res)
		   throws IOException,ServletException{
	   res.setContentType("text/html");
	   PrintWriter out=res.getWriter();
	   HttpSession session=req.getSession(false);
	   session.invalidate();
	   Cookie ck[]=req.getCookies();
	   ck[0].setMaxAge(0);
	   ck[1].setMaxAge(0);
	   res.addCookie(ck[0]);
	   res.addCookie(ck[1]);
	   res.sendRedirect("Exit.html");
  }
}
	   
