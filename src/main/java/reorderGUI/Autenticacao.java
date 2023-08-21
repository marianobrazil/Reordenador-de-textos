package reorderGUI;

import connection.DAO;
import connection.DAOCreator;
import javax.swing.*;
import java.awt.event.*;

/**
 * Interface gráfica para autenticação do usuário.
 *
 * @author Wara Inti Pardo
 */
public class Autenticacao extends JDialog {

    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField txtUsuario;
    private JTextField txtSenha;
    private JLabel lblUsuario;
    private JLabel lblSenha;

    /**
     *
     * @author Wara Inti Pardo
     */
    public Autenticacao(JFrame janelaPrincipal) {
        this.setTitle("Autenticação");
        setContentPane(contentPane);
        this.setSize(380, 170);
        setLocationRelativeTo(janelaPrincipal);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getRootPane().setDefaultButton(buttonOK);
        setModal(true);
        setResizable(true);
        buttonOK.addActionListener(e -> onOK());
        buttonCancel.addActionListener(e -> onCancel());
        setVisible(true);

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    /**
     * Método que é executado quando o usuário clica no botão "OK",<br>
     * que verifica se o usuário e a senha estão corretos, obtém a instância<br>
     * DAO do banco de dados e percorre o ArrayList retornado, adicionando<br>
     * as linhas e uma quebra de linha ao texto final a cada iteração.
     *
     * @author Wara Inti Pardo
     */
    private void onOK() {
        // add your code here
        String i = "LocalDAO";

        System.out.println(txtUsuario.getText() + txtSenha.getText());
        if (txtUsuario.getText().equals("si400_2022") && txtSenha.getText().equals("si400_2022")) {
            i = "MariaDBDAO";
        }

        DAO database = DAOCreator.factoryDAO(i);

        if (!database.getConnection("qualquercoisa", "qualquercoisa").equals(null)) {
            for (String linha : database.retrieve()) {
                ConstantesGlobais.textoFinal.add(linha + "\n");
            }
            ConstantesGlobais.statusConexao = "Conectado";
        }
        dispose();
    }

    /**
     * @author Wara Inti Pardo
     */
    private void onCancel() {
        // add your code here if necessary
        System.out.println("Cancel");
        dispose();
    }
}
