package com.thinking.machines.webrock;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
public class ServletOne extends HttpServlet
{
public void doGet(HttpServletRequest request,HttpServletResponse response)
{
try
{
PrintWriter pw=response.getWriter();
response.setContentType("text/html");
String name=request.getParameter("jsFile");
ServletContext servletContext=request.getServletContext();
String path=servletContext.getRealPath("")+File.separator+"js"+File.separator+name;
File file=new File(path);
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
pw.println(randomAccessFile.readLine());
}
}catch(Exception e)
{

}
}
}