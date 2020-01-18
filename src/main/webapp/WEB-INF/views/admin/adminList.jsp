<%@ page session="false" language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/common.jsp"%>
<div class="bbslistdiv">
<table class="ydpcmttl" cellpadding="0" cellspacing="0">
	<tr><td class="cmttl">&nbsp;&nbsp;&nbsp;관리자 관리</td></tr>
	<tr><td class="cmttlgra"></td></tr>
</table>
<table border="0" cellpadding="0" cellspacing="0" width="100%" >
	 		<tr>
				<td>
	  <table class="bbslist" cellpadding="0" cellspacing="0" >
		<colgroup>
			<col width="0">
			<col width="*">
			<col width="120">
			<col width="80">
			<col width="60">
			<col width="80">
			<col width="80">
			<col width="80">
			<col width="80">
			<col width="80">
		</colgroup>
			<tr>
				<td style="background-color:#d8d8d8;"></td>
				<td class="listttl"> ID </td>
				<td class="listttl"> 이름 </td>
				<td class="listttl"> 상태</td>
				<td class="listttl"> 사용여부</td>
				<td class="listttl"> 등록자 </td>
				<td class="listttl"> 등록일 </td>
				<td class="listttl"> 수정자 </td>
				<td class="listttl"> 수정일 </td>
				<td class="listttl"> 수정/삭제 </td>
			</tr>
			<tr><td colspan="100" class="ttlgra"></td></tr>
	  <c:forEach items="${list}" var="item">
	 	<tr align="center" bgcolor="#FFFFFF" height="30" onMouseOver="this.style.backgroundColor='#F0F0F0'" onMouseOut="this.style.backgroundColor='#FFFFFF'">
			<td class="ta11c"></td>
			<td class="ta11c">
				${item.adminId}
			</td>
	   		<td class="ta11c">
	   			${item.adminName}
	   		</td>
			<td class="ta11c" nowrap>
				<c:choose>
					<c:when test="${item.adminGradeFlag eq '1'}">
						슈퍼관리자
					</c:when>
					<c:otherwise>
						서브관리자
					</c:otherwise>
				</c:choose>
			</td>
			<td class="ta11c" nowrap>
				<c:choose>
					<c:when test="${item.adminUseYn eq 'Y'}">
						사용
					</c:when>
					<c:otherwise>
						사용안함
					</c:otherwise>
				</c:choose>
			</td>
			<td class="ta11c" nowrap>
				${item.regUserid }
			</td>
			<td class="ta11c" nowrap>
				${item.regDttime}
			</td>
			<td class="ta11c" nowrap>
				${item.modUserid }
			</td>
			<td class="ta11c" nowrap>
				${item.modDttime }
			</td>
			<td class="ta12c">
		      <img src="/resources/images/common/modify_icon.gif" onclick="modAdmin('${item.adminId}');" onkeypress="javascript:modAdmin('${item.adminId}');" style="cursor:hand;" alt="수정" title="수정" border="0">
		      <img src="/resources/images/common/delete_icon.gif" onclick="delAdmin('${item.adminId}');" onkeypress="javascript:delAdmin('${item.adminId}');" style="cursor:hand;" alt="삭제하기" title="삭제하기" border="0">
		    </td>
	   	</tr>
	   	<tr><td colspan="100" class="colline"></td></tr>
	  </c:forEach> 
			<tr><td colspan="100" class="colh03"></td></tr>
	  </table>
			</td>
		</tr>
</table>

<!-- 페이징 -->
<jsp:include page="/WEB-INF/views/common/page.jsp"/>
<!--// 페이징 -->
<table class="bbslist"  cellpadding="0" cellspacing="0" summary="관라자 목록">
			<tr>
				<td align="center" height="30"> </td>
			</tr>
			<tr>
				<td align="right" height="30"> <img src="/resources/images/common/regist.gif" alt="등록" title="등록" onclick="return goPage('reg')" onkeypress="return goPage('reg')" style="cursor:hand" /> </td>
			</tr>
</table>
<!-- 검색 -->
<div class="listsear">
<form:form id="thisForm" name="thisForm" action="">
	<input type="hidden" id="adminId" name="adminId" />
	<input type="hidden" id="adminNm" name="adminNm" />
	<input type="hidden" id="page" name="page" />
<table cellpadding="0" cellspacing="0">
  <tr>
    <td><img src="/resources/images/common/ico_sear.gif" alt="검색아이콘" title="검색아이콘">&nbsp;</td>
    <td>
      <select name="searchType" id="searchType" style="width:100px;" class="select">
        <option value="1" title="ID으로 검색" <c:if test="${param.searchType eq '1' }">selected</c:if>>ID</option>
        <option value="2" title="이름으로 검색" <c:if test="${param.searchType eq '2' }">selected</c:if>>이름</option>
      </select>
    </td>
    <td>
 			<div id="selectSearchType1">
				<input type="text" size="20" maxlength="15" id="searchValue" name="searchValue" value="${param.searchValue}">
			</div>
    </td>
    <td>
    	<img src="/resources/images/common/search.gif" alt="검색" title="검색" onclick="javascript:searchList();" style="cursor:hand;">
 		<c:if test="${!empty param.searchValue}">	
			<img src="/resources/images/common/searchInit.gif" alt="초기화" title="초기화" onclick="initSearch();" style="cursor:hand" />
		</c:if>
    </td>

  </tr>
</table>
</form:form>
</div>
<!-- 검색 -->
</div>
<script type="text/javascript">
/* function loginChk(){
	$("#adminId").val($("#id").val()); 		//관리자 ID
	$("#adminPw").val($("#passwd").val()); 	//관리자 패스워드
	ajax.callAjax("/login/loginChk.dw","post", ajax.formSerialize($("#thisForm")),loginChkCallback,"json",false);
} */
function nextPaging(page){
	$("#page").val(page);
	$("#thisForm").attr({
		"action":"/admin/adminList.dw"
		,"method":"post"
	}).submit();
}
function searchList(){
	if($("#searchType").val() == "1"){
		$("#adminId").val($("#searchValue").val()); 
	}else if($("#searchType").val() == "2"){
		$("#adminNm").val($("#searchValue").val());
	}
	$("#thisForm").attr({
		"action":"/admin/adminList.dw"
		,"method":"post"
	}).submit();
}

function initSearch(){
	$("#searchType").val('');
	$("#searchValue").val('');
	$("#adminId").val('');
	$("#adminNm").val('');
	
	$("#thisForm").attr({
		"action":"/admin/adminList.dw"
		,"method":"post"
	}).submit();
}
function modAdmin(admId){
	$("#adminId").val(admId);
	goPage("reg");
}

function delAdmin(admId){
	if(confirm("관리자를 삭제 하시겠습니까?")){
		$("#adminId").val(admId);
		goAdminDelete();
	}
	return;
}
//관리자 삭제
function goAdminDelete(){
	ajax.callAjax("/admin/deleteAdminInfo.dw","post", ajax.formSerialize($("#thisForm")),
			deleteAdminInfoCallback,"json",false);
}
function deleteAdminInfoCallback(data){
	if(data.resultCode == "0000"){
		initSearch();
	}
}
function goPage(val){
	if(val == "reg"){
		$("#thisForm").attr({
			"action":"/admin/adminRegist.dw"
			,"method":"post"
		}).submit();
	}else{
		alert("회원정보가 일치하지 않습니다.\n아이디와 패스워드를 확인해주세요.");
	}
	return;
}

</script>