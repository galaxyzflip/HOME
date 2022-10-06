
//MVC 게시판 글 업로드시 체크
function wrtieSave() {

	if (document.writeForm.guestName.value == "") {
		alert("작성자를 입력하십시오.");
		document.writeForm.guestName.focus();
		return false;
	}
	if (document.writeForm.password.value == "") {
		alert("제목을 입력하십시오.");
		document.writeForm.password.focus();
		return false;
	}
	if (document.writeForm.message.value == "") {
		alert("내용을 입력하십시오.");
		document.writeForm.message.focus();
		return false;
	}

}


function deleteSave() {
	if (document.writeForm.password.value == "") {
		alert("제목을 입력하십시오.");
		document.writeForm.password.focus();
		return false;
	}
}
