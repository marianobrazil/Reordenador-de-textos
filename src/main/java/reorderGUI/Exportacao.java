package reorderGUI;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JTextArea;

/**
 * Classe de método único para criação e escrita do arquivo resultante.<br>
 * @author Rodrigo Marabesi
 */
public class Exportacao {

    /**
     * Método unico da classe para criação do arquivo<br>
     * e escrita no mesmo<br>
     * <b>Uso:</b><br>
     * Exportacao.escrever(JTextArea texto a ser gravado, String nome do
     * arquivo);<br>
     * @param jText Recebe o texto que será gravado no arquivo criado.<br>
     * @param name Recebe o nome do arquivo a ser escrito.<br>
     * @author Rodrigo Marabesi
     */
    public static void escrever(JTextArea jText, String name) {
        try {
            FileWriter arq = new FileWriter(name);
            try ( PrintWriter gravarArq = new PrintWriter(arq)){
                gravarArq.print(jText.getText());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
