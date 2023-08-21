package connection;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import reorderGUI.ConstantesGlobais;

/**
 * Classe que implementa DAO para banco de dados local, no caso o SQLite<br>
 * @author Matheus Roberto
 */
public class LocalDAO implements DAO {

    public static final String DBURL = "jdbc:sqlite:SI400A.db";
    private static Connection con = null;
    Integer groupId = 11;

    /**
    * Método que obtém a conexão, caso ainda não exista<br>
    * via autenticação de usuário e senha.
    * @params usuário e senha, respectivamente.
    * @return a conexão com o banco de dados efetivamente, caso<br>
    * a autenticação ocorra com sucesso.
    * @author Matheus Roberto
    */
    @Override
    public Connection getConnection(String usuario, String senha) {
        if (con == null) {
            try {
                Class.forName("org.sqlite.JDBC");
                con = DriverManager.getConnection(DBURL);

                if (con != null) {
                    DatabaseMetaData meta = con.getMetaData();
                }
            } catch (SQLException e) {
                System.err.println("Exception: " + e.getMessage());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return con;
    }
    
    /**
    * Método que executa o comando SQL no banco para selecionar<br>
    * todos os fragmentos pertencentes ao nosso grupo e os ordena por<br>
    * numero da linha e finalmente armazena cada string em um ArrayList<br>
    * e retorna o texto completo.
    * @return um ArrayList contendo os fragmentos ordenados.
    * @author Gabriel Marino
    */
    @Override
    public List retrieve() {
        List<String> texto = new ArrayList();
        try {
            Statement s = (Statement) con.createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM Fragmentos WHERE groupId = " + groupId + " ORDER BY line");
            while (rs.next()) {
                texto.add(rs.getString("text"));
                ConstantesGlobais.fileName = rs.getString("file");
            }
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return texto;
    }
}
