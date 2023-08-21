package reorderGUI;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
     * Classe onde se encontram as imagens do programa
     * 
     * @author Tauan Rodrigues
     */
class PainelLogotipo extends JPanel {
    public ImageIcon matheusImg = new ImageIcon(getClass().getResource(ConstantesGlobais.matheus));
    public ImageIcon tauanImg = new ImageIcon(getClass().getResource(ConstantesGlobais.tauan));
    public ImageIcon waraImg = new ImageIcon(getClass().getResource(ConstantesGlobais.wara));
    public ImageIcon gabrielImg = new ImageIcon(getClass().getResource(ConstantesGlobais.gabriel));
    public ImageIcon rodrigoImg = new ImageIcon(getClass().getResource(ConstantesGlobais.rodrigo));
    
    JLabel labelMatheus = new JLabel(matheusImg);
    JLabel labelTauan = new JLabel(tauanImg);
    JLabel labelWara = new JLabel(waraImg);
    JLabel labelGabriel = new JLabel(gabrielImg);
    JLabel labelRodrigo = new JLabel(rodrigoImg);

    PainelLogotipo(){
        add(labelMatheus);
        add(labelTauan);
        add(labelWara);
        add(labelGabriel);
        add(labelRodrigo);
        
        setSize( 150, 150);
        setVisible(true);
    }
}
