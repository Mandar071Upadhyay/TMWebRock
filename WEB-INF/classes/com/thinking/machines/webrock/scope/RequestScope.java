package com.thinking.machines.webrock.scope;
import javax.servlet.*;
import javax.servlet.http.*;
public class RequestScope
{
private HttpServletRequest httpServletRequest;
public void setAttribute(String key,Object object)
{
this.httpServletRequest.setAttribute(key,object);
}
public void setAttribute(HttpServletRequest httpServletRequest)
{
this.httpServletRequest=httpServletRequest;
} 
public Object getAttribute(String key)
{
return this.httpServletRequest.getAttribute(key);
}

}