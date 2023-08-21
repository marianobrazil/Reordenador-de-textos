package reorderGUI;

import javax.swing.*;
import java.awt.event.*;

/**
 * 
 * @author Wara Inti Pardo
 */
public class Ajuda extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JTextArea lblTextoSobre;
    private JLabel imgMatheus;
    private JLabel imgTauan;
    private JLabel imgWara;
    private JLabel imgGabriel;
    private JLabel imgRodrigo;


/**
 * 
 * @author Wara Inti Pardo
 */
    public Ajuda(JanelaPrincipal janelaPrincipal, String texto) {
        this.setTitle("Sobre");
        setContentPane(contentPane);
        this.setSize(1200, 768);
        setLocationRelativeTo(janelaPrincipal);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getRootPane().setDefaultButton(buttonOK);
        setModal(true);
        setResizable(true);
        buttonOK.addActionListener(e -> onOK());
        setVisible(true);
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });



        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }
/**
 * 
 * @author Wara Inti Pardo
 */
    private void onOK() {
        // add your code here
        dispose();
    }
}
