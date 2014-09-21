package fs2013;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet(value="/upload")
public class FileUploadHandler extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2100017043240349999L;
	private final String UPLOAD_DIRECTORY = "/WEB-INF/upload";
	
	public void init(ServletConfig conf) throws ServletException {
		super.init(conf);
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
		throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		
		//process only if its multipart content
		if (ServletFileUpload.isMultipartContent(request)) {
			try {
				List<FileItem> multiparts = new ServletFileUpload(
						new DiskFileItemFactory()).parseRequest(request);
				
				for (FileItem item : multiparts) {
					if (!item.isFormField()) {
						String name = new File(item.getName()).getName();
						File uploadFile = new File(getServletContext().getRealPath(UPLOAD_DIRECTORY + File.separator + name));
						uploadFile.createNewFile();
 						item.write(uploadFile);
					}
				}
				
				//File uploaded successfully
				request.setAttribute("message", "File Uploaded Successfully");
				request.getRequestDispatcher("/main.jsp").forward(request, response);
			} catch (Exception ex) {
				request.setAttribute("message",  "File Upload Failed due to " + ex);
				request.getRequestDispatcher("/result.jsp").forward(request, response);
			}
		} else {
			request.setAttribute("message",
					"Sorry this Servlet only handles file upload requests");
			request.getRequestDispatcher("/result.jsp").forward(request, response);
		}
	}
}
