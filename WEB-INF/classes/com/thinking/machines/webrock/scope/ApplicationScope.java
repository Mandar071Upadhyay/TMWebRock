package com.thinking.machines.webrock.scope;
import javax.servlet.*;
import javax.servlet.http.*;
public class ApplicationScope
{
private ServletContext servletContext;
public void setAttribute(String key,Object object)
{
this.servletContext.setAttribute(key,object);
}
public void setAttribute(ServletContext servletContext)
{
this.servletContext=servletContext;
} 
public Object getAttribute(String key)
{
return this.servletContext.getAttribute(key);
}

}