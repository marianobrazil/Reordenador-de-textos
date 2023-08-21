package connection;

import java.sql.Connection;
import java.util.List;

/**
 * Interface DAO a ser implementada para cada SGBD específico.<br>
 * @author Matheus Roberto
 */
public interface DAO {
    public Connection getConnection(String usuario, String senha);
    public List<String> retrieve();
}
