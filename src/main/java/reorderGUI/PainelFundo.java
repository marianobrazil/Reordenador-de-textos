package reorderGUI;

import java.awt.*;

import javax.swing.*;

/**
 * Classe onde se encontra o JTextArea
 *
 * @author Tauan Rodrigues
 * @author Prof. Dr. Andre F. de Angelis
 */
class PainelFundo extends JPanel {

    private static final long serialVersionUID = 1L;
    private Color corFrente = Color.white;
    private Color corFundo = Color.lightGray;
    private JTextArea txtArea;

    /**
     * Construtor da Classe
     *
     * @author Tauan Rodrigues
     */
    PainelFundo() {
        super();
        this.txtArea = new JTextArea("", 30, 60);
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        JScrollPane scrollPane = new JScrollPane(txtArea);
        this.txtArea.setBorder(BorderFactory.createLineBorder(Color.black));
        txtArea.setLineWrap(true);
        txtArea.setFont(new Font("Verdana", Font.BOLD, 15));
        this.txtArea.setEditable(true);
        this.setForeground(corFrente);
        this.setBackground(corFundo);
        this.add(scrollPane, BorderLayout.PAGE_START);

    }

    /**
     * metodo para limpar a tela e evitar de que o arquivo final saia errado
     *
     * @author Tauan Rodrigues
     */
    public void limpaConteudo() {
        this.txtArea.setText(null);
    }

    /**
     * metodo getter para o JTextArea
     *
     * @author Tauan Rodrigues
     */
    public JTextArea getTxtArea() {
        return this.txtArea;
    }

}
