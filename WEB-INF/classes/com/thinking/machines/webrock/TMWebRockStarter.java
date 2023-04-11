package com.thinking.machines.webrock;
import com.thinking.machines.webrock.annotation.*;
import com.thinking.machines.webrock.model.*;
import com.thinking.machines.webrock.pojo.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.lang.reflect.*;
public class TMWebRockStarter extends HttpServlet
{
private List<String> allFilePresentList;
public void init()
{
try
{
allFilePresentList=new ArrayList();
ServletContext context=getServletContext();
ServletConfig servletConfig=getServletConfig();
WebRockModel webRockModel=new WebRockModel();
String scaneOnlyPath=servletConfig.getInitParameter("packageName");
File currentDir = new File(context.getRealPath(""));
displayDirectoryContents(currentDir);
for(String allFilePresent : allFilePresentList)
{
int lengthOfFile=allFilePresent.length();
try
{
allFilePresent=allFilePresent.substring(46,lengthOfFile);
}catch(Exception e)
{
continue;
}
if(allFilePresent==null)
{
allFilePresent="";
continue;
}
String packageOnlyScan=allFilePresent;
if(packageOnlyScan.startsWith(scaneOnlyPath) && packageOnlyScan.endsWith(".class"))
{
packageOnlyScan=packageOnlyScan.substring(0,packageOnlyScan.length()-".class".length());
packageOnlyScan=packageOnlyScan.replace('\\','.');
Class c=Class.forName(packageOnlyScan);
Path pathOnClass=(Path)c.getAnnotation(Path.class); 
Get getOnClass=(Get)c.getAnnotation(Get.class);
Get getOnMethod=null;
Post postOnClass=(Post)c.getAnnotation(Post.class);
Post postOnMethod=null;
Path pathOnMethod=null;
InjectSessionScope injectSessionScope=(InjectSessionScope)c.getAnnotation(InjectSessionScope.class);
InjectApplicationScope injectApplicationScope=(InjectApplicationScope)c.getAnnotation(InjectApplicationScope.class);
InjectRequestScope injectRequestScope=(InjectRequestScope)c.getAnnotation(InjectRequestScope.class);
InjectApplicationDirectory injectApplicationDirectory=(InjectApplicationDirectory)c.getAnnotation(InjectApplicationDirectory.class);
Autowired autowiredAnnotationOnField=null;
RequestParameter requestParameterAnnotationOnParameter=null;
if(pathOnClass!=null)
{
Method [] methods=c.getDeclaredMethods();
for(Method method : methods)
{
pathOnMethod=(Path)method.getAnnotation(Path.class);
if(pathOnMethod!=null)
{
String pathClassValue=pathOnClass.value();
String pathMethodValue=pathOnMethod.value();
Service service=new Service();
if(injectSessionScope!=null) service.setIsSessionScope(true);
if(injectApplicationScope!=null) service.setIsApplicationScope(true);
if(injectRequestScope!=null) service.setIsRequestScope(true);
if(injectApplicationDirectory!=null) service.setIsApplicationDirectory(true);
getOnMethod=(Get)method.getAnnotation(Get.class);
postOnMethod=(Post)method.getAnnotation(Post.class);
if(getOnMethod!=null) service.setIsGetAllowed(true);
if(postOnMethod!=null) service.setIsPostAllowed(true);
if(getOnClass!=null) service.setIsGetAllowed(true);
if(postOnClass!=null) service.setIsPostAllowed(true);
Forward forwardOnMethod=(Forward)method.getAnnotation(Forward.class);
if(forwardOnMethod!=null) service.setForwardTo(forwardOnMethod.value());
service.setServiceClass(c);
service.setUrl(pathClassValue+pathMethodValue);
service.setService(method);
Field fields[]=c.getDeclaredFields();
List<AutowiredClass> autowiredList=new ArrayList();
AutowiredClass autowiredClass;
for(Field field : fields)
{
autowiredAnnotationOnField=(Autowired)field.getAnnotation(Autowired.class);
if(autowiredAnnotationOnField!=null)
{
autowiredClass=new AutowiredClass();
service.setIsAutowired(true);
autowiredClass.setName(autowiredAnnotationOnField.name());
autowiredClass.setMethodName("set"+Character.toUpperCase(field.getName().charAt(0))+field.getName().substring(1,field.getName().length()));
autowiredClass.setType(field.getType());
autowiredList.add(autowiredClass);
}
}
service.setAutowiredList(autowiredList);
Parameter parameters[]=method.getParameters();
RequestParameterClass requestParameterClass;
List<RequestParameterClass> requestParameterList=new ArrayList();
for(int e=0;e<parameters.length;e++)
{
requestParameterAnnotationOnParameter=(RequestParameter)parameters[e].getAnnotation(RequestParameter.class);
requestParameterClass=new RequestParameterClass();
if(requestParameterAnnotationOnParameter!=null)
{
requestParameterClass.setName(requestParameterAnnotationOnParameter.value());
}else
{
requestParameterClass.setName("AnyScope");
}
requestParameterClass.setType(""+parameters[e].getType());
requestParameterList.add(requestParameterClass);
}
service.setRequestParameterList(requestParameterList);
webRockModel.hashMapWeb.put(pathClassValue+pathMethodValue,service);
}

}
}
}
}
createAllClassesJavaScript();
context.setAttribute("webRockModel",webRockModel);
System.out.println("_________________________________");
}catch(Exception e)
{

}

}

private void displayDirectoryContents(File dir) {
try {
File[] files = dir.listFiles();
for (File file : files)
{
if (file.isDirectory())
{
allFilePresentList.add(file.getCanonicalPath());
displayDirectoryContents(file);
} 
else
{
allFilePresentList.add(file.getCanonicalPath());
}
}
} 
catch (IOException e)
{
e.printStackTrace();
}
}

public void createAllClassesJavaScript()
{
String name="pqrs.js";
ServletContext servletContext=getServletContext();
String path=servletContext.getRealPath("")+File.separator+"WEB-INF"+File.separator+"js"+File.separator+name;
String urlString="/TMWebRock1/SchoolService";

File file=new File(path);
if(file.exists()) file.delete();
try
{
ServletConfig servletConfig=getServletConfig();
String scaneOnlyPath=servletConfig.getInitParameter("packageName");
for(String allFiles : allFilePresentList)
{
try
{
allFiles=allFiles.substring(46,allFiles.length());
}catch(Exception e)
{
continue;
}
if(allFiles.startsWith(scaneOnlyPath) && allFiles.endsWith(".class"))
{
allFiles=allFiles.replace('\\','.').substring(0,allFiles.length()-".class".length());
Class c=Class.forName(allFiles);
Path pathAnnotationOnClass=(Path)c.getAnnotation(Path.class);
if(pathAnnotationOnClass!=null)
{
System.out.println(c.getSimpleName());
Method[] methods=c.getDeclaredMethods();
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()>0)
{
while(randomAccessFile.getFilePointer()<randomAccessFile.length()) randomAccessFile.readLine();
}
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes("class "+c.getSimpleName()+"\n");
randomAccessFile.writeBytes("{"+"\n");
Path pathAnnotationOnMethod=null;
String p="\n";
String dataClass=null;
for(Method method : methods)
{
pathAnnotationOnMethod=(Path) method.getAnnotation(Path.class);
if(pathAnnotationOnMethod!=null)
{
Parameter [] parameters=method.getParameters();
randomAccessFile.writeBytes(method.getName()+"(");
int i=0;
if(parameters.length>0)
{
for(Parameter parameter : parameters)
{
RequestParameter requestParameter=(RequestParameter) parameter.getAnnotation(RequestParameter.class);
i++;
if(requestParameter!=null)
{
randomAccessFile.writeBytes(requestParameter.value());
if(i>parameters.length)
{
randomAccessFile.writeBytes(",");
}
}else
{
dataClass=""+parameter.getType().getSimpleName();
randomAccessFile.writeBytes(""+parameter.getType().getSimpleName());
if(i>parameters.length)
{
randomAccessFile.writeBytes(",");
}
}
}
randomAccessFile.writeBytes(")"+"\n");
}

if(parameters.length==0)
{
randomAccessFile.writeBytes(")"+"\n");
}
randomAccessFile.writeBytes("{"+"\n");
Post pp=(Post)method.getAnnotation(Post.class);
pp=(Post)c.getAnnotation(Post.class);
if(pp!=null)
{

}

randomAccessFile.writeBytes("var psm=new Promise((done)=>{");
randomAccessFile.writeBytes(""+p+"$(()=>{"+p+"$.ajax("+p+"{");
Post post=(Post)c.getAnnotation(Post.class);
Get get=(Get)c.getAnnotation(Get.class);
if(get!=null)
{
randomAccessFile.writeBytes(""+p+"type : 'Get',");
}
if(post!=null)
{
randomAccessFile.writeBytes(""+p+"type : 'post',");
}
Post postMethod=(Post)method.getAnnotation(Post.class);
if(postMethod!=null)
{
randomAccessFile.writeBytes(""+p+"type : 'Post',");
}
Get getMethod=(Get)method.getAnnotation(Get.class);
if(getMethod!=null)
{
randomAccessFile.writeBytes(""+p+"type : 'Get',");
}

parameters=method.getParameters();
i=0;
if(parameters.length>0)
{
randomAccessFile.writeBytes(""+p+"url : '"+urlString+""+pathAnnotationOnClass.value()+""+pathAnnotationOnMethod.value()+""); 
for(Parameter parameter : parameters)
{
RequestParameter requestParameter=(RequestParameter) parameter.getAnnotation(RequestParameter.class);
if(requestParameter!=null)
{
if(i==0)
{
randomAccessFile.writeBytes("?"+requestParameter.value()+"="+"'+"+requestParameter.value()+"+''"); 
}else
{
randomAccessFile.writeBytes("&"+requestParameter.value()+"="+"'+"+requestParameter.value()+"+''"); 
}
} 
i++;
}
randomAccessFile.writeBytes(","); 
}
else
{
randomAccessFile.writeBytes(""+p+"url : "+urlString+""+pathAnnotationOnClass.value()+""+pathAnnotationOnMethod.value()+""+","); 
}
randomAccessFile.writeBytes(""+p+"success : function(result){"+p+"Object obj=JSON.parse(result); "+p+"done(obj); "+p+"} "+p+"}); "+p+"}); "+p+"});");

randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes("return psm"+"\n");
randomAccessFile.writeBytes("}"+"\n");
}
}
randomAccessFile.writeBytes("}"+"\n");
}else
{
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()>0)
{
while(randomAccessFile.getFilePointer()<randomAccessFile.length()) randomAccessFile.readLine();
randomAccessFile.writeBytes("\n");
}
randomAccessFile.writeBytes("class "+c.getSimpleName()+"\n");
randomAccessFile.writeBytes("{"+"\n");
randomAccessFile.writeBytes("constructor(");
Field [] fields=c.getDeclaredFields();
int i=0;
for(Field field : fields)
{
i++;
randomAccessFile.writeBytes(field.getName());
if(i<fields.length)
{
randomAccessFile.writeBytes(",");
}
}
randomAccessFile.writeBytes(")"+"\n");
randomAccessFile.writeBytes("{"+"\n");
for(Field field : fields)
{
randomAccessFile.writeBytes("this."+field.getName()+"="+field.getName()+";"+"\n");
}
randomAccessFile.writeBytes("}"+"\n");
randomAccessFile.writeBytes("}"+"\n");
randomAccessFile.close();
}
}

}
}catch(Exception e)
{
System.out.println(e.getMessage());
}

}


}