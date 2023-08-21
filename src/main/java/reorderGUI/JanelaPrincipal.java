package reorderGUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
* Interface gráfica da janela principal do sistema.<br>
* @author Tauan Rodrigues
* @author Prof. Dr. Andre F. de Angelis
*/
class JanelaPrincipal extends JFrame implements ActionListener, Runnable {

    private static final long serialVersionUID = 1L;
    private JPanel painelStatus;
    private JLabel labelStatus;
    private JMenuBar menuBar;
    private JMenu menuArquivo;
    private JMenu menuAjuda;
    private JMenuItem menuItemSaida;
    private JMenuItem menuItemAutenticacao;
    private JMenuItem menuItemAjuda;
    private JMenuItem menuItemSobre;
    private PainelFundo painelFundo;
    private JMenuItem menuItemLimpeza;
    private boolean rodando;

    
    JanelaPrincipal() throws HeadlessException {
        super(ConstantesGlobais.getNomeVersao());

        this.rodando = false;

        configuraJanela();
        criaAdicionaMenu();
        adicionaOuvinteMenus(this);
        inicializaAdicionaComponentes();
    }

    /**
    * Método que configura a janela.<br>
    * @author Tauan Rodrigues
    * @author Prof. Dr. Andre F. de Angelis
    */
    private void configuraJanela() {
        this.setSize((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.8), (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.77));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout(5, 5));
    }

    private void inicializaAdicionaComponentes() {

        this.painelStatus = new JPanel();
        this.labelStatus = new JLabel();
        this.painelStatus.add(labelStatus);
        this.painelStatus.setBackground(Color.gray);
        this.painelStatus.setBorder(BorderFactory.createEtchedBorder());
        this.add(painelStatus, BorderLayout.SOUTH);

        this.painelFundo = new PainelFundo();
        this.add(painelFundo, BorderLayout.CENTER);

    }
    
    /**
    * Método que altera a cor do fundo para os casos de sucesso
    * e fracasso de alterar o campo de texto.
    * @author Tauan Rodrigues
    * @author Prof. Dr. Andre F. de Angelis
    */
    void setMsgStatus(String texto, int sucesso) {
        this.labelStatus.setText(texto);
        if(sucesso == 1){
            this.painelStatus.setBackground(Color.green);
        }
        else{
            this.painelStatus.setBackground(Color.red);

        }
    }

    /**
    * Método que inicia a janela principal
    * @author Tauan Rodrigues
    * @author Prof. Dr. Andre F. de Angelis
    */
    void inicia() {
        this.setMsgStatus(ConstantesGlobais.statusConexao,0);
        this.setVisible(true);

        this.rodando = true;

        Thread serverDispatcher = new Thread(this);
        serverDispatcher.start();

        System.out.println(this.getClass().getResource("").getPath());
        
        while (ConstantesGlobais.statusConexao.equals("Desconectado")) {
            System.out.println("Aguardando Conexão!");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(ConstantesGlobais.statusConexao.equals("Conectado")){
            this.setMsgStatus(ConstantesGlobais.statusConexao,1);
            for (String linha : ConstantesGlobais.textoFinal) {
                painelFundo.getTxtArea().setText(painelFundo.getTxtArea().getText() + linha);
            }
            Exportacao.escrever(painelFundo.getTxtArea(), ConstantesGlobais.fileName);
        }
    }
 /**
    * Método que cria e Inicializa os Menus
    * @author Tauan Rodrigues
    * @author Prof. Dr. Andre F. de Angelis
    */
    private void criaAdicionaMenu() {
        menuArquivo = new JMenu("Arquivo");

        menuItemAutenticacao = new JMenuItem("Autenticação");
        menuArquivo.add(menuItemAutenticacao);

        menuItemLimpeza = new JMenuItem("Limpar");
        menuArquivo.add(menuItemLimpeza);

        menuArquivo.addSeparator();

        menuItemSaida = new JMenuItem("Saida");
        menuArquivo.add(menuItemSaida);

        menuAjuda = new JMenu("Ajuda");

        menuItemAjuda = new JMenuItem("Ajuda");
        menuAjuda.add(menuItemAjuda);

        menuItemSobre = new JMenuItem("Sobre");
        menuAjuda.add(menuItemSobre);

        menuBar = new JMenuBar();
        menuBar.add(menuArquivo);
        menuBar.add(menuAjuda);

        this.setJMenuBar(menuBar);
    }
 /**
    * Método que chama o metodo para adicionar ouvintes
    * @author Tauan Rodrigues
    * @author Prof. Dr. Andre F. de Angelis
    */
    void adicionaOuvinteMenus(ActionListener ouvinte) {
        for (Component menuPrincipal : this.getJMenuBar().getComponents()) {
            if (menuPrincipal instanceof JMenu) {
                adicionaOuvinteItemMenu(ouvinte, (JMenu) menuPrincipal);
            }
        }
    }
 /**
    * Método que adiciona ouvintes aos menus e itens de menus
    * @author Tauan Rodrigues
    * @author Prof. Dr. Andre F. de Angelis
    */
    private void adicionaOuvinteItemMenu(ActionListener ouvinte, JMenu menuPrincipal) {
        for (Component alvo : menuPrincipal.getMenuComponents()) {
            if (alvo instanceof JMenuItem) {
                ((JMenuItem) alvo).addActionListener(ouvinte);
            }
        }
    }

    /**
    * Método que implementa as funcionalidades dos botões da janela principal.<br>
    * @params evento é um click em um botão.<br>
    * @author Tauan Rodrigues
    * @author Prof. Dr. Andre F. de Angelis
    */
    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == menuItemSaida) {
            this.rodando = false;
            System.exit(NORMAL);
        }

        if (evento.getSource() == menuItemAjuda) {
            new Ajuda(this, ConstantesGlobais.getTextoAjuda());
        }

        if (evento.getSource() == menuItemSobre) {
            (new TelaDeMensagem(this, "Sobre - " + ConstantesGlobais.getNomeVersao(), ConstantesGlobais.getTextoSobre())).setVisible(true);
        }

        if (evento.getSource() == menuItemAutenticacao) {
            new Autenticacao(this);
            painelFundo.limpaConteudo();
        }
        if (evento.getSource() == menuItemLimpeza) {
            painelFundo.limpaConteudo();
        }
    }
 /**
    * Método que adiciona um temporizador
    * paea congelar o programa durantes alguns segundos
    * @author Tauan Rodrigues
    * @author Prof. Dr. Andre F. de Angelis
    */
    @Override
    public void run() {
        while (this.rodando) {
            //painelFundo.repaint();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
