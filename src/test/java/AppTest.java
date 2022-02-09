import dao.DAOLoginRepository;
import model.ModelLogin;

public class AppTest {
	
	public static void main(String[] args) throws Exception {
		DAOLoginRepository daoUsuarioRepository = new DAOLoginRepository();
		
		ModelLogin login = new ModelLogin();
		
		login.setLogin("admin");
		login.setSenha("admin");
		
		boolean n = daoUsuarioRepository.validarAutenticacao(login);
		
		System.out.println(n);
		
	}
}
