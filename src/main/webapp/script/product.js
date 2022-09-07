/* 정보폼에서 필수입력 받게하는 함수 **/
function productCheck(){
	if(document.frm.name.value.lenth == 0){
		alert("상품명을 써주세요.");
		frm.name.focus();
		
		return false;
	}
	
	if(document.frm.price.value.lenth == 0){
		alert("가격을 써주세요.");
		frm.price.focus();
		
		return false;
	}
	
	if(isNaN(document.frm.price.value)){
		alert("숫자를 입력해야합니다.");
		frm.price.focus();
		
		return false;
	}
}