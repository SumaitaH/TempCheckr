package cs.qc.cuny.edu;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
@WebServlet("/FileUploadServlet")
@MultipartConfig(fileSizeThreshold=1024*1024*10,  // 10 MB 
               maxFileSize=1024*1024*50,       // 50 MB
               maxRequestSize=1024*1024*100)    // 100 MB

public class FileUploadServer extends HttpServlet {
  private static final long serialVersionUID = 205242440643911308L;
 
  /**
   * Directory where uploaded files will be saved, its relative to
   * the web application directory.
   */
  private static final String UPLOAD_DIR = "uploads";
  
   
  protected void doPost(HttpServletRequest request,
          HttpServletResponse response) throws ServletException, IOException {
	  
  	String sqlQuery = "SELECT * FROM HISTORY";
  	ora_DBTest_Demo DBConnect_instance = new ora_DBTest_Demo();
  	String dbTime = DBConnect_instance.testconnection_mysql(20000);
  	ArrayList<String> searchHistory = DBConnect_instance.generateResultSet(sqlQuery);
  	//create a new textfile with filename
  	response.getWriter().write("Location, Date" + "\n");
  	for(int i=0;i<searchHistory.size();i++){
  		response.getWriter().write(searchHistory.get(i) + "\n");
  	} 



      // gets absolute path of the web application
//      String applicationPath = request.getServletContext().getRealPath("");
      // constructs path of the directory to save uploaded file
//      String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;
       
      // creates the save directory if it does not exists
//      File fileSaveDir = new File(uploadFilePath);
//      if (!fileSaveDir.exists()) {
//          fileSaveDir.mkdirs();
//      }
//      System.out.println("Upload File Directory="+fileSaveDir.getAbsolutePath());
//      
//      String fileName = "";
//      //Get all the parts from request and write it to the file on server
//      for (Part part : request.getParts()) {
//          fileName = getFileName(part);
//          if (fileName.lastIndexOf("\\") != -1)
//           fileName = fileName.substring(fileName.lastIndexOf("\\"));
//          part.write(uploadFilePath + File.separator + fileName);
//      }
//      
//      String message = "";
//      
//      ora_DBTest_Demo DBConnect_instance = new ora_DBTest_Demo();
//      String dbTime = DBConnect_instance.testconnection_mysql(20000);
//      if (dbTime.length() > 0) {
//          System.out.println("Successful Completion");
//          message = dbTime;
//      } else {
//          System.out.println("MYSQL DB connection fail");
//      }
//      Scanner scanContent = new Scanner(new File(uploadFilePath + File.separator + fileName)); 
//      String content = scanContent.useDelimiter("\\Z").next();
      response.getWriter().write("");
      //writeToResponse(response, content);
      //scanContent.close(); 
  }
  protected void doGet(HttpServletRequest request,
          HttpServletResponse response) throws ServletException, IOException{
	  
	  String dateParam = request.getParameter("date");
	  String locationParam = request.getParameter("location");
	  System.out.println("Using get request, the date is: " + dateParam);
	  System.out.println("Using get request, the location is: " + locationParam);
	  
	  
	  
	  
      ora_DBTest_Demo DBConnect_instance = new ora_DBTest_Demo();
      DBConnect_instance.addToHistory(dateParam,locationParam);   
  }
  
  /**
   * Utility method to get file name from HTTP header content-disposition
   */
//  private String getFileName(Part part) {
//      String contentDisp = part.getHeader("content-disposition");
//      System.out.println("content-disposition header= "+contentDisp);
//      String[] tokens = contentDisp.split(";");
//      for (String token : tokens) {
//          if (token.trim().startsWith("filename")) {
//              return token.substring(token.indexOf("=") + 2, token.length()-1);
//          }
//      }
//      return "";
//  }
//
//  
// private void writeToResponse(HttpServletResponse resp, String results) throws IOException {
//  PrintWriter writer = new PrintWriter(resp.getOutputStream());
//  resp.setContentType("text/plain");
//  if (results.isEmpty()) {
//   writer.write("No results found.");
//  } else {
//   writer.write(results);
//  }
//  writer.close();
// } 
}