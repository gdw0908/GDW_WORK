<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/common.jsp"%>
<form:form id="thisForm" name="thisForm" method="post" action="">
<div class="bbswrite">
<table class="ydpcmttl" cellpadding="0" cellspacing="0">
	<tr><td class="cmttl">&nbsp;&nbsp;&nbsp;관리자 관리</td></tr>
	<tr><td class="cmttlgra"></td></tr>
</table>
<table style="width:600px;" cellspacing="0" cellpadding="0">
	<tr><td colspan="2" class="colh03"></td></tr>
	<tr><td colspan="2" class="colline02"></td></tr>
	<tr height="30">
		<td><img src="/resources/images/common/ico_point.gif" align="absmiddle" alt="point">&nbsp;아이디</td>
		<td bgcolor="#FFFFFF" align="left">&nbsp;
			<input type="text" name="adminId" id="adminId" value="${item.adminId }">
		</td>
	</tr>
	<tr><td colspan="2" class="colline02"></td></tr>
	
	<tr height="30">
		<td><img src="/resources/images/common/ico_point.gif" align="absmiddle" alt="point">&nbsp;비밀번호</td>
		<td bgcolor="#FFFFFF">&nbsp;
		<%-- <input <c:if test="${admLvl == '1'}">type="text"</c:if> <c:if test="${admLvl != '1'}">type="password"</c:if> name="admPw" id="admPw" value="${item.admPw }"> --%>
			<input type="text" name="adminPass" id="adminPass" value="${item.adminPass }">
		</td>
	</tr>
	<tr><td colspan="2" class="colline02"></td></tr>
	
	<tr height="30">
		<td><img src="/resources/images/common/ico_point.gif" align="absmiddle" alt="point">&nbsp;이름</td>
		<td bgcolor="#FFFFFF">&nbsp;
			<input type="text" name="adminName" id="adminName" value="${item.adminName}">
		</td>
	</tr>
	<tr><td colspan="2" class="colline02"></td></tr>
	
	<tr height="30">
		<td><img src="/resources/images/common/ico_point.gif" align="absmiddle" alt="point">&nbsp;권한</td>
		<td bgcolor="#FFFFFF" >&nbsp; 
			<select name="adminGradeFlag" id="adminGradeFlag">
				<option value="">선택하세요</option>
				<option value="1" <c:if test="${item.adminGradeFlag eq '1' }">selected</c:if>>슈퍼관리자</option>
				<option value="2"<c:if test="${item.adminGradeFlag eq '2' }">selected</c:if>>서브관리자</option>						
			</select>
		</td>
	</tr>
	<tr><td colspan="2" class="colline02"></td></tr>
	
	<tr height="30">
		<td><img src="/resources/images/common/ico_point.gif" align="absmiddle" alt="point">&nbsp;사용유무</td>
		<td bgcolor="#FFFFFF">&nbsp;
			<input type="radio" name="adminUseYn" id="adminUseYn" value="Y"  <c:if test="${item.adminUseYn == 'Y' || empty item.adminUseYn }">checked</c:if>>사용&nbsp;
      		<input type="radio" name="adminUseYn" id="adminUseYn" value="N" <c:if test="${item.adminUseYn == 'N' }">checked</c:if>>사용안함
		</td>
	</tr>
	<tr><td colspan="2" class="colline02"></td></tr>
</table>
<!-- 버튼 -->
<div class="viewbtn" style="text-align:right;width:600px;">
	<table>
		<tr>
			<td align="right">
				<img src="/resources/images/common/ok.gif" alt="확인" title="확인" onclick="checkSubmit();" style="cursor:hand">
				<img src="/resources/images/common/cancel.gif" alt="취소" title="취소" onclick="goList();" style="cursor:hand">
			</td>
		</tr>
	</table>
</div>
<!--// 버튼 -->
</div>
</form:form>
<script type="text/javascript">
$(document).ready(function () {
    //initTooltip();
    //initDebitCard();
});

//등록 및 수정
function checkSubmit(){
  if($("#adminId").val() == ""){
      alert("아이디를 입력해주세요.");
      $("#adminId").focus();
      return false;
  }else if($("#adminPass").val() == ""){
      alert("비밀번호를 입력해주세요.");
      $("#adminPass").focus();
      return false;
  }else if($("#adminName").val() == ""){
      alert("이름을 입력해주세요.");
      $("#adminName").focus();
      return false;
  }else if($("#adminGradeFlag").val() == ""){
      alert("슈퍼관리자 권한을 선택해주세요.");
      $("#adminGradeFlag").focus();
      return false;
  }else if($("#adminUseYn").val() == ""){
      alert("관리자 사용 여부를 선택해주세요.");
      $("#adminUseYn").focus();
      return false;
  }
  var ment = ('${item}' == "") ? "관리자를 등록하시겠습니끼?" : "관리자를 수정하시겠습니까?";
  check = confirm(ment);
  if(check){
	  goAdminRegist();
  }else return false;
}

function goAdminRegist(){
	ajax.callAjax("/admin/registAdminInfo.dw","post", ajax.formSerialize($("#thisForm")),
			adminRegistCallback,"json",false);
}

function adminRegistCallback(data){
	if(data.resultCode == "0000"){
		goList();	
	}
}

function goList(){
	$("#adminId").val("");
	$("#thisForm").attr({
		"action":"/admin/adminList.dw"
		,"method":"post"
	}).submit();	
}
</script>