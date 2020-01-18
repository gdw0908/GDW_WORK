<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="pagingArea" align="center">
	<div class="page paging">
        <c:if test="${pagination.curRange ne 1 }">
            <a href="#" class="page" onClick="nextPaging(0)"><img src="/resources/images/board/ico_pre_02.gif" alt="맨처음" title="맨처음"/></a> 
        </c:if>
        <c:if test="${pagination.curPage ne 1}">
            <a href="#" class="page" onClick="nextPaging('${pagination.prevPage }')"><img src="/resources/images/board/ico_pre.gif" alt="이전" title="이전"/></a> 
        </c:if>
        <c:forEach var="pageNum" begin="${pagination.startPage }" end="${pagination.endPage }">
            <c:choose>
                <c:when test="${pageNum eq  pagination.curPage}">
                    <span style="font-weight: bold;"><a href="#" class="func" onClick="nextPaging('${pageNum }')">${pageNum }</a></span> 
                </c:when>
                <c:otherwise>
                    <a href="#" class="func" onClick="nextPaging('${pageNum }')">${pageNum }</a> 
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <c:if test="${pagination.curPage ne pagination.pageCnt && pagination.pageCnt > 0}">
            <a href="#" class="func" onClick="nextPaging('${pagination.nextPage }')"><img src="/resources/images/board/ico_next.gif" alt="다음" title="다음"/></a> 
        </c:if>
        <c:if test="${pagination.curRange ne pagination.rangeCnt && pagination.rangeCnt > 0}">
            <a href="#" class="func" onClick="nextPaging('${pagination.pageCnt }')"><img src="/resources/images/board/ico_next_02.gif" alt="맨뒤" title="맨뒤"/></a> 
        </c:if>
    </div>
<%--                 <div>
                    총 게시글 수 : ${pagination.listCnt } /    총 페이지 수 : ${pagination.pageCnt } / 현재 페이지 : ${pagination.curPage }  <!-- / 현재 블럭 : ${pagination.curRange } / 총 블럭 수 : ${pagination.rangeCnt } -->
                </div> --%>
</div>