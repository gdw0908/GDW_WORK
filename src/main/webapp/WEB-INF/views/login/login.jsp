<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>GDW CMS</title>
<!-- <link rel="stylesheet" href="/css/hcms_style.css" type="text/css"> -->
<style type="text/css">
	img {border:0px;}
	.txtinp {border:1px solid #cfd0d4; color:#000; font-size:11px; padding:2px; height:18px;}
</style>
</head>
<body>
<table width="100%" height="90%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td> <table width="600" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr> 
          <td height="1" colspan="3" bgcolor="#E3E3E3"></td>
        </tr>
        <tr background="/resources/images/login/t_a_login_bg.gif"> 
          <td colspan="3" background="/resources/images/login/t_a_login_bg.gif"><input type="image" src="/resources/images/login/t_a_login.gif"></td>
        </tr>
        <tr align="center" valign="bottom"> 
          <td height="40" colspan="3" bgcolor="F4F4F4"><input type="image" src="/resources/images/login/t_4.gif"></td>
        </tr>
        <tr> 
          <td width="346" height="120" align="right" bgcolor="F4F4F4"><table width="192" border="0" cellpadding="0" cellspacing="3">
              <tr> 
                <td width="62"><input type="image" src="/resources/images/login/id.gif" width="52" height="13"></td>
                <td width="163" align="center"><input type="text" name = "id" id="id" class="box"></td>
              </tr>
              <tr> 
                <td><input type="image" src="/resources/images/login/pw.gif" width="52" height="13"></td>
                <td align="center"><input type="password" name = "passwd" id="passwd" class="box"></td>
              </tr>
            </table>
          </td>
          <td width="11" bgcolor="F4F4F4">&nbsp;</td>
          	<td width="243" bgcolor="F4F4F4">
          		<a style="cursor:hand" onClick="loginChk()"> 
	            	<input type="image" src="/resources/images/login/bt_login.gif" name="Image6" width="61" height="61" />
	            </a>
         	</td>
        </tr>
        <tr> 
          <td height="1" colspan="3" bgcolor="#E3E3E3"></td>
        </tr>
      </table>
      <br>
      <table width="100" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr> 
          <td><input type="image" src="/resources/images/login/t_1.gif" width="609" height="91" border="0"></td>
        </tr>
      </table> 
	  </td>
   </tr>
</table>
<form:form name="thisForm" id="thisForm" method="post">
	<input type="hidden" name="adminId"  id="adminId">
	<input type="hidden" name="adminPw"  id="adminPw">
	<input type="hidden" name="loginDupleYn"  id="loginDupleYn">
</form:form>
<script type="text/javascript">
function loginChk(){
	if($("#id").val() == ""){
		alert("관리자ID를 입력해 주세요.");
		$("#id").focus();
		return;
	}
	if($("#passwd").val() == ""){
		alert("관리자 패스워드를 입력해 주세요.");
		$("#passwd").focus();
		return;
	}
	$("#adminId").val($("#id").val()); 		//관리자 ID
	$("#adminPw").val($("#passwd").val()); 	//관리자 패스워드
	ajax.callAjax("/login/loginChk.dw","post", ajax.formSerialize($("#thisForm")),loginChkCallback,"json",false);
}

function loginChkCallback(data){
	if(data.loginDupleYn == "Y"){
		if(confirm("동일한 아이디로 접속중입니다. 재로그인 하시겠습니까??")){
			$("#loginDupleYn").val("R"); 		//관리자 ID		
			loginChk();
		}else{
			return;
		}
	}else{
		if(data.loginYn == "Y"){
			$("#thisForm").attr({
				"action":"/main/home.dw"
				,"method":"post"
			}).submit();
		}else{
			alert("회원정보가 일치하지 않습니다.\n아이디와 패스워드를 확인해주세요.");
		}		
	}
	return;
}
</script>
</BODY>
</html>