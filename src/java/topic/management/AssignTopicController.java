/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package topic.management;

import DAO.TopicMNGDAO;
import DTO.UserDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Nam An
 */
public class AssignTopicController extends HttpServlet {

    private static final String ERROR = "assignTopic.jsp";
    private static final String SUCCESS = "assignTopic.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            //getParameter
            int topicID = Integer.parseInt(request.getParameter("topicID"));
            String subject = request.getParameter("subject");
//            String semester = request.getParameter("semester");
            
            HttpSession session = request.getSession();
            UserDTO loginUser = (UserDTO)session.getAttribute("LOGIN_USER");
            //----------------------------------------
            String regex = "^[A-Za-z]+-\\d+$";
            String regex2 = "^(Spring|Fall|Summer)-\\d+$";
            boolean check = false;
            //----------------------------------------
            //check
            TopicMNGDAO dao = new TopicMNGDAO();
            if (!dao.checkDuplicateTopicID(topicID)) {
                request.setAttribute("MESSAGE", "Topic ID could not be found!!!");
                return;
            }

            int subjectID = 0;
            if (!subject.matches(regex)) {
                request.setAttribute("MESSAGE", "Subject ID not Match !!!");
                return;
            } else {
                String tmp[] = subject.split("-");
                String subjectCode = tmp[0];
                subjectID = Integer.parseInt(tmp[1]);
                boolean result = dao.checkDuplicateSubjectID(subjectID, subjectCode);
                if (!result) {
                    request.setAttribute("MESSAGE", "Subject ID could not be found!!!");
                    return;
                }
            }
//            int semesterID = 0;
//            if (!semester.matches(regex2)) {
//                request.setAttribute("MESSAGE", "Semester ID is not match!!");
//                return;
//            } else {
//                String tmp[] = semester.split("-");
//                String semesterName = tmp[0];
//                int semesterYear = Integer.parseInt(tmp[1]);
//                boolean result = dao.checkDuplicateSemesterID(semesterYear, semesterName);
//                if (!result) {
//                    request.setAttribute("MESSAGE", "Semester ID could not be found!!!");
//                    return;
//                }
//                semesterID = dao.getSemesterID(semesterYear, semesterName);
//            }
            
            if (!dao.findTopicID(loginUser.getEmail())) {
                request.setAttribute("MESSAGE", "Cannot be assigned with another Lecturer's Topic.!!!");
                return;
            }
            
            if (dao.findSubjectID(subjectID, topicID)) {
                request.setAttribute("MESSAGE", "This Topic is already assigned in Subject.!!!");
                return;
            }

            check = true;
            //create
            if (check) {
                boolean result = dao.assignTopic(topicID, subjectID);
                if (result) {
                    url = SUCCESS;
                    request.setAttribute("MESSAGE", "Assign Successfully !!");
                } else {
                    url = ERROR;
                    request.setAttribute("MESSAGE", "Create Fail !!");
                }

            }

        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}