//autores caio e matheus
package trabalhoclusterizacao;

import MeanShift.ImageType;
import MeanShift.MSImageProcessor;
import MeanShift.SpeedUpLevel;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

public class TrabalhoClusterizacao {

    public TrabalhoClusterizacao() {
    }

    public BufferedImage converter(BufferedImage imagemRGB) {
        WritableRaster raster = imagemRGB.getRaster();
        BufferedImage ret = new BufferedImage(imagemRGB.getWidth(), imagemRGB.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
        WritableRaster retRaster = ret.getRaster();
        int[] pixel = new int[3];
        for (int i = 0; i < raster.getWidth(); i++) {
            for (int j = 0; j < raster.getHeight(); j++) {
                retRaster.setPixel(i, j, converteRGBLHS(raster.getPixel(i, j, pixel)));
            }
        }
        return ret;
    }

    public BufferedImage realcar(BufferedImage imagemLHS) {
        WritableRaster raster = imagemLHS.getRaster();
        int[] pixel = new int[3];
        for (int i = 0; i < raster.getWidth(); i++) {
            for (int j = 0; j < raster.getHeight(); j++) {
                raster.setPixel(i, j, realcar(raster.getPixel(i, j, pixel)));
            }
        }
        return imagemLHS;
    }

    public BufferedImage reconverter(BufferedImage imagemLHS) {
        WritableRaster raster = imagemLHS.getRaster();
        int[] pixel = new int[3];
        for (int i = 0; i < raster.getWidth(); i++) {
            for (int j = 0; j < raster.getHeight(); j++) {
                raster.setPixel(i, j, converteLHSRGB(raster.getPixel(i, j, pixel)));
            }
        }
        return imagemLHS;
    }

    public BufferedImage aplicaMeanShift(BufferedImage imagem, int sigmaS, float sigmaR, int minRegiao) {
        // Atributos da Imagem 
        BufferedImage tmpImage = imagem;
        int width = tmpImage.getWidth();
        int height = tmpImage.getHeight();
        width = tmpImage.getWidth();
        height = tmpImage.getHeight();
        int pixelCount = width * height;
        int rgbPixels[] = new int[pixelCount];
        tmpImage.getRGB(0, 0, width, height, rgbPixels, 0, width);

        //System.out.println("==== Mean Shift Algoritmo ====");
        MSImageProcessor segm = new MSImageProcessor();
        segm.DefineBgImage(rgbPixels, ImageType.COLOR, height, width);

        // Segmenta Imagem        
        segm.Segment(1, true, sigmaS, sigmaR, minRegiao, SpeedUpLevel.HIGH_SPEEDUP);
        int segpixels[] = new int[pixelCount];
        segm.GetResults(segpixels);
        BufferedImage segImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        segImage.setRGB(0, 0, width, height, segpixels, 0, width);

        return segImage;
    }

    private static int[] converteRGBLHS(int[] pixelRGB) {
        int r, g, b;
        int l, h, s;   // these are in 0..255
        int maxVal, minVal, dif, sum, numerador;

        r = pixelRGB[0];
        g = pixelRGB[1];
        b = pixelRGB[2];

        maxVal = maxima(r, g);
        maxVal = maxima(maxVal, b);
        minVal = minima(r, g);
        minVal = minima(minVal, b);
        dif = maxVal - minVal;
        sum = maxVal + minVal;
        l = dirDesloc(sum, 1);

        if (dif <= 1) {
            s = 0;
            h = 0;
        } else {
            s = (int) Math.floor(((escDesloc(dif, 9)) - dif - dif) / (l <= 127 ? sum : 510 - sum));
            s = (int) Math.floor(dirDesloc((s + 1), 1));   // round to 8 bits

            if (r == maxVal) {
                numerador = (int) Math.floor((maxVal - b) - (maxVal - g));
                h = 0;
            } else if (g == maxVal) {
                numerador = (int) Math.floor((maxVal - r) - (maxVal - b));
                h = (int) Math.floor((escDesloc(1, 12)) * 1 / 3);
            } else {
                numerador = (int) Math.floor((maxVal - g) - (maxVal - r));
                h = (int) Math.floor((escDesloc(1, 12)) * 2 / 3);
            }

            h += (escDesloc(numerador, 11)) / (dif + dif + dif);
            h = dirDesloc((h + (escDesloc(1, 3))), 4);
        }
        int[] ret = {l, h, s};
        return ret;
    }

    private static int[] realcar(int[] pixelLHS) {
        //vegetação
        if (pixelLHS[1] > 50 && pixelLHS[1] < 100 && pixelLHS[0] > 50 && pixelLHS[2] < 60) {
            pixelLHS[0] = 67;
            pixelLHS[1] = 80;
        }

        //casas
        if (pixelLHS[1] > 20 && pixelLHS[1] < 30 && pixelLHS[0] < 100 && pixelLHS[2] < 100) {
            pixelLHS[0] = 80;
            pixelLHS[1] = 3;
        }

        //industrias
        if ((pixelLHS[0] > 135) || (pixelLHS[1] == 0)) {
            pixelLHS[0] = 255;
            pixelLHS[1] = 0;
        }

        pixelLHS[2] = 225;
        return pixelLHS;
    }

    private static int[] converteLHSRGB(int[] pixelLHS) {
        int l, h, s;
        int r = 0, g = 0, b = 0;
        int hbase, hfrac, product, maxVal, minVal, midVal = 0;

        l = pixelLHS[0];   // 1.0 is at 255
        h = pixelLHS[1];   // 1.0 is at 255 (which is 360 degrees)
        s = pixelLHS[2];   // 1.0 is at 255

        // In RGB_to_HLS, L=sum/2, which truncates.  The average error from this
        // truncation is 0.25, which is the (1<<4) added below.
        l = (escDesloc(l, 6)) + (escDesloc(1, 4));   // now 1.0 is at 255*(1<<6), 6 bits of frac
        s = escDesloc(s, 6);

        h *= 6;
        hbase = dirDesloc(h, 8);       // in 0..5, and is the basic hue
        hfrac = h & 0x00ff;   // fractional offset from basic hue to next basic hue

        product = l * s;
        product = dirDesloc((product + (dirDesloc(product, 8))), (8 + 6));  // approx division by 255*(1<<6)
        if (l <= (escDesloc(127, 6)) + (escDesloc(1, 4))) {
            maxVal = l + product;
        } else {
            maxVal = l + s - product;
        }

        minVal = l + l - maxVal;
        midVal = minVal + (dirDesloc(((maxVal - minVal) * (((hbase & 1) == 1) ? 256 - hfrac : hfrac)), 8));

        // round the results to 8 bits by shifting out the 6 frac bits
        minVal = dirDesloc((minVal + (escDesloc(1, 5))), 6);
        midVal = dirDesloc((midVal + (escDesloc(1, 5))), 6);
        maxVal = dirDesloc((maxVal + (escDesloc(1, 5))), 6);

        // I ran this routine with all possible h-l-s values (2^24 of them!), and
        // none produced a value outside 0..255, so we don't do the checks below.
        // if (maxVal < 0) maxVal=0; else if (maxVal > 255) maxVal = 255;
        // if (midVal < 0) midVal=0; else if (midVal > 255) midVal = 255;
        // if (minVal < 0) minVal=0; else if (minVal > 255) minVal = 255;

        switch (hbase) {
            case 0:
                r = maxVal;
                g = midVal;
                b = minVal;
                break;
            case 1:
                r = midVal;
                g = maxVal;
                b = minVal;
                break;
            case 2:
                r = minVal;
                g = maxVal;
                b = midVal;
                break;
            case 3:
                r = minVal;
                g = midVal;
                b = maxVal;
                break;
            case 4:
                r = midVal;
                g = minVal;
                b = maxVal;
                break;
            case 5:
                r = maxVal;
                g = minVal;
                b = midVal;
                break;
        }
        int[] ret = {r, g, b};
        return ret;
    }

    private static int maxima(int esq, int dir) {
        return (esq > dir ? esq : dir);
    }

    private static int minima(int esq, int dir) {
        return (esq < dir ? esq : dir);
    }

    private static int escDesloc(int x, int y) {
        return (int) Math.floor(x * Math.pow(2, y));
    }

    private static int dirDesloc(int x, int y) {
        return (int) Math.floor(x / Math.pow(2, y));
    }
}