package filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import connection.SingleConnectionBanco;


@WebFilter(urlPatterns = {"/principal/*"}) //Intercepta tods as requisições que vierem do projeto ou mapeamento
public class FilterAutenticacao implements Filter {
	
	private static Connection connection;

    public FilterAutenticacao() {
    }
    
    /* Encerra os processos quando o servidor é parado
     * Ex: Encerra a conexão com o banco de dados
     * */
	public void destroy() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Intercepta as requisições e das repostas no sistema
	 * Tudo o que fizermos no sistema, passará por aqui
	 * Ex: Validação de autenticação; Dar commit e rolback de trasações no banco;
	 * redirecionamento específicos
	 * */ 
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		try {
			request.setCharacterEncoding("UTF-8");
		    response.setContentType("text/html; charset=UTF-8");
			
			HttpServletRequest req = (HttpServletRequest) request;
			HttpSession session = req.getSession();
			
			String usuarioLogado = (String) session.getAttribute("usuario");
			
			String urlParaAutenticar = req.getServletPath(); /*URL que está sendo acessada*/
			
			/* Validar se está logado, se não, redireciona para a tela de login*/
			if (usuarioLogado == null && !urlParaAutenticar.equalsIgnoreCase("/principal/ServletLogin")) {
				System.out.println(usuarioLogado);
				RequestDispatcher redireciona = request.getRequestDispatcher("../index.jsp?url=" + urlParaAutenticar);
				request.setAttribute("msg", "Por favor, realize o login");
				redireciona.forward(request, response); /*comando de redirecionamento*/
				
				return; /*Para a execução e redireciona para o login*/
			}
			else {
				chain.doFilter(request, response);
			}
			
			connection.commit(); //Se der tudo certo, commit as alterações no banco
			
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("/erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	/* Inicia os procesos ou recursos quando o servidor sobe o projeto
	 * Ex: conexao com o banco de dados
	 * */
	public void init(FilterConfig fConfig) throws ServletException {
		connection = SingleConnectionBanco.getConnection();
	}

}
