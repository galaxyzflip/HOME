/**
 * 
 */

function setId() {
	opener.document.userinput.id.value = document.checkForm.id.value;
	self.close();
}


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

function begin() {
	document.myform.id.focus();
}

function loginCheck() {
	if (!document.myform.id.value) {
		alert("이름을 입력하지 않으셨습니다.");
		document.myform.id.focus();
		return false;
	}
	if (!document.myform.passwd.value) {
		alert("비밀번호를 입력하지 않으셨습니다.");
		document.myform.passwd.focus();
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



 function sample6_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    
                
                } else {
                    
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample6_postcode').value = data.zonecode;
                document.getElementById("sample6_address").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("sample6_detailAddress").focus();
            }
        }).open();
    }










