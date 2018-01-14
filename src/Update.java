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
@WebServlet("/Update")//Annotation mapping
public class Update extends HttpServlet {
   public void doPost(HttpServletRequest req,HttpServletResponse res)
		   throws IOException,ServletException{
	   res.setContentType("text/html");
	   PrintWriter out=res.getWriter();
	   String randomSpace="icreatedthiswebappandiloveitsosososmuch";
	   String _tag=req.getParameter("tag");
	   String _data=req.getParameter("data");
	   int _key=Integer.parseInt(req.getParameter("key"));
	   int _oldKey=Integer.parseInt(req.getParameter("oldKey"));
	   String ans=new String (Base64.getDecoder().decode(_data));
	   ans=ans.substring(_oldKey);
	   String newData=randomSpace.substring(0,_key)+ans;
	   String encryptedData=Base64.getEncoder().encodeToString(
			   newData.getBytes());
	   Cookie ck[]=req.getCookies();
	   String username=ck[1].getValue();
	   out.println(username);
	   try{
		   Connection con=OraConnection.getConnection();
		   String sql="update storage set Key="+_key+",Data='"+encryptedData+"' where Tag='"+_tag+"' and Username='"
		   		+username+"'";
		   PreparedStatement ptmt=con.prepareStatement(sql);
		   ptmt.executeUpdate();
		   con.close();
		   res.sendRedirect("Display.jsp");
	   }
	   catch(Exception ex){ex.printStackTrace();}
  }
}
	   
