package manager.controller.manage.topic;

import admin.DAO.TopicDAO;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import system.main.DTO.UserAccountDTO;

public class CreateTopic extends HttpServlet {
    private static final String ERROR = "createTopicMNG.jsp";
    private static final String SUCCESS = "createTopicMNG.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            //getParameter
            String topic = request.getParameter("topic");
            String topicName = request.getParameter("topicName");
            HttpSession session = request.getSession();
            UserAccountDTO loginUser = (UserAccountDTO) session.getAttribute("LOGIN_USER");
            String shortDescription = request.getParameter("shortDescription");
            String fullDescription = request.getParameter("fullDescription");
            boolean check = false;
            //----------------------------------------
            String regex = "^[A-Za-z]+-\\d+$";
            //check
            TopicDAO topDao = new TopicDAO();

            String topicCode;
            int topicID;
            if (!topic.matches(regex)) {
                request.setAttribute("MESSAGE", "Topic Code is not Match !!!");
                return;
            } else {
                String tmp[] = topic.split("-");
                topicCode = tmp[0];
                topicID = Integer.parseInt(tmp[1]);
                boolean result = topDao.checkDuplicateTopicID(topicID, topicCode);
                if (result) {
                    request.setAttribute("MESSAGE", "Topic Code is duplicated!!!");
                    return;
                }
            }

            if (topicName.length() < 0 || topicName.length() > 100) {
                request.setAttribute("MESSAGE", "Topic Name must have a length in [0,100]");
                return;
            }

            int lecturerID = topDao.getLecturerID(loginUser.getEmail());

            if (shortDescription.length() < 0) {
                request.setAttribute("MESSAGE", "Short Description must be longer than 0!!!");
                return;
            }
            if (fullDescription.length() < 0) {
                request.setAttribute("MESSAGE", "Full Description must be longer than 0!!!");
                return;
            }

            check = true;
            //create
            if (check) {
                boolean result = topDao.createTopic(topicID, topicCode, topicName, lecturerID, shortDescription, fullDescription);
                if (result) {
                    url = SUCCESS;
                    request.setAttribute("MESSAGE", "Create Successfully !!");
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}