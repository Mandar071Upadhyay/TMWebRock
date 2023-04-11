package bobby;
import com.thinking.machines.webrock.annotation.*;
import com.thinking.machines.webrock.scope.*;
import java.sql.*;
import com.google.gson.*;
import java.util.*;
@Path("/studentService")
public class StudentService
{
@Get
@Path("/GetAll")
public String getAll()
{
try
{
Class.forName("com.mysql.cj.jdbc.Driver");
Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/car","root","mandar");
PreparedStatement preparedStatement=c.prepareStatement("select * from student");
ResultSet resultSet=preparedStatement.executeQuery();
List<Student> students=new ArrayList();
int id;
String name;
Student student;
while(resultSet.next())
{
id=resultSet.getInt("id");
name=resultSet.getString("name");
student=new Student();
student.setId(id);
student.setName(name);
students.add(student);
}
Gson gson=new Gson();
return gson.toJson(students);
}catch(Exception e)
{
}
return "";
}
@Post
@Path("/Add")
public String add(Student student)
{
if(student==null) return "Null Pointer";
try
{
Class.forName("com.mysql.cj.jdbc.Driver");
Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/car","root","mandar");
PreparedStatement preparedStatement=c.prepareStatement("insert into student (id,name) values (?,?)");
preparedStatement.setInt(1,student.getId());
preparedStatement.setString(2,student.getName());
preparedStatement.executeUpdate();
}catch(Exception e)
{
System.out.println(e.getMessage());
}
Gson gson=new Gson();
return gson.toJson(student);
}

@Post
@Path("/Update")
public void update(Student student)
{
if(student==null) return;
try
{
Class.forName("com.mysql.cj.jdbc.Driver");
Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/car","root","mandar");
PreparedStatement preparedStatement=c.prepareStatement("update student set name=? where id=?");
preparedStatement.setString(1,student.getName());
preparedStatement.setInt(2,student.getId());
preparedStatement.executeUpdate();
}catch(Exception e)
{
System.out.println(e.getMessage());
}
}

@Post
@Path("/Delete")
public void delete(@RequestParameter("id") int id)
{
if(id==0) return;
System.out.println("yes this is working : "+id);
try
{
Class.forName("com.mysql.cj.jdbc.Driver");
Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/car","root","mandar");
PreparedStatement preparedStatement=c.prepareStatement("delete from student where id=?");
preparedStatement.setInt(1,id);
preparedStatement.executeUpdate();
}catch(Exception e)
{
System.out.println(e.getMessage());
}
}


@Get
@Path("/GetById")
public String getById(@RequestParameter("id") int id)
{
Student student=new Student();
try
{
Class.forName("com.mysql.cj.jdbc.Driver");
Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/car","root","mandar");
PreparedStatement preparedStatement=c.prepareStatement("select * from student where id=?");
preparedStatement.setInt(1,id);
ResultSet resultSet=preparedStatement.executeQuery();
while(resultSet.next())
{
student.setId(resultSet.getInt("id"));
student.setName(resultSet.getString("name"));
}
resultSet.close();
preparedStatement.close();
c.close();
}catch(Exception e)
{
System.out.println(e.getMessage());
}
Gson gson=new Gson();
return gson.toJson(student);

}



}