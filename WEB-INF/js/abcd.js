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
getAll()
{
var psm=new Promise((done)=>{
$(()=>{
$.ajax({
type : "GET",
url : "/TMWebRock1/SchoolService/studentService/GetAll",
success : function(result){
var object=JSON.parse(result);
done(object);
}
});
});
});
return psm;
}

add(student)
{
var tranferData={
id:student.id,
name:student.name
};
var jsonString=JSON.stringify(tranferData);
var psm=new Promise((done)=>{
$(()=>{
$.ajax({
type:'Post',
url:'/TMWebRock1/SchoolService/studentService/Add',
data: jsonString,
success : function(result){
alert(result);
done();
}
});
});
});
return psm;
}

update(student)
{
var transferData={
id:student.id,
name:student.name
}
var jsonString=JSON.stringify(transferData);
var psm=new Promise((done)=>{
$(()=>{
$.ajax({
type:'Post',
url:'/TMWebRock1/SchoolService/studentService/Update',
data:jsonString,
success : function(data){
done();
}
});
});
});
return psm;
}

delete(id)
{
var psm=new Promise((done)=>{
$(()=>{
$.ajax({
type:'Post',
url:'/TMWebRock1/SchoolService/studentService/Delete?id='+id+'',
success : function(){
done();
}
});
});
});
return psm;
}


getById(id)
{
var psm=new Promise((done)=>{
$(()=>{
$.ajax({
type:'Get',
url:'/TMWebRock1/SchoolService/studentService/GetById?id='+id+'',
success : function(result){
var object=JSON.parse(result);
$("#rollNumberSpan").text(object.id);
$("#nameSpan").text(object.name);
}
});
});
});
return psm;
}
}
