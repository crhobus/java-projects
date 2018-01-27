package teste;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

/**
 *
 * @author crhobus
 */
public class JTableLineNumber extends JTable {

    private JTable mainTable;
    private boolean selected;

    public JTableLineNumber(JTable table) {
        super();
        mainTable = table;
        setAutoCreateColumnsFromModel(false);
        setModel(mainTable.getModel());
        setSelectionModel(mainTable.getSelectionModel());
        setAutoscrolls(false);

        addColumn(new TableColumn());
        getColumnModel().getColumn(0).setCellRenderer(mainTable.getTableHeader().getDefaultRenderer());
        getColumnModel().getColumn(0).setPreferredWidth(30);
        setPreferredScrollableViewportSize(getPreferredSize());

        for (int i = 0; i < getColumnCount(); i++) {
            getColumnModel().getColumn(i).setCellRenderer(new TesteCellRenderer());
        }
    }

    public boolean isCellEditable(int row, int column) {
        return false;
    }

    public Object getValueAt(int row, int column) {
        return row + 1;
    }

    public int getRowHeight(int row) {
        return mainTable.getRowHeight();
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }

        DefaultTableModel model = new DefaultTableModel(100, 5);
        JTable table = new JTable(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JScrollPane scrollPane = new JScrollPane(table);

        JTable lineTable = new JTableLineNumber(table);
        scrollPane.setRowHeaderView(lineTable);

        JFrame frame = new JFrame("Line Number Table");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    class TesteCellRenderer extends LabelGradiente implements TableCellRenderer {

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            selected = isSelected;
            setText(value != null ? value.toString() : null);
            setHorizontalAlignment(SwingConstants.CENTER);
            setOpaque(true);
            if (isSelected) {
                setForeground(Color.RED.darker());
            } else {
                setForeground(Color.BLACK.darker());
            }
            return this;
        }
    }

    class LabelGradiente extends JLabel {

        protected void paintComponent(Graphics g) {
            if (!isOpaque()) {
                super.paintComponent(g);
                return;
            }

            Graphics2D g2d = (Graphics2D) g;

            int w = getWidth();
            int h = getHeight();

            Color c1 = new Color(241, 237, 228);
            Color c2 = new Color(212, 208, 200);

            GradientPaint gp = null;
            if (selected) {
                gp = new GradientPaint(
                        0, 0, c2,
                        0, h, c1);
            } else {
                gp = new GradientPaint(
                        0, 0, c1,
                        0, h, c2);
            }
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, w, h);

            setOpaque(false);
            super.paintComponent(g);
            setOpaque(true);
        }
    }
}
