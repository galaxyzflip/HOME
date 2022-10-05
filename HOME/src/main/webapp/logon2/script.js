/**
 * 
 */


function wrtieSave() {

	if (document.writeForm.writer.value == "") {
		alert("작성자를 입력하십시오.");
		document.writeForm.writer.focus();
		return false;
	}
	if (document.writeForm.subject.value == "") {
		alert("내용을 입력하십시오.");
		document.writeForm.subject.focus();
		return false;
	}
	if (document.writeForm.content.value == "") {
		alert("내용을 입력하십시오.");
		document.writeForm.content.focus();
		return false;
	}
	if (document.writeForm.passwd.value == "") {
		alert("비밀번호를 입력하십시오.");
		document.writeForm.passwd.focus();
		return false;
	}
}



function deleteSave() {
	if (document.delForm.passwd.value == '') {
		alert("비밀번호를 입력하십시요.");
		document.delForm.passwd.focus();
		return false;
	}
}   

function checkIt(){
	var userinput = eval("document.userinput");
	if(!userinput.id.value){
		alert("ID를 입력하세요");
		return false;
	}
	
	if(userinput.passwd.value != userinput.passwd2.value){
		alert("비밀번호를 동일하게 입력해주세요");
		return false;
	}
	
	if(!userinput.name.value){
		alert("이름 입력하세요");
		return false;
	}
	
	if(!userinput.phone.value){
		alert("휴대폰 번호를 입력하세요.");
		return false;
	}
	
}

function openConfirmId(userinput){
	if(userinput.id.value == ""){
		alert("아이디를 입력하세");
		return;
	}
	
	url = "/HOME/logon2/confirmId.do?id=" + userinput.id.value;
	
	open(url, "confirm", "toolbar=no,location=no,status=no,menubar=no,scrollbars=no,width=550,height=200");
	
}














