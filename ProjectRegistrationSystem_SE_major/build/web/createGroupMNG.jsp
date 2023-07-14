<%@page import="system.main.DTO.ClassInformation"%>
<%@page import="java.util.List"%>
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

        <a href="ListGroupMNG?courseID=<%=session.getAttribute("COURSE_ID")%>&subID=<%=session.getAttribute("SUBJECT_ID")%>">List Group</a>
        
        <h3>Create Topic</h3>
        <form action="LecturerController">      

            Group Name: <input type="text" name="grName" value="" required=""/> <br>    
            
            Student Code <input type="text" name="student" value="" required="" placeholder="SE16-123"/> <br>
            
            Topic Code <input type="text" name="topic" value="" required="" placeholder="HRM-501"/> <br>
            
            Context: <textarea name="context" style="width: 300px; height: 100px;" required=""></textarea> <br>
            
            Actors: <textarea name="actors" style="width: 300px; height: 100px;" required=""></textarea> <br>
            
            Function Requirements: <textarea name="funcR" style="width: 300px; height: 100px;" required=""></textarea> <br>
            
            Note: <textarea name="note" style="width: 300px; height: 100px;" required=""></textarea> <br>
            
            <input type="submit" value="Create" />
            <input type="hidden" value="CreateGroup" name="action" />  
            <input type="hidden" name="courseID" value="<%=session.getAttribute("COURSE_ID")%>"/>
            <input type="hidden" name="subID" value="<%=session.getAttribute("SUBJECT_ID")%>"/>
            <input type="hidden" name="sesID" value="<%=session.getAttribute("SEMESTER_ID")%>"/>
            <input type="reset" value="Reset" />
        </form>
        <%
            String message = (String) request.getAttribute("MESSAGE");
            if (message != null) {
        %>
        <a><%=message%></a>
        <%
            }
        %>
    </body>
</html>