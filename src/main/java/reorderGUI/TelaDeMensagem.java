package reorderGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/**
 * Classe que apresenta as mensagens de ajuda e sobre
 *
 * @author Tauan Rodrigues
 * @author Prof. Dr. Andre F. de Angelis
 */
class TelaDeMensagem extends JDialog implements ActionListener {

    private static final long serialVersionUID = 1L;
    private final JButton botaoFecha;
    private final JPanel painelTexto;
    private final JPanel painelBotoes;
    private final PainelLogotipo painelLogotipo;
    private final JTextArea areaTexto;

    /**
     * Construtor da classe
     *
     * @author Tauan Rodrigues
     */
    TelaDeMensagem(JFrame janelaPrincipal, String titulo, String texto) throws HeadlessException {
        super(janelaPrincipal, titulo);
        setSize(800, 320);
        setResizable(false);
        setLocationRelativeTo(janelaPrincipal);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setModalityType(Dialog.ModalityType.APPLICATION_MODAL);

        areaTexto = new JTextArea();
        areaTexto.setText(texto);
        formatAreaTexto();

        painelTexto = new JPanel();
        painelTexto.setBackground(Color.white);
        painelTexto.setBorder(new TitledBorder(new LineBorder(Color.gray), ConstantesGlobais.nome));
        painelTexto.add(areaTexto);
        add(painelTexto, BorderLayout.CENTER);

        painelLogotipo = new PainelLogotipo();
        painelLogotipo.setPreferredSize(new Dimension(100, 220));
        painelLogotipo.setBorder(new TitledBorder(new LineBorder(Color.gray), ConstantesGlobais.escola));
        painelLogotipo.setBackground(Color.white);
        add(painelLogotipo, BorderLayout.WEST);

        painelBotoes = new JPanel();
        botaoFecha = new JButton("Fecha");
        botaoFecha.addActionListener(this);
        painelBotoes.add(botaoFecha);
        add(painelBotoes, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        this.setVisible(false);
    }

    private void formatAreaTexto() {
        areaTexto.setPreferredSize(new Dimension(580, 220));
        areaTexto.setForeground(Color.black);
        areaTexto.setBackground(Color.white);
        areaTexto.setEditable(false);
        areaTexto.setFocusable(false);
        areaTexto.setLineWrap(true);
        areaTexto.setWrapStyleWord(true);
        areaTexto.setFont(new Font("Arial", Font.BOLD, 12));
    }
}
