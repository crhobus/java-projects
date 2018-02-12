package edu.furb.webapplication;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ImgServlet", urlPatterns = {"/imgservlet/*"})
public class ImgServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("image/jpg");

        String fileName = request.getRequestURI().replace("/WebApplicationServletImg/imgservlet/", "");
        File file = new File("C:/Users/Caio/Documents/Arquivos/img/" + fileName);

        if (!file.exists()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        BufferedImage buffImg = ImageIO.read(file);

        String tipo = "";
        int altura = buffImg.getHeight();
        int largura = buffImg.getWidth();

        if (request.getParameter("altura") != null) {
            altura = Integer.parseInt(request.getParameter("altura"));
        }
        if (request.getParameter("largura") != null) {
            largura = Integer.parseInt(request.getParameter("largura"));
        }
        if (request.getParameter("tipo") != null) {
            tipo = request.getParameter("tipo");
        }

        if (request.getParameter("altura") != null
                || request.getParameter("largura") != null) {
            buffImg = toBufferedImage(ImageIO.read(file).getScaledInstance(largura, altura, java.awt.Image.SCALE_DEFAULT));
        }

        switch (tipo) {
            case "c":
                buffImg = toGrayScale(buffImg);
                break;
            case "s":
                buffImg = toSepia(buffImg);
                break;
        }

        ImageIO.write(buffImg, "jpg", response.getOutputStream());
        response.getOutputStream().close();
    }

    public BufferedImage toGrayScale(BufferedImage master) {
        BufferedImage gray = new BufferedImage(master.getWidth(), master.getHeight(), BufferedImage.TYPE_BYTE_GRAY);

        // Automatic converstion....
        ColorConvertOp op = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
        op.filter(master, gray);

        return gray;
    }

    public BufferedImage toSepia(BufferedImage img) {

        BufferedImage sepia = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
        // Play around with this.  20 works well and was recommended
        //   by another developer. 0 produces black/white image
        int sepiaDepth = 20;

        int w = img.getWidth();
        int h = img.getHeight();

        WritableRaster raster = sepia.getRaster();

        // We need 3 integers (for R,G,B color values) per pixel.
        int[] pixels = new int[w * h * 3];
        img.getRaster().getPixels(0, 0, w, h, pixels);

        //  Process 3 ints at a time for each pixel.  Each pixel has 3 RGB
        //    colors in array
        for (int i = 0; i < pixels.length; i += 3) {
            int r = pixels[i];
            int g = pixels[i + 1];
            int b = pixels[i + 2];

            int gry = (r + g + b) / 3;
            r = g = b = gry;
            r = r + (sepiaDepth * 2);
            g = g + sepiaDepth;

            if (r > 255) {
                r = 255;
            }
            if (g > 255) {
                g = 255;
            }
            if (b > 255) {
                b = 255;
            }

            // Darken blue color to increase sepia effect
            b -= 80;

            // normalize if out of bounds
            if (b < 0) {
                b = 0;
            }
            if (b > 255) {
                b = 255;
            }

            pixels[i] = r;
            pixels[i + 1] = g;
            pixels[i + 2] = b;
        }
        raster.setPixels(0, 0, w, h, pixels);

        return sepia;
    }

    public BufferedImage toBufferedImage(Image img) {

        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return bimage;
    }
}
