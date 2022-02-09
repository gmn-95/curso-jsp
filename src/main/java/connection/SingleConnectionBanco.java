package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnectionBanco {
	
	private static String banco = "jdbc:postgresql://localhost:5432/curso-jsp?autoReconnect=true";
	private static String usuario = "postgres";
	private static String senha = "postgres";
	
	private static Connection connection = null;
	
	static {
		conectar();
	}
	
	public SingleConnectionBanco() { // quando houver uma instância, vai conectar
		conectar();
	}
	
	private static void conectar() {
		try {
			
			if(connection == null) {
				Class.forName("org.postgresql.Driver"); 
				connection = DriverManager.getConnection(banco, usuario, senha);
				connection.setAutoCommit(false); 
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		return connection;
	}
	
}
