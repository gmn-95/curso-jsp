package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connection.SingleConnectionBanco;
import model.ModelLogin;

public class DAOUsuarioRepository {

	private Connection connection;
	
	public DAOUsuarioRepository() {
		connection = SingleConnectionBanco.getConnection();
	}
	
	public ModelLogin gravarUsuario(ModelLogin modelLogin, Long userLogado) throws Exception{
		
		if(modelLogin.isNovo()) { //Grava um novo usuário
			
			String sql = "insert into model_login(login, senha, nome, email, usuario_id, perfil, sexo, cep, logradouro, bairro, localidade, uf, numero) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, modelLogin.getLogin());
			statement.setString(2, modelLogin.getSenha());
			statement.setString(3, modelLogin.getNome());
			statement.setString(4, modelLogin.getEmail());
			statement.setLong(5, userLogado);
			statement.setString(6, modelLogin.getPerfil());
			statement.setString(7, modelLogin.getSexo());
			statement.setString(8, modelLogin.getCep());
			statement.setString(9, modelLogin.getLogradouro());
			statement.setString(10, modelLogin.getBairro());
			statement.setString(11, modelLogin.getLocalidade());
			statement.setString(12, modelLogin.getUf());
			statement.setString(13, modelLogin.getNumero());
			
			
			statement.execute();
			connection.commit();
			
			if(modelLogin.getFotoUser() != null && !modelLogin.getFotoUser().isEmpty()) {
				sql = "update model_login set foto_user = ?, extensao_foto_user = ? where login = ?";
				
				statement = connection.prepareStatement(sql);
				statement.setString(1, modelLogin.getFotoUser());
				statement.setString(2, modelLogin.getExtensaoFotoUser());
				statement.setString(3, modelLogin.getLogin());
				
				statement.execute();
				connection.commit();
			}
		}
		else {
			String sql = "UPDATE model_login set login = ?, nome = ?, email = ?, senha = ?, perfil = ?, sexo = ?, cep = ?, logradouro = ?, bairro = ?, localidade = ?, uf = ?, numero = ? where id = " + modelLogin.getId();
			
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(1, modelLogin.getLogin());
			statement.setString(2, modelLogin.getNome());
			statement.setString(3, modelLogin.getEmail());
			statement.setString(4, modelLogin.getSenha());
			statement.setString(5, modelLogin.getPerfil());
			statement.setString(6, modelLogin.getSexo());
			statement.setString(7, modelLogin.getCep());
			statement.setString(8, modelLogin.getLogradouro());
			statement.setString(9, modelLogin.getBairro());
			statement.setString(10, modelLogin.getLocalidade());
			statement.setString(11, modelLogin.getUf());
			statement.setString(12, modelLogin.getNumero());
			
			statement.executeUpdate();
			connection.commit();
			
			
			if(modelLogin.getFotoUser() != null && !modelLogin.getFotoUser().isEmpty()) {
				sql = "update model_login set foto_user = ?, extensao_foto_user = ? where id = ?";
				
				statement = connection.prepareStatement(sql);
				statement.setString(1, modelLogin.getFotoUser());
				statement.setString(2, modelLogin.getExtensaoFotoUser());
				statement.setLong(3, modelLogin.getId());
				
				statement.executeUpdate();
				connection.commit();
			}
			
		}
		
		return this.consultaUsuario(modelLogin.getLogin());
			
	}
	
	public int totalpagina(Long userLogado) throws Exception{
		
		String sql = "SELECT count(1) as total from model_login where usuario_id = " + userLogado;
		
		PreparedStatement statement = connection.prepareStatement(sql);
		
		ResultSet resultSet = statement.executeQuery();
		
		resultSet.next();
		
		Double cadastros = resultSet.getDouble("total");
		
		Double porpagina = 5.0;
		Double pagina = cadastros / porpagina;
		Double resto = pagina % 2;
		
		if(resto > 0) {
			pagina++;
		}
		
		return pagina.intValue();

	}
	
	public List<ModelLogin> consultaUsuarioList(Long userLogado) throws Exception{
		
		List<ModelLogin> retorno = new ArrayList<ModelLogin>();
		
		String sql = "SELECT * FROM model_login where user_admin is false and usuario_id = " + userLogado + " limit 5";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		
		ResultSet resultSet = statement.executeQuery();

		while(resultSet.next()) {
			
			ModelLogin modelLogin = new ModelLogin();
			modelLogin.setEmail(resultSet.getString("email"));
			modelLogin.setId(resultSet.getLong("id"));
			modelLogin.setLogin(resultSet.getString("login"));
			modelLogin.setNome(resultSet.getString("nome"));
			modelLogin.setUserAdmin(resultSet.getBoolean("user_admin"));
			modelLogin.setPerfil(resultSet.getString("perfil"));
			modelLogin.setSexo(resultSet.getString("sexo"));
			//modelLogin.setSenha(resultSet.getString("senha"));
			
			retorno.add(modelLogin);
		}
		
		return retorno;
	}
	
	public List<ModelLogin> consultaUsuarioListPaginada(Long userLogado, Integer offset) throws Exception{
		
		List<ModelLogin> retorno = new ArrayList<ModelLogin>();
		
		String sql = "SELECT * FROM model_login where user_admin is false and usuario_id = " + userLogado + " order by id offset " + offset + " limit 5";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		
		ResultSet resultSet = statement.executeQuery();

		while(resultSet.next()) {
			
			ModelLogin modelLogin = new ModelLogin();
			modelLogin.setEmail(resultSet.getString("email"));
			modelLogin.setId(resultSet.getLong("id"));
			modelLogin.setLogin(resultSet.getString("login"));
			modelLogin.setNome(resultSet.getString("nome"));
			modelLogin.setUserAdmin(resultSet.getBoolean("user_admin"));
			modelLogin.setPerfil(resultSet.getString("perfil"));
			modelLogin.setSexo(resultSet.getString("sexo"));
			//modelLogin.setSenha(resultSet.getString("senha"));
			
			retorno.add(modelLogin);
		}
		
		return retorno;
	}
	
	public List<ModelLogin> consultaUsuarioListOffSet(String nome, Long userLogado, int offset) throws Exception{
		
		List<ModelLogin> retorno = new ArrayList<ModelLogin>();
		
		String sql = "SELECT * FROM model_login WHERE upper(nome) like upper(?) and user_admin is false and usuario_id = ? order by id offset " + offset + " limit 5";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, "%" + nome + "%");
		statement.setLong(2, userLogado);
		
		ResultSet resultSet = statement.executeQuery();

		while(resultSet.next()) {
			
			ModelLogin modelLogin = new ModelLogin();
			modelLogin.setEmail(resultSet.getString("email"));
			modelLogin.setId(resultSet.getLong("id"));
			modelLogin.setLogin(resultSet.getString("login"));
			modelLogin.setNome(resultSet.getString("nome"));
			modelLogin.setPerfil(resultSet.getString("perfil"));
			modelLogin.setSexo(resultSet.getString("sexo"));
			//modelLogin.setSenha(resultSet.getString("senha"));
			
			retorno.add(modelLogin);
		}
		
		return retorno;
	}
	
	public List<ModelLogin> consultaUsuarioList(String nome, Long userLogado) throws Exception{
		
		List<ModelLogin> retorno = new ArrayList<ModelLogin>();
		
		String sql = "SELECT * FROM model_login WHERE upper(nome) like upper(?) and user_admin is false and usuario_id = ? limit 5";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, "%" + nome + "%");
		statement.setLong(2, userLogado);
		
		ResultSet resultSet = statement.executeQuery();

		while(resultSet.next()) {
			
			ModelLogin modelLogin = new ModelLogin();
			modelLogin.setEmail(resultSet.getString("email"));
			modelLogin.setId(resultSet.getLong("id"));
			modelLogin.setLogin(resultSet.getString("login"));
			modelLogin.setNome(resultSet.getString("nome"));
			modelLogin.setPerfil(resultSet.getString("perfil"));
			modelLogin.setSexo(resultSet.getString("sexo"));
			//modelLogin.setSenha(resultSet.getString("senha"));
			
			retorno.add(modelLogin);
		}
		
		return retorno;
	}
	
	public int consultaUsuarioListTotalPaginacao(String nome, Long userLogado) throws Exception{
		
		
		String sql = "SELECT count(1) as total FROM model_login WHERE upper(nome) like upper(?) and user_admin is false and usuario_id = ? ";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, "%" + nome + "%");
		statement.setLong(2, userLogado);
		
		ResultSet resultSet = statement.executeQuery();

		resultSet.next();
		
		Double cadastros = resultSet.getDouble("total");
		
		Double porpagina = 5.0;
		Double pagina = cadastros / porpagina;
		Double resto = pagina % 2;
		
		if(resto > 0) {
			pagina++;
		}
		
		return pagina.intValue();
	}
	
	public ModelLogin consultaUsuarioLogado(String login) throws Exception{
		
		ModelLogin modelLogin = new ModelLogin();
		
		String sql = "SELECT * FROM model_login WHERE login = ?";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, login);
		
		statement.executeQuery();
		
		ResultSet resultado = statement.getResultSet();
		
		if(resultado.next()) {
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setSenha(resultado.getString("senha"));
			modelLogin.setUserAdmin(resultado.getBoolean("user_admin"));
			modelLogin.setPerfil(resultado.getString("perfil"));
			modelLogin.setSexo(resultado.getString("sexo"));
			modelLogin.setFotoUser(resultado.getString("foto_user"));
			modelLogin.setExtensaoFotoUser(resultado.getString("extensao_foto_user"));
			modelLogin.setCep(resultado.getString("cep"));
			modelLogin.setLogradouro(resultado.getString("logradouro"));
			modelLogin.setBairro(resultado.getString("bairro"));
			modelLogin.setLocalidade(resultado.getString("localidade"));
			modelLogin.setUf(resultado.getString("uf"));
			modelLogin.setNumero(resultado.getString("numero"));
		}
		
		return modelLogin;
	}
	
	public ModelLogin consultaUsuario(String login) throws Exception{
		
		ModelLogin modelLogin = new ModelLogin();
		
		String sql = "SELECT * FROM model_login WHERE login = ? and user_admin is false";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, login);
		
		statement.executeQuery();
		
		ResultSet resultado = statement.getResultSet();
		
		if(resultado.next()) {
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setSenha(resultado.getString("senha"));
			modelLogin.setPerfil(resultado.getString("perfil"));
			modelLogin.setSexo(resultado.getString("sexo"));
			modelLogin.setFotoUser(resultado.getString("foto_user"));
			modelLogin.setExtensaoFotoUser(resultado.getString("extensao_foto_user"));
			modelLogin.setCep(resultado.getString("cep"));
			modelLogin.setLogradouro(resultado.getString("logradouro"));
			modelLogin.setBairro(resultado.getString("bairro"));
			modelLogin.setLocalidade(resultado.getString("localidade"));
			modelLogin.setUf(resultado.getString("uf"));
			modelLogin.setNumero(resultado.getString("numero"));
			
		}
		
		return modelLogin;
	}
	
	public ModelLogin consultaUsuario(String login, Long userLogado) throws Exception{
		
		ModelLogin modelLogin = new ModelLogin();
		
		String sql = "SELECT * FROM model_login WHERE login = ? and user_admin is false and usuario_id = ?";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, login);
		statement.setLong(2, userLogado);
		
		statement.executeQuery();
		
		ResultSet resultado = statement.getResultSet();
		
		if(resultado.next()) {
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setSenha(resultado.getString("senha"));
			modelLogin.setPerfil(resultado.getString("perfil"));
			modelLogin.setSexo(resultado.getString("sexo"));
			modelLogin.setFotoUser(resultado.getString("foto_user"));
			modelLogin.setExtensaoFotoUser(resultado.getString("extensao_foto_user"));
			modelLogin.setCep(resultado.getString("cep"));
			modelLogin.setLogradouro(resultado.getString("logradouro"));
			modelLogin.setBairro(resultado.getString("bairro"));
			modelLogin.setLocalidade(resultado.getString("localidade"));
			modelLogin.setUf(resultado.getString("uf"));
			modelLogin.setNumero(resultado.getString("numero"));
		}
		
		return modelLogin;
	}
	
	public ModelLogin consultaUsuarioId(String id, Long userLogado) throws Exception{
		
		ModelLogin modelLogin = new ModelLogin();
		
		String sql = "SELECT * FROM model_login WHERE id = ? and user_admin is false and usuario_id = ?";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, Long.parseLong(id));
		statement.setLong(2, userLogado);
		
		statement.executeQuery();
		
		ResultSet resultado = statement.getResultSet();
		
		if(resultado.next()) {
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setSenha(resultado.getString("senha"));
			modelLogin.setPerfil(resultado.getString("perfil"));
			modelLogin.setSexo(resultado.getString("sexo"));
			modelLogin.setFotoUser(resultado.getString("foto_user"));
			modelLogin.setExtensaoFotoUser(resultado.getString("extensao_foto_user"));
			modelLogin.setCep(resultado.getString("cep"));
			modelLogin.setLogradouro(resultado.getString("logradouro"));
			modelLogin.setBairro(resultado.getString("bairro"));
			modelLogin.setLocalidade(resultado.getString("localidade"));
			modelLogin.setUf(resultado.getString("uf"));
			modelLogin.setNumero(resultado.getString("numero"));
		}
		
		return modelLogin;
	}
	
	public boolean validaLogin(String login) throws Exception {
		
		String sql = "SELECT count(1) > 0 as existe from model_login where login = '" + login + "'";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		
		ResultSet resultado = statement.executeQuery();
		
		if(resultado.next()) {
			return resultado.getBoolean("existe");
		}
		
		return false;
	}
	
	public void deletarUsuario(String idUsuario) throws Exception{
		
		String sql = "DELETE FROM model_login WHERE id = ? and user_admin is false";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, Long.parseLong(idUsuario));
		statement.executeUpdate();
		
		connection.commit();
		
	}
}
