package com.thinking.machines.webrock.pojo;
import java.lang.reflect.*;
import java.util.*;
public class Service
{
private Class serviceClass;
private String url;
private Method service; 
private boolean isPostAllowed;
private boolean isGetAllowed;
private String forwardTo;
private boolean isSessionScope;
private boolean isApplicationScope;
private boolean isRequestScope;
private boolean isApplicationDirectory;
private boolean isAutowired;
private List<AutowiredClass> autowiredList;
private boolean isRequestParameter;
private List<RequestParameterClass> requestParameterList;
public void setServiceClass(Class serviceClass)
{
this.serviceClass=serviceClass;
}
public void setUrl(String url)
{
this.url=url;
}
public void setService(Method service)
{
this.service=service;
}

public Class getServiceClass()
{
return this.serviceClass;
}
public String getUrl()
{
return this.url;
}
public Method getService()
{
return this.service;
}
public void setIsPostAllowed(boolean isPostAllowed)
{
this.isPostAllowed=isPostAllowed;
}
public void setIsGetAllowed(boolean isGetAllowed)
{
this.isGetAllowed=isGetAllowed;
}
public boolean getIsPostAllowed()
{
return this.isPostAllowed;
}
public boolean getIsGetAllowed()
{
return this.isGetAllowed;
}
public void setForwardTo(String forwardTo)
{
this.forwardTo=forwardTo;
}
public String getForwardTo()
{
return this.forwardTo;
}
public void setIsSessionScope(boolean sessionScope)
{
this.isSessionScope=sessionScope;
}
public boolean getIsSessionScope()
{
return this.isSessionScope;
}
public void setIsApplicationScope(boolean applicationScope)
{
this.isApplicationScope=applicationScope;
}
public boolean getIsApplicationScope()
{
return this.isApplicationScope;
}
public void setIsRequestScope(boolean requestScope)
{
this.isRequestScope=requestScope;
}
public boolean getIsRequestScope()
{
return this.isRequestScope;
}
public void setIsApplicationDirectory(boolean applicationDirectory)
{
this.isApplicationDirectory=applicationDirectory;
}
public boolean getIsApplicationDirectory()
{
return this.isApplicationDirectory;
}
public void setIsAutowired(boolean autowired)
{
this.isAutowired=autowired;
}
public boolean getIsAutowired()
{
return this.isAutowired;
}
public void setAutowiredList(List<AutowiredClass> autowiredList)
{
this.autowiredList=autowiredList;
}
public List<AutowiredClass> getAutowiredList()
{
return this.autowiredList;
}
public void setIsRequestParameter(boolean isRequestParameter)
{
this.isRequestParameter=isRequestParameter;
}
public boolean getIsRequestParameter()
{
return this.isRequestParameter;
}
public void setRequestParameterList(List<RequestParameterClass> list)
{
this.requestParameterList=list;
}
public List<RequestParameterClass> getRequestParameterList()
{
return this.requestParameterList;
}
}
