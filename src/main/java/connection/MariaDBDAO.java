package connection;

import reorderGUI.ConstantesGlobais;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe dedicada a implementação do DAO para o MariaDB<br>
 * @author Matheus Roberto
 */
public class MariaDBDAO implements DAO {
    String usuario = "si400_2022";
    String senha = "si400_2022";
    private static Connection con = null;
    Integer groupId = 11;

    /**
    * Método que obtém a conexão, caso ainda não exista<br>
    * via autenticação de usuário e senha.
    * @params usuário e senha, respectivamente.
    * @return a conexão com o MariaDB efetivamente, caso<br>
    * a autenticação ocorra com sucesso.
    * @author Matheus Roberto
    */
    @Override
    public Connection getConnection(String usuario, String senha) {
        if (con == null && usuario.equals(this.usuario) && senha.equals(this.senha)) {
            try {
                Class.forName("org.mariadb.jdbc.Driver");
                con = DriverManager.getConnection(
                        "jdbc:mariadb://143.106.243.64:3306/SI400",
                        usuario, senha
                );
                if (con != null) {
                    DatabaseMetaData meta = con.getMetaData();
                }
            } catch (SQLException ex) {
                Logger.getLogger(MariaDBDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return con;
        }else{
        return null;
        }
    }

     /**
    * Método que executa o comando SQL no banco para selecionar<br>
    * todos os fragmentos pertencentes ao nosso grupo e os ordena por<br>
    * numero da linha por fim armazena cada string em um ArrayList<br>
    * e retorna o texto completo.
    * @return um ArrayList contendo os fragmentos ordenados.
    * @author 
    * Gabriel Marino
    */
    @Override
    public List retrieve() {
        List<String> texto = new ArrayList();
        try {
            PreparedStatement statement = con.prepareStatement("SELECT * FROM Fragmentos WHERE groupId = " + groupId + "ORDER BY line");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                texto.add(rs.getString("text"));
                ConstantesGlobais.fileName = rs.getString("file");
            }
                    } catch (SQLException ex) {
            Logger.getLogger(MariaDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return texto;
    }
}
