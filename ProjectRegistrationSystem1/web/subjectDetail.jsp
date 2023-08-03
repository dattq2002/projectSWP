<%@page import="system.main.DTO.UserAccountDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Project Registration System</title>
    </head>
    <body>
        <%
            UserAccountDTO loginUser = (UserAccountDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null || !loginUser.getRoleID().equals("MNG")) {
                response.sendRedirect("login.jsp");
                return;
            }
        %>
        <a href="GetListStudentInClass?courseID=<%=session.getAttribute("COURSE_ID")%>&subID=<%=session.getAttribute("SUBJECT_ID")%>">
            List Student
        </a>
        <a href="ListTopic?sesID=<%=session.getAttribute("SEMESTER_ID")%>&subID=<%=session.getAttribute("SUBJECT_ID")%>">List Topic</a>
        <a href="ListGroup?courseID=<%=session.getAttribute("COURSE_ID")%>&subID=<%=session.getAttribute("SUBJECT_ID")%>&sesID=<%=session.getAttribute("SEMESTER_ID")%>">List Group</a>
    </body>
</html>