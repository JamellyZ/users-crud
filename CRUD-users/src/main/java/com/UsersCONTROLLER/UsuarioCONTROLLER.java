package com.UsersCONTROLLER;


import java.sql.SQLException;
import java.util.List;

import com.UsersMODAL.Telefone;
import com.UsersMODAL.Usuarios;
import com.usersDAB.UsuarioDB;

public class UsuarioCONTROLLER {
	UsuarioDB bancoDeDados;
	
	public UsuarioCONTROLLER() {
		bancoDeDados = new UsuarioDB();
	}
	
	public void criarBD() {
		bancoDeDados.CriarBD();
	}
	
	public void registrarUsuarioBD(Usuarios user) throws SQLException {
		bancoDeDados.registrarUsuarioDB(user);
	}
	
	public List<Usuarios> ListarUsuariosBD(){	
		return bancoDeDados.ListarUsuariosDB();
	}

	public boolean deleteUser(int id) throws SQLException {
		return bancoDeDados.deleteUser(id);
		
	}

	public List<Usuarios> listarUsuarios() {
		return bancoDeDados.ListarUsuariosDB();
	}

	public void atualizarUsuarios(Usuarios usuario) throws SQLException {
		bancoDeDados.atualizarUsuariosBD(usuario);
		
	}

	public Usuarios selectUser(int id) {
		return bancoDeDados.selectUser(id);
	}
}
