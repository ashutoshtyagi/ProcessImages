

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import javax.activation.MimetypesFileTypeMap;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/ReceiveAndReturnImage")
@MultipartConfig
public class ReceiveAndReturnImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ReceiveAndReturnImage() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*doGet(request, response);*/
		
		String description = request.getParameter("description"); // Retrieves <input type="text" name="description">
	    Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
	    String fileName = filePart.getSubmittedFileName();
	    InputStream fileContent = filePart.getInputStream();
	    BufferedImage imBuff = ImageIO.read(fileContent);
	    // ... (do your job here)
	    
	    String path = "/media/ashutosh/Shared/Documents/99acres/photoshop/output_img.jpg";
	    File outputfile = new File(path);
	    ImageIO.write(imBuff, "jpg", outputfile);
	    
	    rotateImage("/media/ashutosh/Shared/Documents/99acres/photoshop/output_img.jpg", 
	    		"/media/ashutosh/Shared/Documents/99acres/photoshop/output_img_1.jpg");
	    
	    String path_1 = "/media/ashutosh/Shared/Documents/99acres/photoshop/output_img_1.jpg";
	    File outputfile_1 = new File(path_1);
	    response.setHeader("Content-Type", new MimetypesFileTypeMap().getContentType(outputfile_1));
	    BufferedImage bi_1 = ImageIO.read(outputfile_1);
		OutputStream out = response.getOutputStream();
		ImageIO.write(bi_1, "jpg", out);
		out.close();
		
	    /*response.setHeader("Content-Type", new MimetypesFileTypeMap().getContentType(outputfile));
		BufferedImage bi = ImageIO.read(outputfile);
		OutputStream out = response.getOutputStream();
		ImageIO.write(bi, "jpg", out);
		out.close();*/
	}
	
	public static String rotateImage(String sourceImage, String targetImage) {
        ProcessBuilder builder = new ProcessBuilder("convert", "-rotate", "90", sourceImage, targetImage);
        /*printCommand(builder.command());*/
        builder.redirectErrorStream(true);
        String shellOut = "";
        try {
            Process process = builder.start();

            BufferedReader inputStreamBufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = inputStreamBufferedReader.readLine()) != null) {
                shellOut += line;
            }
            System.out.println(shellOut);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return shellOut;
        }
    }

}
