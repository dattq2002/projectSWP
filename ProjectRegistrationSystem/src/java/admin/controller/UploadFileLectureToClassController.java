package admin.controller;

import DTO.LectureProfile;
import adminDAO.ProfileDAO;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
@MultipartConfig
public class UploadFileLectureToClassController extends HttpServlet {
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Part filePart = request.getPart("fileLecToClass"); // Lấy phần tệp từ yêu cầu
        InputStream fileInputStream = filePart.getInputStream(); // Lấy luồng đầu vào từ tệp
        try ( Workbook workbook = new XSSFWorkbook(fileInputStream)) { // Tạo đối tượng Workbook từ tệp Excel
            Sheet sheet = workbook.getSheetAt(0); // Lấy sheet đầu tiên
            // Xử lý dữ liệu từ tệp Excel theo ý muốn của bạn
            ProfileDAO dao = new ProfileDAO();
            int rowCount = sheet.getPhysicalNumberOfRows();
            int colCount = 3;
            for (int i = 1; i < rowCount; i++) {
                Row row = sheet.getRow(i);
                for (int j = 0; j < colCount; j++) {
                    Cell cell = row.getCell(j);
                    //xử lý file
                    int lecID = (int) row.getCell(j).getNumericCellValue();
                    int subID =(int) row.getCell(j + 1).getNumericCellValue();
                    int courseID = (int) row.getCell(j + 2).getNumericCellValue();
                    boolean status = true;
                    if(cell != null){
                        boolean result = dao.AddLectureToClass(lecID, subID, courseID, status);
                        if(result == false){
                            request.setAttribute("MESSAGE_FAIL", "Upload fail !!!");
                            return;
                        }
                    }
                    break;
                }
            }
            request.setAttribute("MESSAGE", "Add data successfully !!!");
            workbook.close(); // Đóng Workbook
            fileInputStream.close(); // Đóng luồng đầu vào
        } catch (Exception e) {
            request.setAttribute("FAIL", "Add data fail !!");
        } finally {
            request.getRequestDispatcher("createCourse.jsp").forward(request, response);
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
