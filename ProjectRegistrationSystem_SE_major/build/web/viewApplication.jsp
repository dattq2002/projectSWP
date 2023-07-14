<%@page import="system.main.DTO.Application"%>
<%@page import="system.main.DTO.UserAccountDTO"%>
<%@page import="java.util.List"%>
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
            if (loginUser == null || !loginUser.getRoleID().equals("USER")) {
                response.sendRedirect("login.jsp");
                return;
            }
        %>
        <h3>View Application</h3>
        <%
            List<Application> list = (List<Application>) request.getAttribute("SHOWLIST_FORM");
            if (list != null) {
                if (!list.isEmpty()) {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>Application ID</th>
                    <th>Student Code</th>
                    <th>Create Date</th>
                    <th>Type</th>
                    <th>Reason</th>
                    <th>Lecture Note</th>
                    <th>Status</th>
                    <th>Lecture Name</th>
                    <th>Course Name</th>
                    <th>Room</th>
                    <th>Present Date</th>
                    <th>Time</th>
                </tr>
            </thead>
            <tbody>
                <%
                    for (Application item : list) {
                %>
                <tr>
                    <td><%=item.getID()%></td>
                    <td><%=item.getStuCode() + item.getStuID()%></td>
                    <td><%=item.getCreateDate()%></td>
                    <td><%=item.getType()%></td>
                    <td><%=item.getReason()%></td>
                    <td><%=(item.getLecNote() == null) ? "" : item.getLecNote()%></td>
                    <td><%=item.getStatus()%></td>
                    <td><%=item.getLecName()%></td>
                    <td><%=item.getCourseName() + item.getCourseID()%></td>
                    <td><%=(item.getRoom() == null) ? "" : item.getRoom()%></td>
                    <td><%=(item.getPresentDate() == null) ? "" : item.getPresentDate()%></td>
                    <td><%=(item.getTime() == null) ? "" : item.getTime()%></td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>

        <%
                }
            }
        %>
    </body>
</html>