package com.thinking.machines.webrock.scope;
import javax.servlet.*;
import javax.servlet.http.*;
public class SessionScope
{
private HttpSession httpSession;
public void setAttribute(String key,Object object)
{
this.httpSession.setAttribute(key,object);
}
public void setAttribute(HttpSession httpSession)
{
this.httpSession=httpSession;
} 
public Object getAttribute(String key)
{
return this.httpSession.getAttribute(key);
}

}