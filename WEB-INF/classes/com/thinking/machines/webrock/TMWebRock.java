package com.thinking.machines.webrock;
import com.thinking.machines.webrock.model.*;
import com.thinking.machines.webrock.pojo.*;
import com.thinking.machines.webrock.scope.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.lang.reflect.*;
import com.google.gson.*;
public class TMWebRock extends HttpServlet
{
public void doGet(HttpServletRequest request,HttpServletResponse response)
{
try
{
ServletContext context=request.getServletContext();
WebRockModel webRockModel=(WebRockModel	)context.getAttribute("webRockModel");
String urlString=request.getRequestURI();
urlString=urlString.substring(25,urlString.length());
boolean urlExists=false;
for(Map.Entry<String,Service> map: webRockModel.hashMapWeb.entrySet())
{
String keyPath=map.getKey();
Service service=map.getValue();
if(keyPath.equalsIgnoreCase(urlString))
{
urlExists=true;
if(service.getIsPostAllowed()==true) response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
boolean scopeExists=true;
PrintWriter pw=response.getWriter();
if(service.getIsSessionScope()==true)
{
scopeExists=false;
SessionScope sessionScope1=new SessionScope();
HttpSession httpSession1=request.getSession();
sessionScope1.setAttribute(httpSession1);
Class c1=service.getServiceClass();
Method method1=c1.getMethod("setSessionScope",SessionScope.class);
Object object1=c1.newInstance();
method1.invoke(object1,sessionScope1);
if(service.getIsRequestScope()==true)
{
RequestScope requestScope1=new RequestScope();
requestScope1.setAttribute(request);
method1=c1.getMethod("setRequestScope",RequestScope.class);
method1.invoke(object1,requestScope1);
}
if(service.getIsApplicationScope()==true)
{
ApplicationScope applicationScope1=new ApplicationScope();
ServletContext servletContext1=request.getServletContext();
applicationScope1.setAttribute(servletContext1);
method1=c1.getMethod("setApplicationScope",ApplicationScope.class);
method1.invoke(object1,applicationScope1);
}
if(service.getIsAutowired()==true)
{
List<AutowiredClass> autowiredList1=service.getAutowiredList();
if(autowiredList1.size()>0)
{
for(AutowiredClass autowiredClass1 : autowiredList1)
{
System.out.println(autowiredClass1.getName());
HttpSession httpSessionAutowired1=request.getSession();
Object objectSession1=httpSessionAutowired1.getAttribute(autowiredClass1.getName());
if(objectSession1!=null)
{
method1=c1.getMethod(autowiredClass1.getMethodName(),autowiredClass1.getType());
method1.invoke(object1,objectSession1);
}
ServletContext servletContextAutowired1=request.getServletContext();
Object objectContext1=servletContextAutowired1.getAttribute(autowiredClass1.getName());
if(objectContext1!=null)
{
method1=c1.getMethod(autowiredClass1.getMethodName(),autowiredClass1.getType());
method1.invoke(object1,objectContext1);
}

}
}
}

List<Object> objectList1=new ArrayList();
List<RequestParameterClass> requestParameterList1=service.getRequestParameterList();
String requestParameterString1;
for(RequestParameterClass requestParameterClass1 : requestParameterList1)
{
if(requestParameterClass1.getType().equalsIgnoreCase("class java.lang.String"))
{
requestParameterString1=request.getParameter(requestParameterClass1.getName());
if(requestParameterString1==null)
{
objectList1.add(null);
}
if(requestParameterString1!=null)
{
objectList1.add(requestParameterString1);
}
}
if(requestParameterClass1.getType().equalsIgnoreCase("int"))
{
requestParameterString1=request.getParameter(requestParameterClass1.getName());
if(requestParameterString1==null)
{
objectList1.add(0);
}
if(requestParameterString1!=null)
{
objectList1.add(Integer.parseInt(requestParameterString1));
}
}

if(requestParameterClass1.getType().equalsIgnoreCase("char"))
{
requestParameterString1=request.getParameter(requestParameterClass1.getName());
if(requestParameterString1==null)
{
objectList1.add(' ');
}
if(requestParameterString1!=null)
{
objectList1.add(requestParameterString1.charAt(0));
}
}

if(requestParameterClass1.getType().equalsIgnoreCase("double"))
{
requestParameterString1=request.getParameter(requestParameterClass1.getName());
if(requestParameterString1==null)
{
objectList1.add(0);
}
if(requestParameterString1!=null)
{
objectList1.add(Double.parseDouble(requestParameterString1));
}
}

}
if(requestParameterList1.size()==0)
{
String responseSend=(String)service.getService().invoke(object1);
if(responseSend!=null) pw.printf(responseSend);
}
else
{
String responseSend=(String)service.getService().invoke(object1,objectList1.toArray());
if(responseSend!=null) pw.printf(responseSend);
}

}

if(scopeExists==true)
{
if(service.getIsApplicationScope()==true)
{
scopeExists=false;
ApplicationScope applicationScope=new ApplicationScope();
ServletContext servletContext=request.getServletContext();
applicationScope.setAttribute(servletContext);
Class c2=service.getServiceClass();
Method method2=c2.getMethod("setApplicationScope",ApplicationScope.class);
Object object2=c2.newInstance();
method2.invoke(object2,applicationScope);
if(service.getIsSessionScope()==true)
{
SessionScope sessionScope2=new SessionScope();
HttpSession httpSession2=request.getSession();
sessionScope2.setAttribute(httpSession2);
method2=c2.getMethod("setSessionScope",SessionScope.class);
method2.invoke(object2,sessionScope2);
}
if(service.getIsRequestScope()==true)
{
RequestScope requestScope2=new RequestScope();
requestScope2.setAttribute(request);
method2=c2.getMethod("setRequestScope",RequestScope.class);
method2.invoke(object2,requestScope2);
}
if(service.getIsAutowired()==true)
{
List<AutowiredClass> autowiredList2=service.getAutowiredList();
for(AutowiredClass autowiredClass2 : autowiredList2)
{
HttpSession httpSessionAutowired2=request.getSession();
Object objectAutowired2=httpSessionAutowired2.getAttribute(autowiredClass2.getName());
if(objectAutowired2!=null)
{
method2=c2.getMethod(autowiredClass2.getMethodName(),autowiredClass2.getType());
method2.invoke(object2,objectAutowired2);
}
ServletContext servletContextAutowired2=request.getServletContext();
objectAutowired2=servletContextAutowired2.getAttribute(autowiredClass2.getName());
if(objectAutowired2!=null)
{
method2=c2.getMethod(autowiredClass2.getMethodName(),autowiredClass2.getType());
method2.invoke(object2,objectAutowired2);
}

}
}



List<Object> objectList1=new ArrayList();
List<RequestParameterClass> requestParameterList1=service.getRequestParameterList();
String requestParameterString1;
for(RequestParameterClass requestParameterClass1 : requestParameterList1)
{
if(requestParameterClass1.getType().equalsIgnoreCase("class java.lang.String"))
{
requestParameterString1=request.getParameter(requestParameterClass1.getName());
if(requestParameterString1==null)
{
objectList1.add(null);
}
if(requestParameterString1!=null)
{
objectList1.add(requestParameterString1);
}
}
if(requestParameterClass1.getType().equalsIgnoreCase("int"))
{
requestParameterString1=request.getParameter(requestParameterClass1.getName());
if(requestParameterString1==null)
{
objectList1.add(0);
}
if(requestParameterString1!=null)
{
objectList1.add(Integer.parseInt(requestParameterString1));
}
}

if(requestParameterClass1.getType().equalsIgnoreCase("char"))
{
requestParameterString1=request.getParameter(requestParameterClass1.getName());
if(requestParameterString1==null)
{
objectList1.add(' ');
}
if(requestParameterString1!=null)
{
objectList1.add(requestParameterString1.charAt(0));
}
}

if(requestParameterClass1.getType().equalsIgnoreCase("double"))
{
requestParameterString1=request.getParameter(requestParameterClass1.getName());
if(requestParameterString1==null)
{
objectList1.add(0);
}
if(requestParameterString1!=null)
{
objectList1.add(Double.parseDouble(requestParameterString1));
}
}

}
if(requestParameterList1.size()==0)
{
String responseSend=(String)service.getService().invoke(object2);
if(responseSend!=null) pw.printf(responseSend);
}
else
{
String responseSend=(String)service.getService().invoke(object2,objectList1.toArray());
if(responseSend!=null) pw.printf(responseSend);
}

}
}

if(scopeExists==true)
{
if(service.getIsRequestScope()==true)
{
RequestScope requestScope=new RequestScope();
requestScope.setAttribute(request);
Class c3=service.getServiceClass();
Method method3=c3.getMethod("setRequestScope",RequestScope.class);
Object object3=c3.newInstance();
method3.invoke(object3,requestScope);
if(service.getIsSessionScope()==true)
{
SessionScope sessionScope=new SessionScope();
HttpSession httpSession3=request.getSession();
sessionScope.setAttribute(httpSession3);
method3=c3.getMethod("setSessionScope",SessionScope.class);
method3.invoke(object3,sessionScope);
}
if(service.getIsApplicationScope()==true)
{
ApplicationScope applicationScope=new ApplicationScope();
ServletContext servletContext3=request.getServletContext();
applicationScope.setAttribute(servletContext3);
method3=c3.getMethod("setApplicationScope",ApplicationScope.class);
method3.invoke(object3,applicationScope);
}
if(service.getIsAutowired()==true)
{
List<AutowiredClass> autowiredClassList3=service.getAutowiredList();
if(autowiredClassList3.size()>0)
{
for(AutowiredClass autowiredClass3 : autowiredClassList3)
{
HttpSession httpSessionObject3=request.getSession();
Object autowiredObject3=httpSessionObject3.getAttribute(autowiredClass3.getName());
if(autowiredObject3!=null)
{
method3=c3.getMethod(autowiredClass3.getMethodName(),autowiredClass3.getType());
method3.invoke(object3,autowiredObject3);
}
ServletContext servletContextObject3=request.getServletContext();
autowiredObject3=servletContextObject3.getAttribute(autowiredClass3.getName());
if(autowiredObject3!=null)
{
method3=c3.getMethod(autowiredClass3.getMethodName(),autowiredClass3.getType());
method3.invoke(object3,autowiredObject3);
}
}
}
}


List<Object> objectList1=new ArrayList();
List<RequestParameterClass> requestParameterList1=service.getRequestParameterList();
String requestParameterString1;
for(RequestParameterClass requestParameterClass1 : requestParameterList1)
{
if(requestParameterClass1.getType().equalsIgnoreCase("class java.lang.String"))
{
requestParameterString1=request.getParameter(requestParameterClass1.getName());
if(requestParameterString1==null)
{
objectList1.add(null);
}
if(requestParameterString1!=null)
{
objectList1.add(requestParameterString1);
}
}
if(requestParameterClass1.getType().equalsIgnoreCase("int"))
{
requestParameterString1=request.getParameter(requestParameterClass1.getName());
if(requestParameterString1==null)
{
objectList1.add(0);
}
if(requestParameterString1!=null)
{
objectList1.add(Integer.parseInt(requestParameterString1));
}
}

if(requestParameterClass1.getType().equalsIgnoreCase("char"))
{
requestParameterString1=request.getParameter(requestParameterClass1.getName());
if(requestParameterString1==null)
{
objectList1.add(' ');
}
if(requestParameterString1!=null)
{
objectList1.add(requestParameterString1.charAt(0));
}
}

if(requestParameterClass1.getType().equalsIgnoreCase("double"))
{
requestParameterString1=request.getParameter(requestParameterClass1.getName());
if(requestParameterString1==null)
{
objectList1.add(0);
}
if(requestParameterString1!=null)
{
objectList1.add(Double.parseDouble(requestParameterString1));
}
}

}
if(requestParameterList1.size()==0)
{
String responseSend=(String)service.getService().invoke(object3);
if(responseSend!=null) pw.printf(responseSend);
}
else
{
String responseSend=(String)service.getService().invoke(object3,objectList1.toArray());
if(responseSend!=null) pw.printf(responseSend);
}
service.getService().invoke(object3);
}
}

if(service.getIsApplicationDirectory()==true)
{
File file=new File(request.getServletContext().getRealPath(""));
ApplicationDirectory applicationDirectory=new ApplicationDirectory(file);
Class c4=service.getServiceClass();
Method method4=c4.getMethod("setApplicationDirectory",ApplicationDirectory.class);
Object object4=c4.newInstance();
method4.invoke(object4,applicationDirectory);
service.getService().invoke(object4);
}

boolean sessionExists=service.getIsSessionScope();
boolean applicationExists=service.getIsApplicationScope();
boolean requestExists=service.getIsRequestScope();
boolean applicationDirectoryExists=service.getIsApplicationDirectory();
if(sessionExists==false && applicationExists==false && requestExists==false && applicationDirectoryExists==false)
{
Class c5=service.getServiceClass();
Method method5=service.getService();
Object object5=c5.newInstance();
if(service.getIsAutowired()==true)
{
List<AutowiredClass> autowiredClassList5=service.getAutowiredList();
if(autowiredClassList5.size()>0)
{
for(AutowiredClass autowiredClass5 : autowiredClassList5)
{
HttpSession httpSessionObject5=request.getSession();
Object objectAutowired5=httpSessionObject5.getAttribute(autowiredClass5.getName());
if(objectAutowired5!=null)
{
method5=c5.getMethod(autowiredClass5.getMethodName(),autowiredClass5.getType());
method5.invoke(object5,objectAutowired5);
}
ServletContext servletContextObject5=request.getServletContext();
objectAutowired5=servletContextObject5.getAttribute(autowiredClass5.getName());
if(objectAutowired5!=null)
{
method5=c5.getMethod(autowiredClass5.getMethodName(),autowiredClass5.getType());
method5.invoke(object5,objectAutowired5);
}
}
}
}

List<Object> objectList1=new ArrayList();
List<RequestParameterClass> requestParameterList1=service.getRequestParameterList();
String requestParameterString1;
for(RequestParameterClass requestParameterClass1 : requestParameterList1)
{
if(requestParameterClass1.getType().equalsIgnoreCase("class java.lang.String"))
{
requestParameterString1=request.getParameter(requestParameterClass1.getName());
if(requestParameterString1==null)
{
objectList1.add(null);
}
if(requestParameterString1!=null)
{
objectList1.add(requestParameterString1);
}
}
if(requestParameterClass1.getType().equalsIgnoreCase("int"))
{
requestParameterString1=request.getParameter(requestParameterClass1.getName());
if(requestParameterString1==null)
{
objectList1.add(0);
}
if(requestParameterString1!=null)
{
objectList1.add(Integer.parseInt(requestParameterString1));
}
}

if(requestParameterClass1.getType().equalsIgnoreCase("char"))
{
requestParameterString1=request.getParameter(requestParameterClass1.getName());
if(requestParameterString1==null)
{
objectList1.add(' ');
}
if(requestParameterString1!=null)
{
objectList1.add(requestParameterString1.charAt(0));
}
}

if(requestParameterClass1.getType().equalsIgnoreCase("double"))
{
requestParameterString1=request.getParameter(requestParameterClass1.getName());
if(requestParameterString1==null)
{
objectList1.add(0);
}
if(requestParameterString1!=null)
{
objectList1.add(Double.parseDouble(requestParameterString1));
}
}

if(requestParameterClass1.getType().equalsIgnoreCase("class com.thinking.machines.webrock.scope.SessionScope"))
{
SessionScope sessionScope5=new SessionScope();
HttpSession httpSession5=request.getSession();
sessionScope5.setAttribute(httpSession5);
objectList1.add(sessionScope5);
}
if(requestParameterClass1.getType().equalsIgnoreCase("class com.thinking.machines.webrock.scope.ApplicationScope"))
{
ApplicationScope applicationScope5=new ApplicationScope();
ServletContext servletContext5=request.getServletContext();
applicationScope5.setAttribute(servletContext5);
objectList1.add(applicationScope5);
}
if(requestParameterClass1.getType().equalsIgnoreCase("class com.thinking.machines.webrock.scope.RequestScope"))
{
RequestScope requestScope5=new RequestScope();
requestScope5.setAttribute(request);
objectList1.add(requestScope5);
}
}
if(requestParameterList1.size()==0)
{
String responseSend=(String)service.getService().invoke(object5);
if(responseSend!=null) pw.printf(responseSend);
}
else
{
String responseSend=(String)service.getService().invoke(object5,objectList1.toArray());
if(responseSend!=null) pw.printf(responseSend);
}
}




String forwardTo=null;
if(service.getForwardTo()!=null)
{
forwardTo=service.getForwardTo();
if(forwardTo.endsWith(".jsp"))
{
RequestDispatcher requestDispatcher=request.getRequestDispatcher(forwardTo);
requestDispatcher.forward(request,response);
}
Service forwardService=webRockModel.hashMapWeb.get(forwardTo);
if(forwardService==null)
{
response.sendError(HttpServletResponse.SC_NOT_FOUND);
}




}


}

}

if(urlExists==false)
{
response.sendError(HttpServletResponse.SC_NOT_FOUND);
}
}catch(Exception e)
{

}
}
public void doPost(HttpServletRequest request,HttpServletResponse response)
{

try
{
ServletContext context=request.getServletContext();
WebRockModel webRockModel=(WebRockModel	)context.getAttribute("webRockModel");
String urlString=request.getRequestURI();
urlString=urlString.substring(25,urlString.length());
boolean urlExists=false;
for(Map.Entry<String,Service> map: webRockModel.hashMapWeb.entrySet())
{
String keyPath=map.getKey();
Service service=map.getValue();
if(keyPath.equalsIgnoreCase(urlString))
{
urlExists=true;
if(service.getIsGetAllowed()==true) response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
boolean scopeExists=true;
PrintWriter pw=response.getWriter();
if(service.getIsSessionScope()==true)
{
scopeExists=false;
SessionScope sessionScope1=new SessionScope();
HttpSession httpSession1=request.getSession();
sessionScope1.setAttribute(httpSession1);
Class c1=service.getServiceClass();
Method method1=c1.getMethod("setSessionScope",SessionScope.class);
Object object1=c1.newInstance();
method1.invoke(object1,sessionScope1);
if(service.getIsRequestScope()==true)
{
RequestScope requestScope1=new RequestScope();
requestScope1.setAttribute(request);
method1=c1.getMethod("setRequestScope",RequestScope.class);
method1.invoke(object1,requestScope1);
}
if(service.getIsApplicationScope()==true)
{
ApplicationScope applicationScope1=new ApplicationScope();
ServletContext servletContext1=request.getServletContext();
applicationScope1.setAttribute(servletContext1);
method1=c1.getMethod("setApplicationScope",ApplicationScope.class);
method1.invoke(object1,applicationScope1);
}
if(service.getIsAutowired()==true)
{
List<AutowiredClass> autowiredList1=service.getAutowiredList();
if(autowiredList1.size()>0)
{
for(AutowiredClass autowiredClass1 : autowiredList1)
{
System.out.println(autowiredClass1.getName());
HttpSession httpSessionAutowired1=request.getSession();
Object objectSession1=httpSessionAutowired1.getAttribute(autowiredClass1.getName());
if(objectSession1!=null)
{
method1=c1.getMethod(autowiredClass1.getMethodName(),autowiredClass1.getType());
method1.invoke(object1,objectSession1);
}
ServletContext servletContextAutowired1=request.getServletContext();
Object objectContext1=servletContextAutowired1.getAttribute(autowiredClass1.getName());
if(objectContext1!=null)
{
method1=c1.getMethod(autowiredClass1.getMethodName(),autowiredClass1.getType());
method1.invoke(object1,objectContext1);
}

}
}
}

List<Object> objectList1=new ArrayList();
List<RequestParameterClass> requestParameterList1=service.getRequestParameterList();
String requestParameterString1;
for(RequestParameterClass requestParameterClass1 : requestParameterList1)
{
if(requestParameterClass1.getType().equalsIgnoreCase("class java.lang.String"))
{
requestParameterString1=request.getParameter(requestParameterClass1.getName());
if(requestParameterString1==null)
{
objectList1.add(null);
}
if(requestParameterString1!=null)
{
objectList1.add(requestParameterString1);
}
}
if(requestParameterClass1.getType().equalsIgnoreCase("int"))
{
requestParameterString1=request.getParameter(requestParameterClass1.getName());
if(requestParameterString1==null)
{
objectList1.add(0);
}
if(requestParameterString1!=null)
{
objectList1.add(Integer.parseInt(requestParameterString1));
}
}

if(requestParameterClass1.getType().equalsIgnoreCase("char"))
{
requestParameterString1=request.getParameter(requestParameterClass1.getName());
if(requestParameterString1==null)
{
objectList1.add(' ');
}
if(requestParameterString1!=null)
{
objectList1.add(requestParameterString1.charAt(0));
}
}

if(requestParameterClass1.getType().equalsIgnoreCase("double"))
{
requestParameterString1=request.getParameter(requestParameterClass1.getName());
if(requestParameterString1==null)
{
objectList1.add(0);
}
if(requestParameterString1!=null)
{
objectList1.add(Double.parseDouble(requestParameterString1));
}
}
if(requestParameterClass1.getName().equalsIgnoreCase("AnyScope"))
{
BufferedReader br=request.getReader();
StringBuffer sb=new StringBuffer();
String b;
while(true)
{
b=br.readLine();
if(b==null) break;
sb.append(b);
}
String rowString=sb.toString();
String typeOfParameter1=requestParameterClass1.getType();
typeOfParameter1=typeOfParameter1.substring(6,typeOfParameter1.length());
Gson gson=new Gson();
Object oo=gson.fromJson(rowString,Class.forName(typeOfParameter1));
objectList1.add(oo);
}
}

if(requestParameterList1.size()==0)
{
String responseSend=(String)service.getService().invoke(object1);
if(responseSend!=null) pw.printf(responseSend);
}
else
{
String responseSend=(String)service.getService().invoke(object1,objectList1.toArray());
if(responseSend!=null) pw.printf(responseSend);
}

}

if(scopeExists==true)
{
if(service.getIsApplicationScope()==true)
{
scopeExists=false;
ApplicationScope applicationScope=new ApplicationScope();
ServletContext servletContext=request.getServletContext();
applicationScope.setAttribute(servletContext);
Class c2=service.getServiceClass();
Method method2=c2.getMethod("setApplicationScope",ApplicationScope.class);
Object object2=c2.newInstance();
method2.invoke(object2,applicationScope);
if(service.getIsSessionScope()==true)
{
SessionScope sessionScope2=new SessionScope();
HttpSession httpSession2=request.getSession();
sessionScope2.setAttribute(httpSession2);
method2=c2.getMethod("setSessionScope",SessionScope.class);
method2.invoke(object2,sessionScope2);
}
if(service.getIsRequestScope()==true)
{
RequestScope requestScope2=new RequestScope();
requestScope2.setAttribute(request);
method2=c2.getMethod("setRequestScope",RequestScope.class);
method2.invoke(object2,requestScope2);
}
if(service.getIsAutowired()==true)
{
List<AutowiredClass> autowiredList2=service.getAutowiredList();
for(AutowiredClass autowiredClass2 : autowiredList2)
{
HttpSession httpSessionAutowired2=request.getSession();
Object objectAutowired2=httpSessionAutowired2.getAttribute(autowiredClass2.getName());
if(objectAutowired2!=null)
{
method2=c2.getMethod(autowiredClass2.getMethodName(),autowiredClass2.getType());
method2.invoke(object2,objectAutowired2);
}
ServletContext servletContextAutowired2=request.getServletContext();
objectAutowired2=servletContextAutowired2.getAttribute(autowiredClass2.getName());
if(objectAutowired2!=null)
{
method2=c2.getMethod(autowiredClass2.getMethodName(),autowiredClass2.getType());
method2.invoke(object2,objectAutowired2);
}

}
}



List<Object> objectList1=new ArrayList();
List<RequestParameterClass> requestParameterList1=service.getRequestParameterList();
String requestParameterString1;
for(RequestParameterClass requestParameterClass1 : requestParameterList1)
{
if(requestParameterClass1.getType().equalsIgnoreCase("class java.lang.String"))
{
requestParameterString1=request.getParameter(requestParameterClass1.getName());
if(requestParameterString1==null)
{
objectList1.add(null);
}
if(requestParameterString1!=null)
{
objectList1.add(requestParameterString1);
}
}
if(requestParameterClass1.getType().equalsIgnoreCase("int"))
{
requestParameterString1=request.getParameter(requestParameterClass1.getName());
if(requestParameterString1==null)
{
objectList1.add(0);
}
if(requestParameterString1!=null)
{
objectList1.add(Integer.parseInt(requestParameterString1));
}
}

if(requestParameterClass1.getType().equalsIgnoreCase("char"))
{
requestParameterString1=request.getParameter(requestParameterClass1.getName());
if(requestParameterString1==null)
{
objectList1.add(' ');
}
if(requestParameterString1!=null)
{
objectList1.add(requestParameterString1.charAt(0));
}
}

if(requestParameterClass1.getType().equalsIgnoreCase("double"))
{
requestParameterString1=request.getParameter(requestParameterClass1.getName());
if(requestParameterString1==null)
{
objectList1.add(0);
}
if(requestParameterString1!=null)
{
objectList1.add(Double.parseDouble(requestParameterString1));
}
}


if(requestParameterClass1.getName().equalsIgnoreCase("AnyScope"))
{
BufferedReader br=request.getReader();
StringBuffer sb=new StringBuffer();
String b;
while(true)
{
b=br.readLine();
if(b==null) break;
sb.append(b);
}
String rowString=sb.toString();
String typeOfParameter1=requestParameterClass1.getType();
typeOfParameter1=typeOfParameter1.substring(6,typeOfParameter1.length());
Gson gson=new Gson();
Object oo=gson.fromJson(rowString,Class.forName(typeOfParameter1));
objectList1.add(oo);
}
}
if(requestParameterList1.size()==0)
{
String responseSend=(String)service.getService().invoke(object2);
if(responseSend!=null) pw.printf(responseSend);
}
else
{
String responseSend=(String)service.getService().invoke(object2,objectList1.toArray());
if(responseSend!=null) pw.printf(responseSend);
}

}
}

if(scopeExists==true)
{
if(service.getIsRequestScope()==true)
{
RequestScope requestScope=new RequestScope();
requestScope.setAttribute(request);
Class c3=service.getServiceClass();
Method method3=c3.getMethod("setRequestScope",RequestScope.class);
Object object3=c3.newInstance();
method3.invoke(object3,requestScope);
if(service.getIsSessionScope()==true)
{
SessionScope sessionScope=new SessionScope();
HttpSession httpSession3=request.getSession();
sessionScope.setAttribute(httpSession3);
method3=c3.getMethod("setSessionScope",SessionScope.class);
method3.invoke(object3,sessionScope);
}
if(service.getIsApplicationScope()==true)
{
ApplicationScope applicationScope=new ApplicationScope();
ServletContext servletContext3=request.getServletContext();
applicationScope.setAttribute(servletContext3);
method3=c3.getMethod("setApplicationScope",ApplicationScope.class);
method3.invoke(object3,applicationScope);
}
if(service.getIsAutowired()==true)
{
List<AutowiredClass> autowiredClassList3=service.getAutowiredList();
if(autowiredClassList3.size()>0)
{
for(AutowiredClass autowiredClass3 : autowiredClassList3)
{
HttpSession httpSessionObject3=request.getSession();
Object autowiredObject3=httpSessionObject3.getAttribute(autowiredClass3.getName());
if(autowiredObject3!=null)
{
method3=c3.getMethod(autowiredClass3.getMethodName(),autowiredClass3.getType());
method3.invoke(object3,autowiredObject3);
}
ServletContext servletContextObject3=request.getServletContext();
autowiredObject3=servletContextObject3.getAttribute(autowiredClass3.getName());
if(autowiredObject3!=null)
{
method3=c3.getMethod(autowiredClass3.getMethodName(),autowiredClass3.getType());
method3.invoke(object3,autowiredObject3);
}
}
}
}


List<Object> objectList1=new ArrayList();
List<RequestParameterClass> requestParameterList1=service.getRequestParameterList();
String requestParameterString1;
for(RequestParameterClass requestParameterClass1 : requestParameterList1)
{
if(requestParameterClass1.getType().equalsIgnoreCase("class java.lang.String"))
{
requestParameterString1=request.getParameter(requestParameterClass1.getName());
if(requestParameterString1==null)
{
objectList1.add(null);
}
if(requestParameterString1!=null)
{
objectList1.add(requestParameterString1);
}
}
if(requestParameterClass1.getType().equalsIgnoreCase("int"))
{
requestParameterString1=request.getParameter(requestParameterClass1.getName());
if(requestParameterString1==null)
{
objectList1.add(0);
}
if(requestParameterString1!=null)
{
objectList1.add(Integer.parseInt(requestParameterString1));
}
}

if(requestParameterClass1.getType().equalsIgnoreCase("char"))
{
requestParameterString1=request.getParameter(requestParameterClass1.getName());
if(requestParameterString1==null)
{
objectList1.add(' ');
}
if(requestParameterString1!=null)
{
objectList1.add(requestParameterString1.charAt(0));
}
}

if(requestParameterClass1.getType().equalsIgnoreCase("double"))
{
requestParameterString1=request.getParameter(requestParameterClass1.getName());
if(requestParameterString1==null)
{
objectList1.add(0);
}
if(requestParameterString1!=null)
{
objectList1.add(Double.parseDouble(requestParameterString1));
}
}


if(requestParameterClass1.getName().equalsIgnoreCase("AnyScope"))
{
BufferedReader br=request.getReader();
StringBuffer sb=new StringBuffer();
String b;
while(true)
{
b=br.readLine();
if(b==null) break;
sb.append(b);
}
String rowString=sb.toString();
String typeOfParameter1=requestParameterClass1.getType();
typeOfParameter1=typeOfParameter1.substring(6,typeOfParameter1.length());
Gson gson=new Gson();
Object oo=gson.fromJson(rowString,Class.forName(typeOfParameter1));
objectList1.add(oo);
}



}
if(requestParameterList1.size()==0)
{
String responseSend=(String)service.getService().invoke(object3);
if(responseSend!=null) pw.printf(responseSend);
}
else
{
String responseSend=(String)service.getService().invoke(object3,objectList1.toArray());
if(responseSend!=null) pw.printf(responseSend);
}
service.getService().invoke(object3);
}
}

if(service.getIsApplicationDirectory()==true)
{
File file=new File(request.getServletContext().getRealPath(""));
ApplicationDirectory applicationDirectory=new ApplicationDirectory(file);
Class c4=service.getServiceClass();
Method method4=c4.getMethod("setApplicationDirectory",ApplicationDirectory.class);
Object object4=c4.newInstance();
method4.invoke(object4,applicationDirectory);
service.getService().invoke(object4);
}

boolean sessionExists=service.getIsSessionScope();
boolean applicationExists=service.getIsApplicationScope();
boolean requestExists=service.getIsRequestScope();
boolean applicationDirectoryExists=service.getIsApplicationDirectory();
if(sessionExists==false && applicationExists==false && requestExists==false && applicationDirectoryExists==false)
{
Class c5=service.getServiceClass();
Method method5=service.getService();
Object object5=c5.newInstance();
if(service.getIsAutowired()==true)
{
List<AutowiredClass> autowiredClassList5=service.getAutowiredList();
if(autowiredClassList5.size()>0)
{
for(AutowiredClass autowiredClass5 : autowiredClassList5)
{
HttpSession httpSessionObject5=request.getSession();
Object objectAutowired5=httpSessionObject5.getAttribute(autowiredClass5.getName());
if(objectAutowired5!=null)
{
method5=c5.getMethod(autowiredClass5.getMethodName(),autowiredClass5.getType());
method5.invoke(object5,objectAutowired5);
}
ServletContext servletContextObject5=request.getServletContext();
objectAutowired5=servletContextObject5.getAttribute(autowiredClass5.getName());
if(objectAutowired5!=null)
{
method5=c5.getMethod(autowiredClass5.getMethodName(),autowiredClass5.getType());
method5.invoke(object5,objectAutowired5);
}
}
}
}

List<Object> objectList1=new ArrayList();
List<RequestParameterClass> requestParameterList1=service.getRequestParameterList();
String requestParameterString1;
for(RequestParameterClass requestParameterClass1 : requestParameterList1)
{
if(requestParameterClass1.getType().equalsIgnoreCase("class java.lang.String"))
{
requestParameterString1=request.getParameter(requestParameterClass1.getName());
if(requestParameterString1==null)
{
objectList1.add(null);
}
if(requestParameterString1!=null)
{
objectList1.add(requestParameterString1);
}
}
if(requestParameterClass1.getType().equalsIgnoreCase("int"))
{
requestParameterString1=request.getParameter(requestParameterClass1.getName());
if(requestParameterString1==null)
{
objectList1.add(0);
}
if(requestParameterString1!=null)
{
objectList1.add(Integer.parseInt(requestParameterString1));
}
}

if(requestParameterClass1.getType().equalsIgnoreCase("char"))
{
requestParameterString1=request.getParameter(requestParameterClass1.getName());
if(requestParameterString1==null)
{
objectList1.add(' ');
}
if(requestParameterString1!=null)
{
objectList1.add(requestParameterString1.charAt(0));
}
}

if(requestParameterClass1.getType().equalsIgnoreCase("double"))
{
requestParameterString1=request.getParameter(requestParameterClass1.getName());
if(requestParameterString1==null)
{
objectList1.add(0);
}
if(requestParameterString1!=null)
{
objectList1.add(Double.parseDouble(requestParameterString1));
}
}

if(requestParameterClass1.getType().equalsIgnoreCase("class com.thinking.machines.webrock.scope.SessionScope"))
{
SessionScope sessionScope5=new SessionScope();
HttpSession httpSession5=request.getSession();
sessionScope5.setAttribute(httpSession5);
objectList1.add(sessionScope5);
}
if(requestParameterClass1.getType().equalsIgnoreCase("class com.thinking.machines.webrock.scope.ApplicationScope"))
{
ApplicationScope applicationScope5=new ApplicationScope();
ServletContext servletContext5=request.getServletContext();
applicationScope5.setAttribute(servletContext5);
objectList1.add(applicationScope5);
}
if(requestParameterClass1.getType().equalsIgnoreCase("class com.thinking.machines.webrock.scope.RequestScope"))
{
RequestScope requestScope5=new RequestScope();
requestScope5.setAttribute(request);
objectList1.add(requestScope5);
}

if(requestParameterClass1.getName().equalsIgnoreCase("AnyScope") && !requestParameterClass1.getType().equalsIgnoreCase("class com.thinking.machines.webrock.scope.SessionScope")  && !requestParameterClass1.getType().equalsIgnoreCase("class com.thinking.machines.webrock.scope.ApplicationScope") && !requestParameterClass1.getType().equalsIgnoreCase("class com.thinking.machines.webrock.scope.RequestScope"))
{
BufferedReader br=request.getReader();
StringBuffer sb=new StringBuffer();
String b;
while(true)
{
b=br.readLine();
if(b==null) break;
sb.append(b);
}
String rowString=sb.toString();
String typeOfParameter1=requestParameterClass1.getType();
typeOfParameter1=typeOfParameter1.substring(6,typeOfParameter1.length());
Gson gson=new Gson();
Object oo=gson.fromJson(rowString,Class.forName(typeOfParameter1));
objectList1.add(oo);
}

}
if(requestParameterList1.size()==0)
{
String responseSend=(String)service.getService().invoke(object5);
if(responseSend!=null) pw.printf(responseSend);
}
else
{
String responseSend=(String)service.getService().invoke(object5,objectList1.toArray());
if(responseSend!=null) pw.printf(responseSend);
}
}




String forwardTo=null;
if(service.getForwardTo()!=null)
{
forwardTo=service.getForwardTo();
if(forwardTo.endsWith(".jsp"))
{
RequestDispatcher requestDispatcher=request.getRequestDispatcher(forwardTo);
requestDispatcher.forward(request,response);
}
Service forwardService=webRockModel.hashMapWeb.get(forwardTo);
if(forwardService==null)
{
response.sendError(HttpServletResponse.SC_NOT_FOUND);
}




}


}

}

if(urlExists==false)
{
response.sendError(HttpServletResponse.SC_NOT_FOUND);
}
}catch(Exception e)
{
System.out.println(e.getMessage());
}

}

}
