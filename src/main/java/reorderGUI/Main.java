package reorderGUI;

import java.awt.HeadlessException;

/**
* Classe Main do programa que abre a interface gráfica, a inicializa,<br>
* dispara exceções e mostra a mensagem de erro no console.
* @author Tauan Rodrigues
* @author Prof. Dr. Andre F. de Angelis
*/
public class Main
   {
   /**
   * Método main que abre a interface gráfica, a inicializa e dispara exceções.
   * @params args são os argumentos passados por linha de comando.
   * @author Tauan Rodrigues
   * @author Prof. Dr. Andre F. de Angelis
   */
   public static void main(String[] args)
      {
      try
         {
         JanelaPrincipal programa = new JanelaPrincipal();
         programa.inicia();
         }
      catch (HeadlessException excecao)
         {
         imprMsgErroETermina("Programa terminado por uma HeadlessException no metodo main()", excecao);
         }
      catch (Exception excecao)
         {
         imprMsgErroETermina("Programa terminado por uma Generic Exception no metodo main()", excecao);
         }
      }

   /**
   * Método que é executado caso ocorra alguma exceção no método main,<br>
   * imprimindo mensagem de erro no console.
   * @author Tauan Rodrigues
   * @author Prof. Dr. Andre F. de Angelis
   */
   private static void imprMsgErroETermina(String mensagem, Exception ocorrencia)
      {
      System.out.println("Mensagem de erro:\t" + mensagem);
      System.out.println("Texto da excecao:\t" + ocorrencia.getMessage());
      System.exit(1);
      }
   }
