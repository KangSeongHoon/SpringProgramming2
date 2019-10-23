<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import= "java.util.*" %> 
<%@ page import= "com.mycompany.web.dto.*"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>





<%

List<Ch05Board> boardList = (List<Ch05Board>)request.getAttribute("boardList");

%>


<h4>게시물 리스트(전체 게시물 수 : ${totalNo})</h4>
<table class="table">
  <thead>
    <tr>
    <th scope="col">번호</th>
      <th scope="col">제목</th>
      <th scope="col">글쓴이</th>
      <th scope="col">날짜</th>
      <th scope="col">조회수</th>
    </tr>
  </thead>
  <tbody>
  <c:forEach items="${boardList }" var="board">
    <tr>
      <th scope="row">${board.bno} </th>
      <td>${board.btitle} </td>
      <td>${board.writer} </td>
      <td><fmt:formatDate value="${board.date }" pattern="yyyy-MM-dd"/></td>
      <td>${board.hitcount} </td>
    </tr>
 </c:forEach>
  </tbody>
</table>