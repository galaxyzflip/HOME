/**
 * 
 */
 
 
 function wrtieSave(){
	
	if(document.writeForm.writer.value==""){
		alert("작성자를 입력하십시오.");
		document.writeForm.writer.focus();
		return false;
	}
	if(document.writeForm.subject.value==""){
		alert("내용을 입력하십시오.");
		document.writeForm.subject.focus();
		return false;
	}
	if(document.writeForm.content.value==""){
		alert("내용을 입력하십시오.");
		document.writeForm.content.focus();
		return false;
	}
	if(document.writeForm.passwd.value==""){
		alert("비밀번호를 입력하십시오.");
		document.writeForm.passwd.focus();
		return false;
	}
}



  function deleteSave(){
if(document.delForm.passwd.value==''){
alert("비밀번호를 입력하십시요.");
document.delForm.passwd.focus();
return false;
}
}   