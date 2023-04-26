package br.com.cotiinformatica.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.cotiinformatica.entities.Usuario;
import br.com.cotiinformatica.factories.ConnectionFactory;

public class UsuarioRepository {

	// método para inserir um usuário no banco de dados
	public void create(Usuario usuario) throws Exception {

		// abrindo conexão com o banco de dados
		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection connection = connectionFactory.getConnection();

		// executando o comando SQL
		PreparedStatement statement = connection
				.prepareStatement("insert into usuario(nome, email, senha) values(?, ?, md5(?))");
		statement.setString(1, usuario.getNome());
		statement.setString(2, usuario.getEmail());
		statement.setString(3, usuario.getSenha());
		statement.execute();

		// fechando a conexão
		connection.close();
	}

	// método para consultar 1 usuário no banco de dados através do email
	public Usuario findByEmail(String email) throws Exception {

		// abrindo conexão com o banco de dados
		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection connection = connectionFactory.getConnection();

		// executando o comando SQL
		PreparedStatement statement = connection.prepareStatement("select * from usuario where email = ?");
		statement.setString(1, email);
		ResultSet resultSet = statement.executeQuery();

		Usuario usuario = null; // vazio

		// verificando se algum usuário foi encontrado
		if (resultSet.next()) {

			usuario = new Usuario();

			usuario.setIdUsuario(resultSet.getInt("idusuario"));
			usuario.setNome(resultSet.getString("nome"));
			usuario.setEmail(resultSet.getString("email"));
			usuario.setSenha(resultSet.getString("senha"));
		}

		connection.close(); // fechando a conexão
		return usuario;
	}

	// método para consultar 1 usuário no banco de dados através do email e da senha
	public Usuario findByEmailAndSenha(String email, String senha) throws Exception {

		// abrido conexão com o banco de dados
		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection connection = connectionFactory.getConnection();

		// executando o comando SQL
		PreparedStatement preparedStatement = connection
				.prepareStatement("select * from usuario where email=? and senha=md5(?)");
		preparedStatement.setString(1, email);
		preparedStatement.setString(2, senha);
		ResultSet resultSet = preparedStatement.executeQuery();

		Usuario usuario = null;

		// verificando se algum registro foi encontrado
		if (resultSet.next()) {

			usuario = new Usuario();

			usuario.setIdUsuario(resultSet.getInt("idusuario"));
			usuario.setNome(resultSet.getString("nome"));
			usuario.setEmail(resultSet.getString("email"));
			usuario.setSenha(resultSet.getString("senha"));
		}

		connection.close(); // fechando a conexão
		return usuario;
	}

}