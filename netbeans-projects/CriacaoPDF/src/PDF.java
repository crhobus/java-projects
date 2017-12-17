
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;

public class PDF {

    public static void main(String[] args) {
        //JPanel
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("arq.pdf"));
            document.open();
            document.add(new Paragraph("Primeira                        Nome1"));
            document.add(new Paragraph("Segundo                         Nome2"));
            //String str = "bguidbgciwbvbfu";
            Paragraph pr = new Paragraph("Segundo                         Nome3");
            //JLabel label = new JLabel("vb");
            //document.add(label);
            //Font font = new Font(1, 20, 30);

            pr.font().setSize(7);
            pr.font().setColor(Color.RED);
            // Font font = new Font("Arial", Font.BOLD, 12);
            pr.font().setStyle("BOLD");
            //pr.font().setFamily("Adobe Garamond Pro Bold");
            document.add(pr);
            document.add(new Paragraph("Terceiro                                                                                                                                     cvjdk"));
            //document.add(new PdfPTable(8));

            /* Image img = Image.getInstance("Flamengo.jpg");
            img.setAbsolutePosition(200, 0);
            img.setBorder(80);
            img.setAlignment(Element.PARAGRAPH);
            document.add(img);*/

            PdfPTable table = new PdfPTable(5);//Colunas
            PdfPCell header = new PdfPCell(new Paragraph("Tabela"));
            header.setBackgroundColor(Color.YELLOW);
            header.setBorderWidth(2.0f);
            header.setBorderColor(Color.BLUE);
            header.setBorder(Rectangle.BOTTOM);

            header.setColspan(5);
            table.addCell(header);
            table.addCell("1");
            table.addCell("2");
            table.addCell("3");
            table.addCell("4");
            table.addCell("5");
            table.addCell("6");
            table.addCell("7");
            table.addCell("8");
            table.addCell("9");
            table.addCell("10");
            table.addCell("11");
            table.addCell("12");
            table.addCell("13");
            table.addCell("14");
            table.addCell("15");
            table.addCell("16");
            table.addCell("17");
            table.addCell("18");
            table.addCell("19");
            table.addCell("20");
            table.addCell("21");
            table.addCell("22");
            table.addCell("23");
            table.addCell("24");
            table.addCell("25");
            table.addCell("26");
            table.addCell("27");
            table.addCell("28");
            table.addCell("29");
            table.addCell("30");
            table.setWidthPercentage(60.0f);
            table.setHorizontalAlignment(Element.ALIGN_LEFT);
            document.add(table);
            document.close();
        } catch (DocumentException de) {
            System.err.println(de.getMessage());
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
        document.close();
    }
}
