<%@page import="java.util.*"%>
<%@page import="java.sql.*"%>
<%@page import="java.io.*"%>
<%@page import="webtest.*"%>
<%@page import="java.io.IOException" %>
<%@page import="java.util.Base64" %>
<%@page import="javax.servlet.*"%>
<%@page import="javax.servlet.http.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html >
<head>
  <meta charset="UTF-8">
  <title>Display Page</title>
  <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,700' rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">

  
      <style>
      body {
  font-family: "Open Sans", sans-serif;
  height: 100vh;
  background: url("https://i.imgur.com/HgflTDf.jpg") 50% fixed;
  background-size: cover;
  color: silver;
}
     a{
     color: silver;
     font-family: monospace;
     font-weight: bold;
     font-size: large;
}
header{
  font-family: "Open Sans", sans-serif;
  color: gray;
  
}

table,th,td{
  border: 1px solid silver;
}

table{
 border-collapse: collapse;
}
th, td {
    padding: 5px;
    text-align: left;
    border-bottom: 1px solid #ddd;
}
tr:hover {
   background-color: purple; 
   opacity: 0.5;
   filter: alpha(opacity=50);
}
@keyframes spinner {
  0% {
    transform: rotateZ(0deg);
  }
  100% {
    transform: rotateZ(359deg);
  }
}
* {
  box-sizing: border-box;
}

.wrapper {
  display: flex;
  align-items: center;
  flex-direction: column;
  justify-content: center;
  width: 100%;
  min-height: 100%;
  padding: 20px;
  background: rgba(4, 40, 68, 0.85);
}

.login {
  border-radius: 2px 2px 5px 5px;
  padding: 10px 20px 20px 20px;
  width: 90%;
  max-width: 320px;
  background: #ffffff;
  position: relative;
  padding-bottom: 80px;
  box-shadow: 0px 1px 5px rgba(0, 0, 0, 0.3);
}
.login.loading button {
  max-height: 100%;
  padding-top: 50px;
}
.login.loading button .spinner {
  opacity: 1;
  top: 40%;
}
.login.ok button {
  background-color: #8bc34a;
}
.login.ok button .spinner {
  border-radius: 0;
  border-top-color: transparent;
  border-right-color: transparent;
  height: 20px;
  animation: none;
  transform: rotateZ(-45deg);
}
.login input {
  display: block;
  padding: 15px 10px;
  margin-bottom: 10px;
  width: 100%;
  border: 1px solid #ddd;
  transition: border-width 0.2s ease;
  border-radius: 2px;
  color: #ccc;
}
.login input + i.fa {
  color: #fff;
  font-size: 1em;
  position: absolute;
  margin-top: -47px;
  opacity: 0;
  left: 0;
  transition: all 0.1s ease-in;
}
.login input:focus {
  outline: none;
  color: #444;
  border-color: #2196F3;
  border-left-width: 35px;
}
.login input:focus + i.fa {
  opacity: 1;
  left: 30px;
  transition: all 0.25s ease-out;
}
.login a {
  font-size: 0.8em;
  color: #2196F3;
  text-decoration: none;
}
.login .title {
  color: #444;
  font-size: 1.2em;
  font-weight: bold;
  margin: 10px 0 30px 0;
  border-bottom: 1px solid #eee;
  padding-bottom: 20px;
}
.login button {
  width: 100%;
  height: 100%;
  padding: 10px 10px;
  background: #2196F3;
  color: #fff;
  display: block;
  border: none;
  margin-top: 20px;
  position: absolute;
  left: 0;
  bottom: 0;
  max-height: 60px;
  border: 0px solid rgba(0, 0, 0, 0.1);
  border-radius: 0 0 2px 2px;
  transform: rotateZ(0deg);
  transition: all 0.1s ease-out;
  border-bottom-width: 7px;
}
.login button .spinner {
  display: block;
  width: 40px;
  height: 40px;
  position: absolute;
  border: 4px solid #ffffff;
  border-top-color: rgba(255, 255, 255, 0.3);
  border-radius: 100%;
  left: 50%;
  top: 0;
  opacity: 0;
  margin-left: -20px;
  margin-top: -20px;
  animation: spinner 0.6s infinite linear;
  transition: top 0.3s 0.3s ease, opacity 0.3s 0.3s ease, border-radius 0.3s ease;
  box-shadow: 0px 1px 0px rgba(0, 0, 0, 0.2);
}
.login:not(.loading) button:hover {
  box-shadow: 0px 1px 3px #2196F3;
}
.login:not(.loading) button:focus {
  border-bottom-width: 4px;
}

div.transbox {
    margin: 30px;
    background-color: gray;
    border: 1px solid black;
    opacity: 0.5;
    filter: alpha(opacity=60);
}

div.transbox table {
    font-weight: bold;
    color: silver;
}

footer {
  display: block;
  padding-top: 50px;
  text-align: center;
  color: #ddd;
  font-weight: normal;
  text-shadow: 0px -1px 0px rgba(0, 0, 0, 0.2);
  font-size: 0.8em;
}
footer a, footer a:link {
  color: #fff;
  text-decoration: none;
}

    </style>

  <script src="https://cdnjs.cloudflare.com/ajax/libs/prefixfree/1.0.7/prefixfree.min.js"></script>
<%
boolean b=true;
try{
   HttpSession sess=request.getSession();
   Cookie ck[]=request.getCookies();
   if(sess==null || ck==null){
	   b=false;
	   response.sendRedirect("index.html");
   }
   String _username="%$empty$%";
   _username=ck[1].getValue();
   Connection conn=JavaCon.getConnection();
   String sql="select * from storage where Username='"+_username+"'";
   PreparedStatement ptmt=conn.prepareStatement(sql);
   ResultSet rs=ptmt.executeQuery();
%>
</head>
<body>
<header>Currently logged in : <%=_username %></header>
  <div class="wrapper">
<h1>Here's your data</h1><div class="transbox">
<table border="1">
  <tr>
    <th>Tag</th>
    <th>Current Key</th>
    <th>Get Data</th>
    <th>Edit Key</th>
    <th>Delete Item</th>
  </tr>
  <%while(rs.next()){ %>
  <tr>
    <td data-th="Tag" style="text-align: center;"><%= rs.getString(2) %></td>
    <td data-th="Current Key" style="text-align: center;"><%= rs.getInt(4) %></td>
    <td data-th="Get Data" style="text-align: center;"><a href='Decrypt.jsp?tag=<%=rs.getString(2)%>&data=<%=rs.getString(3)%>&key=<%=rs.getInt(4)%>'>Get</a></td>
    <td data-th="Edit Key" style="text-align: center;"><a href='Edit.jsp?tag=<%=rs.getString(2)%>&data=<%=rs.getString(3)%>&key=<%=rs.getInt(4)%>'>Edit Key</a></td>
    <td data-th="Delete Item" style="text-align: center;"><a href='Delete?tag=<%=rs.getString(2)%>&data=<%=rs.getString(3)%>&key=<%=rs.getInt(4)%>'>Delete</a></td>
  </tr>
  <% } 
}
catch(Exception e){
	if(b)
	   response.sendRedirect("index.html");
	}
  
  %>
</table></div>
    <footer><br><br><hr>
    <p>We advise to re-encrypt the data with a different key on a regular basis for better security</p>
    <hr>
    <form action="mediate.html">
    <input type="submit" value="Actions Page" style="color: blue;">
  </form>
   <hr>
    <form action="newData.html">
    <input type="submit" value="Add Data" style="color: blue;">
  </form>
  <hr>
  <form action="LogUserOut">
    <input type="submit" value="Logout" style="color: blue;">
  </form>
    </footer></div>
  <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

    <script src="js/index.js"></script>

</body>
</html>