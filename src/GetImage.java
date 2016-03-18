

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;

import javax.activation.MimetypesFileTypeMap;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.rmi.runtime.Log;

/**
 * Servlet implementation class GetImage
 */
@WebServlet("/GetImage")
public class GetImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetImage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String path = "/media/ashutosh/Shared/Documents/99acres/photoshop/input_img.jpg";
			File imageFile = new File(path);
			Image image = ImageIO.read(imageFile);
			
			/*ServletContext cntx = getServletContext();
			String mime = cntx.getMimeType(path);
			response.setContentType("text/html");
	        PrintWriter printWriter  = response.getWriter();
	        printWriter.println("<h1>"  + mime + "</h1>");*/
			
			response.setHeader("Content-Type", new MimetypesFileTypeMap().getContentType(imageFile));
			
			BufferedImage bi = ImageIO.read(imageFile);
			OutputStream out = response.getOutputStream();
			ImageIO.write(bi, "jpg", out);
			out.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
