/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lecturer.controller.managegroup;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lecturer.DAO.GroupDAO;

/**
 *
 * @author Nam An
 */
public class CreateGroup extends HttpServlet {

    private static final String ERROR = "ListGroup";
    private static final String SUCCESS = "ListGroup";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            int sesID = Integer.parseInt(request.getParameter("sesID"));
            int courseID = Integer.parseInt(request.getParameter("courseID"));
            int subID = Integer.parseInt(request.getParameter("subID"));
            String grName = request.getParameter("grName");
//            String topic = request.getParameter("topic");
//            String regex = "^[A-Za-z]+-\\d+$";

            GroupDAO grDAO = new GroupDAO();
            if (grDAO.checkGroupName(grName, courseID, subID)) {
                request.setAttribute("MESSAGE", "Group Name is duplicated!!!");
                return;
            }
//            int topicID = 0;
//            if (!topic.matches(regex)) {
//                request.setAttribute("MESSAGE", "Topic is not Match !!!");
//                return;
//            } else {
//                String tmp[] = topic.split("-");
//                String topicCode = tmp[0];
//                topicID = Integer.parseInt(tmp[1]);
//                boolean result = grDAO.checkTopicID(subID, sesID, topicID, topicCode);
//                if (!result) {
//                    request.setAttribute("MESSAGE", "Topic Code could not be found!!!");
//                    return;
//                }
//            }
//            if (grDAO.checkDuplicateTopicID(topicID, courseID, subID)) {
//                request.setAttribute("MESSAGE", "Topic is assign!!!");
//                return;
//            }

            boolean check = grDAO.insertGroup(courseID, subID, grName);
            if (check) {
                url = SUCCESS;
                request.setAttribute("MESSAGE", "Create Successfully !!");
            } else {
                url = ERROR;
                request.setAttribute("MESSAGE", "Create Fail !!");
            }

        } catch (Exception e) {
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