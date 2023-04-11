package com.thinking.machines.webrock.pojo;
import java.lang.reflect.*;
public class AutowiredClass
{
private String name;
private Type type;
private String methodName;
public void setName(String name)
{
this.name=name;
}
public void setType(Type type)
{
this.type=type;
}
public void setMethodName(String methodName)
{
this.methodName=methodName;
}
public String getName()
{
return this.name;
}
public Class getType()
{
return (Class)this.type;
}
public String getMethodName()
{
return this.methodName;
}
}