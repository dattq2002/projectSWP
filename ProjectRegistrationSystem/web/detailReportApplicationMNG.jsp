<%@page import="DTO.ApplicationMNG"%>
<%@page import="DTO.UserAccountDTO"%>
<%@page import="java.util.List"%>
<%@page import="DTO.Application"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Detail Application Page</title>
    </head>
    <body>
        <%
            UserAccountDTO loginUser = (UserAccountDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null || !loginUser.getRoleID().equals("MNG")) {
                response.sendRedirect("login.jsp");
                return;
            }

            String search = (String) request.getParameter("search");
            if (search == null) {
                search = "";
            }

            String searchE = loginUser.getEmail();
            if (search == null) {
                search = "";
            }
        %>

        <a href="manager.jsp">Home Page</a>
        <span> > </span>
        <a href="ManagerController?action=Lecturer's Application&searchE=<%= searchE%>">List of Application</a>

        <%
            List<ApplicationMNG> listApplication = (List<ApplicationMNG>) request.getAttribute("DETAIL_R_APPLICATION");
            if (listApplication != null) {
                if (!listApplication.isEmpty()) {
        %>

        <%
            for (ApplicationMNG dto : listApplication) {
        %>
        <form action="ManagerController" method="POST">
            <br>Application ID: <%= dto.getID()%> <br>
            <br>Type: <%= dto.getType()%> <br>
            <br>Subject ID: <%= dto.getSubjectID()%> <br>
            <br>Course ID: <%= dto.getCourseID()%> <br>
            <br>Group ID: <%= dto.getGroupID()%> <br>
            <br>Student ID: <%= dto.getStudentID()%> <br>
            <br>Reason: <%= dto.getReason()%> <br>
            <br>Create Date: <%= dto.getCreateDate()%> <br>

            <br>Note: <textarea name="note" style="width: 250px; height: 150px;"><%= dto.getNote()%></textarea> <br>   

            <input type="submit" name="action" value="ApproveApplication"/>
            <input type="hidden" name="formID" value="<%= dto.getID()%>"/> 
            <input type="hidden" name="type" value="<%= dto.getType()%>"/>

            <input type="submit" name="action1" value="RefuseApplication"/>
            <input type="hidden" name="formID" value="<%= dto.getID()%>"/>   
            <input type="hidden" name="type" value="<%= dto.getType()%>"/>
        </form>
        <%
            }
        %>
    </tbody>
</table>
<%
    }
    }
%> 
<%
    String error_message = (String) request.getAttribute("ERROR_MESSAGE");
    if (error_message != null) {
        %>
        <p><%=error_message%></p>
<%
    }
%>
</body>
</html>