package ImagemProxy;

import java.awt.event.*;
import java.net.*;
import java.util.*;
import javax.swing.*;

public class Sistema {

    ImagemComponente imagemComponente;
    JFrame frame = new JFrame("Imagem");
    JMenuBar menuBar;
    JMenu menu;
    Hashtable img = new Hashtable();

    public Sistema() throws Exception {
        img.put("Ambient: Music for Airports", "http://images.amazon.com/images/P/ B000003S2K.01.LZZZZZZZ.jpj");
        img.put("Bluddha Bar", "http://images.amazon.eom/images/P/B00009XBYK.01.LZZZZZZZ.jpg");
        img.put("Ima", "http://images.amazon.com/images/P/B000005IRM.01.LZZZZZZZ.jpg");
        img.put("Karma", "http://images.amazon.com/images/P/B000005DCB.01.LZZZZZZZ.gif");
        img.put("MCMXC A.D.", "http://images.amazon.eom/images/P/B000002URV.01.LZZZZZZZ.jpg");
        img.put("Northern Exposure", "http://images.amazon.eom/images/P/B000003SFN.01.LZZZZZZZ.jpg");
        img.put("Selected Ambient Works, Vol. 2", "http://images.amazon.com/images/P/B000002MNZ.01.LZZZZZZZ.jpg");
        img.put("Oliver", "http://www.cs.yale.edu/homes/freeman-elisabeth/2004/9/O1ver_sm.jpg");
        URL inicialURL = new URL((String) img.get("Selected Ambient Works, Vol. 2"));
        menuBar = new JMenuBar();
        menu = new JMenu("Imagem Favoritas");
        menuBar.add(menu);
        frame.setJMenuBar(menuBar);
        for (Enumeration e = img.keys(); e.hasMoreElements();) {
            String nome = (String) e.nextElement();
            JMenuItem menuItem = new JMenuItem(nome);
            menu.add(menuItem);
            menuItem.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent evento) {
                    imagemComponente.setIcon(new ImagemProxy(getImagemURL(evento.getActionCommand())));
                    frame.repaint();
                }
            });
        }
        Icon icon = new ImagemProxy(inicialURL);
        imagemComponente = new ImagemComponente(icon);
        frame.getContentPane().add(imagemComponente);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public URL getImagemURL(String nome) {
        try {
            return new URL((String) img.get(nome));
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) throws Exception {
        Sistema sistema = new Sistema();
    }
}
