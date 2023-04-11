class Employee
{
constructor(salary,name)
{
this.salary=salary;
this.name=name;
}
}

class Student
{
constructor(id,name)
{
this.id=id;
this.name=name;
}
}

class StudentService
{
add(Student)
{
var psm=new Promise((done)=>{
$(()=>{
$.ajax(
{
type : 'Post',
url : '/TMWebRock1/SchoolService/studentService/Add,
success : function(result){
Object obj=JSON.parse(result); 
done(obj); 
} 
}); 
}); 
});
return psm
}
update(Student)
{
var psm=new Promise((done)=>{
$(()=>{
$.ajax(
{
type : 'Post',
url : '/TMWebRock1/SchoolService/studentService/Update,
success : function(result){
Object obj=JSON.parse(result); 
done(obj); 
} 
}); 
}); 
});
return psm
}
delete(id)
{
var psm=new Promise((done)=>{
$(()=>{
$.ajax(
{
type : 'Post',
url : '/TMWebRock1/SchoolService/studentService/Delete?id='+id+'',
success : function(result){
Object obj=JSON.parse(result); 
done(obj); 
} 
}); 
}); 
});
return psm
}
getAll()
{
var psm=new Promise((done)=>{
$(()=>{
$.ajax(
{
type : 'Get',
url : /TMWebRock1/SchoolService/studentService/GetAll,
success : function(result){
Object obj=JSON.parse(result); 
done(obj); 
} 
}); 
}); 
});
return psm
}
getById(id)
{
var psm=new Promise((done)=>{
$(()=>{
$.ajax(
{
type : 'Get',
url : '/TMWebRock1/SchoolService/studentService/GetById?id='+id+'',
success : function(result){
Object obj=JSON.parse(result); 
done(obj); 
} 
}); 
}); 
});
return psm
}
}
