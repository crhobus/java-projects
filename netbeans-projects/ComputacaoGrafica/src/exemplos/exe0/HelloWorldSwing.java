package exemplos.exe0;

public class HelloWorldSwing extends javax.swing.JFrame {

    private static final long serialVersionUID = 1L;
    private javax.swing.JButton jbHelloWorld;
    private javax.swing.JLabel jlHelloWorld;

    /**
     * Creates new form HelloWorldSwing
     */
    public HelloWorldSwing() {
        initComponents();
        setSize(400, 300);
        setResizable(false);
    }

    private void initComponents() {

        jbHelloWorld = new javax.swing.JButton();
        jlHelloWorld = new javax.swing.JLabel();

        getContentPane().setLayout(null);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jbHelloWorld.setText("Hello!");
        jbHelloWorld.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helloWorld(evt);
            }
        });

        getContentPane().add(jbHelloWorld);
        jbHelloWorld.setBounds(120, 200, 160, 60);

        jlHelloWorld.setFont(new java.awt.Font("Tahoma", 0, 24));
        getContentPane().add(jlHelloWorld);
        jlHelloWorld.setBounds(140, 60, 150, 70);

        pack();
    }

    private void helloWorld(java.awt.event.ActionEvent evt) {
        jlHelloWorld.setText("Teste Mundo!");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new HelloWorldSwing().setVisible(true);
            }
        });
    }
}
