package system.maincontroller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MainController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String LOGIN = "LoginController";
    private static final String SEARCH_STUDENT = "SearchStudentInformation";
    private static final String UPDATE_STUDENT = "UpdateInformationProfile";
    private static final String SEARCH_LECTURE = "SearchLectureInformation";
    private static final String UPDATE_LECTURE = "UpdateInformationProfile";
    private static final String RESET_PASSWORD = "ResetPasswordController";
    private static final String LOGOUT = "LogoutController";
    private static final String LIST_ACCOUNT = "ListAccountController";
    private static final String DELETE_ACCOUNT = "DeleteAccountController";
    private static final String SEND_APPLICATION = "SendApplicationController";
    private static final String UPDATE_STUDENT_PROFILE = "UpdateProfileController";
    private static final String VIEW_DETAIL_CLASS_INFO = "DetailClassController";
    private static final String SEARCH_CLASS_INFO = "SearchClassController";
    private static final String SEARCH_FORM = "SearchFormController";
    private static final String CREATE_COURSE = "CreateCourseController";
    private static final String DELETE_CLASS_CONTROLLER = "DeleteClassController";
    private static final String UPDATE_CLASS_CONTROLLER = "UpdateClassController";
    private static final String CREATE_STUDENT = "CreateStudentController";
    private static final String CREATE_LECTURE = "CreateLectureController";
    private static final String APPROVE_TOPIC = "ApproveTopicAssign";
    private static final String DECLINE_TOPIC = "DeclineTopicAssign";
    private static final String VIEW_TOPIC_DETAIL = "ViewTopicDetailController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String action = request.getParameter("action");
            if ("Login".equals(action)) {
                url = LOGIN;
            } else if ("ViewTopicDetail".equals(action)) {
                url = VIEW_TOPIC_DETAIL;    
            } else if ("SearchStudent".equals(action)) {
                url = SEARCH_STUDENT;
            } else if ("ApproveTopic".equals(action)) {
                url = APPROVE_TOPIC;
            } else if ("DeclineTopic".equals(action)) {
                url = DECLINE_TOPIC;    
            } else if ("UpdateStudent".equals(action)) {
                url = UPDATE_STUDENT;
            } else if ("SearchLecture".equals(action)) {
                url = SEARCH_LECTURE;
            } else if ("UpdateLecture".equals(action)) {
                url = UPDATE_LECTURE;
            } else if ("Reset".equals(action)) {
                url = RESET_PASSWORD;
            } else if ("Logout".equals(action)) {
                url = LOGOUT;
            } else if ("Account".equals(action)) {
                url = LIST_ACCOUNT;
            } else if ("DeleteAccount".equals(action)) {
                url = DELETE_ACCOUNT;
            } else if ("Send".equals(action)) {
                url = SEND_APPLICATION;
            } else if ("Update Profile".equals(action)) {
                url = UPDATE_STUDENT_PROFILE;
            } else if ("ViewDetail".equals(action)) {
                url = VIEW_DETAIL_CLASS_INFO;
            } else if ("Search Class".equals(action)) {
                url = SEARCH_CLASS_INFO;
            } else if ("Search Form".equals(action)) {
                url = SEARCH_FORM;
            } else if ("Add Course".equals(action)) {
                url = CREATE_COURSE;
            } else if ("DeleteClass".equals(action)) {
                url = DELETE_CLASS_CONTROLLER;
            } else if ("Update Class".equals(action)) {
                url = UPDATE_CLASS_CONTROLLER;
            } else if ("AddStudent".equals(action)) {
                url = CREATE_STUDENT;
            } else if ("AddLecture".equals(action)) {
                url = CREATE_LECTURE;
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("ERROR_FUNC", "function is not available");
                url = ERROR;
            }
        } catch (Exception e) {
            log("Error at MainController: " + e.toString());
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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
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