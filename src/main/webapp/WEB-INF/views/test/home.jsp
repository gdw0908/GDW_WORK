<%@ page session="false" language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div style="width:800px; height:500px;padding:0px 0px 100px 186px; margin:0px 0px 50px 0px;">
<table class="ydpcmttl" cellpadding="0" cellspacing="0">
	<tr><td class="cmttl">* 관리자 관리</td></tr>
	<tr><td class="cmttlgra"></td></tr>
</table>
<table border="0" cellpadding="0" cellspacing="0" width="700" >
	 		<tr>
				<td>
	  <table class="bbslist" cellpadding="0" cellspacing="0" >
			<colgroup>
		<col width="1">
			<col width="1">
			<col width="120">
			<col width="120">
			<col width="150">
			<col width="*">
			<col width="50">
		</colgroup>
			<tr>
				<td style="background-color:#d8d8d8;"></td>
				<td class="listttl"> SiteCode </td>
				<td class="listttl"> 사이트명 </td>
				<td class="listttl"> 영문명</td>
				<td class="listttl"> 사이트분류 </td>
				<td class="listttl"> 사이트경로 </td>
				<td class="listttl"> 선택 </td>
			</tr>
			<tr><td colspan="100" class="ttlgra"></td></tr>
	  <c:forEach items="${list}" var="item">
	 	<align="center" bgcolor="#FFFFFF" height="30" onMouseOver="this.style.backgroundColor='#F0F0F0'" onMouseOut="this.style.backgroundColor='#FFFFFF'">
	 			<td class="ta11c"></td>
	   		<td class="ta11c">
	   			${item.idxId}
	   		</td>
			<td class="ta11c" nowrap>
				${item.korNm}
			</td>
			<td class="ta11c" nowrap>
				${item.engNm}
			</td>
			<td class="ta11c" nowrap>
				${item.categoryNm }
			</td>
			<td class="ta11c" nowrap>
				${item.url }
			</td>
			<td class="ta11c">
				<input type="radio" name="idxId" value="${item.idxId}" style="border:0">
			</td>
	   	</tr>
	   	<tr><td colspan="100" class="colline"></td></tr>
	  </c:forEach> 
			<tr><td colspan="100" class="colh03"></td></tr>
	  </table>
			</td>
		</tr>
</table>
</div>