package com.usersDAB;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.UsersMODAL.Telefone;
import com.UsersMODAL.Usuarios;


public class UsuarioDB {
	private static final String INSERT_USERS_SQL = "INSERT INTO test6" + "  (nome, email, senha, telefone) VALUES "
			+ " (?, ?, ?, ?);";

	private static final String SELECT_USER_BY_ID = "select id,nome,email,telefone from test6 where id =?";
	private static final String SELECT_ALL_USERS = "select * from test6";
	private static final String DELETE_USERS_SQL = "delete from test6 where id = ?;";
	private static final String UPDATE_USERS_SQL = "update test6 set nome = ?,email= ?, telefone =? where id = ?;";

	public UsuarioDB() {
		
	}
	
	 protected Connection getConnection(){
	    	System.out.println("Conectando ao banco de dados");

	        try {
	        	return  DriverManager.getConnection("jdbc:h2:~/test2","sa","sa");
	        	} 
	        catch (Exception e) {
	        	throw new RuntimeException(e);
	        }
	    }
	
	public void CriarBD() {
		try {
			Statement s = getConnection().createStatement();
			
			DatabaseMetaData meta = getConnection().getMetaData();
		    ResultSet resultSet;
			resultSet = meta.getTables(null, null, "TEST6", new String[] {"TABLE"});
			if(!resultSet.next()) {
		    	s.executeUpdate("CREATE TABLE TEST6(ID INT IDENTITY(1,1) PRIMARY KEY, NOME VARCHAR(255), EMAIL VARCHAR(255), SENHA VARCHAR(10), TELEFONE VARCHAR(15));");
			}
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	    }
	}
	
//	public void RegistrarUsuarioDB(String nome, String email, String senha, String telefone) {
//		Statement s;
//		try {
//			s = connection.createStatement();
//			String query = "INSERT INTO TEST6 " + "(NOME, EMAIL, SENHA, TELEFONE)" + "VALUES('"+nome+ "', '"+ email + "', '"+ senha +"', '"+telefone+"')"; 
//			s.executeUpdate(query);
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	public void registrarUsuarioDB(Usuarios user) throws SQLException {
		System.out.println(INSERT_USERS_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
			preparedStatement.setString(1, user.getNome());
			preparedStatement.setString(2, user.getEmail());
			preparedStatement.setString(3, user.getSenha());
			preparedStatement.setString(4, user.getTelefone());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
//	public List<Usuarios> ListarUsuariosDB(){
//		List<Usuarios> usuariosList = new ArrayList<>();
//		
//		try {
//			Statement stmt = connection.createStatement();
//			
//			ResultSet rs = stmt.executeQuery("SELECT * FROM TEST6");
//			 
//			 while(rs.next()) {
//
//		           Usuarios usuarios = new Usuarios();
//
//		          //pega o valor da coluna nome, de cada linha:
//		          usuarios.setNome( rs.getString("nome")) ;
//		          usuarios.setEmail( rs.getString("email"));
//		          usuarios.setTelefone( rs.getString("telefone"));
//		          usuarios.setId(Integer.parseInt(rs.getString("id")));
//		          
//
//		          usuariosList.add(usuarios);
//
//		    }
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return usuariosList;
//	}
	
	public List<Usuarios> ListarUsuariosDB() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<Usuarios> users = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
	
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("nome");
				String email = rs.getString("email");
				String telefone = rs.getString("telefone");
				users.add(new Usuarios(id, name, email,"", telefone));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}
	
	public boolean atualizarUsuariosBD(Usuarios user) throws SQLException {
		boolean linhaAtualizada;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);) {
			statement.setString(1, user.getNome());
			statement.setString(2, user.getEmail());
			statement.setString(3, user.getTelefone());
			statement.setInt(4, user.getId());
			

			linhaAtualizada = statement.executeUpdate() > 0;
		}
		return linhaAtualizada;
	}
	
	public boolean deleteUser(int id) throws SQLException {
		boolean linhaDeletada;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
			statement.setInt(1, id);
			linhaDeletada = statement.executeUpdate() > 0;
		}
		return linhaDeletada;
	}
	
	public Usuarios selectUser(int id) {
		Usuarios user = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String name = rs.getString("nome");
				String email = rs.getString("email");
				String telefone = rs.getString("telefone");
				
				user = new Usuarios(id, name, email, "", telefone);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
}
