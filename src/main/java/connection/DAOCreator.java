package connection;

/**
 * Classe que de um só método que retorna<br>
 * uma instância DAO do banco especificado.
 * @author Matheus Roberto
 */
public class DAOCreator {
    /**
    * Método que recebe o tipo do BD e retorna<br>
    * uma instância DAO do banco especificado.
    * <b>Uso:</b>
    * DAOCreator.factoryDAO(String database);<br>
    * @param database é uma String contendo o tipo do BD
    * @return uma instância DAO do banco especificado. 
    * @author Matheus Roberto
    */
    public static DAO factoryDAO(String database){
        if(database.equals("LocalDAO")){
            return new LocalDAO();
        }else if(database.equals("MariaDBDAO")){
            return new MariaDBDAO();
        }
        throw new IllegalArgumentException("Banco não cadastrado");
    }
}
