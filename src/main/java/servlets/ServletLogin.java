package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DAOLoginRepository;
import dao.DAOUsuarioRepository;
import model.ModelLogin;

/*O chamado Controller são as servlets
 *Intercepta os dados que vem da tela 
 * */
@WebServlet(urlPatterns = {"/principal/ServletLogin", "/ServletLogin"}) //Mapeamento de URL que vem da tela
public class ServletLogin extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private DAOLoginRepository daoLoginRepository = new DAOLoginRepository();
	private DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();

    public ServletLogin() {
    }

    //recebe os dados pela url em paremetros
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		
		request.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
		
		if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("logout")) {
			request.getSession().invalidate(); //invalida a sessão
			RequestDispatcher redirecionar = request.getRequestDispatcher("/index.jsp");
			redirecionar.forward(request, response);
		}
		else {
			doPost(request, response);
		}
		
	}

	//recebe os dados enviados por um formulario
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		String url = request.getParameter("url");
		
		try {
			//verifica se senha e login são válidos
			if(login != null && !login.isEmpty() && senha != null && !senha.isEmpty()) {
				ModelLogin modelLogin = new ModelLogin(login, senha);
				
				if(daoLoginRepository.validarAutenticacao(modelLogin)) {
					
					modelLogin = daoUsuarioRepository.consultaUsuarioLogado(login);
					
					request.getSession().setAttribute("usuario", modelLogin.getLogin());
					request.getSession().setAttribute("perfil", modelLogin.getPerfil());
					request.getSession().setAttribute("fotoUser", modelLogin.getFotoUser());
					
					if(url == null || url.equals("null")) {
						url = "principal/principal.jsp";
					}
					
					RequestDispatcher redirecionar = request.getRequestDispatcher(url);
					redirecionar.forward(request, response);
				}
				else {
					//... redireciona novamente à página de login
					RequestDispatcher redirecionar = request.getRequestDispatcher("/index.jsp");
					//...envia uma mensagem
					request.setAttribute("msg", "Login ou senha inválido!");
					redirecionar.forward(request, response);
				}
			}
			else { //se n for..
				//... redireciona novamente à página de login
				RequestDispatcher redirecionar = request.getRequestDispatcher("/index.jsp");
				//...envia uma mensagem
				request.setAttribute("msg", "Login ou senha inválido!");
				redirecionar.forward(request, response);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("/erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
		}
	}

}
