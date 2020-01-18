<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@ include file="/WEB-INF/views/common/common.jsp"%>
<!-- 홈페이지 | 게시물관리 | 접수등록 | 직원조직 | CMS관리 -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>▒▒▒  CMS  ▒▒▒</title>
<link rel="stylesheet" href="/resources/css/common.css" type="text/css">
<link rel="stylesheet" href="/resources/css/contents.css" type="text/css">
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript" src="/resources/js/common.js"></script>
<!-- default header name is X-CSRF-TOKEN -->
<meta id="_csrf_header" name="_csrf_header" content="${_csrf.headerName}"/>
<meta id="_csrf" name="_csrf" content="${_csrf.token}"/>

</head>
<body>
<form:form id="commonForm" name="commonForm" action="">
</form:form>
<div style="width:100%; height:105px; background:url(/resources/images/common/bgimg_top_01.jpg) 0 no-repeat; padding-left:290px;">
<table cellpadding="0" cellspacing="0">
	<tr><td height="5"></td></tr>
	<tr>
		<td height="20" style="padding-left:270px;">
			<!-- spot menu -->
			<table cellpadding="0" cellspacing="0">
				<tr>
					<td class="cmtip"><strong>IP : </strong><%=request.getRemoteAddr()%></td>
					<td class="cmttab"><a href="#" onclick="logout();" target="_top"><img src="/resources/images/common/btn_logout.gif" alt="로그아웃" title="로그아웃"></a></td>
					<!-- <td class="cmttab"><a href="" target="_top"><img src="/resources/images/common/btn_homepage.gif" alt="홈페이지바로가기" title="홈페이지바로가기"></a></td> -->
				</tr>
			</table>
			<!-- spot menu -->
		</td>
	</tr>
	<tr><td height="40"></td></tr>
	<tr>
		<td height="40">
			<!-- tab -->
			<table cellpadding="0" cellspacing="0">
				<tr>
					<td class="cmttab">
					<!-- tab 1단위 -->
						<table cellpadding="0" cellspacing="0">
							<tr>
								<td><img src="/resources/images/common/tabimg_top_01.gif" alt="tab image" title="tab image"></td>
								<td class="cmttabcon">
									<a href="/admin/adminList.dw">
										CMS 관리
									</a>
								</td>
								<td><img src="/resources/images/common/tabimg_top_03.gif" alt="tab image" title="tab image"></td>
							</tr>
						</table>
						<!--// tab 1단위 -->
					</td>
					<!-- <td class="cmttab">
						tab 1단위
						<table cellpadding="0" cellspacing="0">
							<tr>
								<td><a href="main.do?op=left"  target="cmsLeft"><img src="/resources/images/common/tab_top_01.gif" alt="전체메뉴보기" title="전체메뉴보기"></a></td>
							</tr>
						</table>
						// tab 1단위
					</td> -->
				</tr>
			</table>
			<!--// tab -->
		</td>
	</tr>
</table>
</div>
