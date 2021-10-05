package com.usersCRUD;

import java.io.IOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.UsersCONTROLLER.UsuarioCONTROLLER;
import com.UsersMODAL.Telefone;
import com.UsersMODAL.Usuarios;
import com.usersDAB.ConnectionDB;


/**
 * Servlet implementation class ProcessInfo
 */
@WebServlet("/")
public class ProcessInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UsuarioCONTROLLER usuarioCONTROLLER = new UsuarioCONTROLLER();
	List<Usuarios> listaDeUsuarios = new ArrayList<Usuarios>();
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
//		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
//		
//		request.setAttribute("listaDeUsuarios", usuarioCONTROLLER.ListarUsuariosBD());
//		dispatcher.forward(request, response);
		
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/new":
				novoFormulario(request, response);
				break;
			case "/insert":
				registrarUsuarioBD(request, response);
				break;
			case "/delete":
				deleteUser(request, response);
				break;
			case "/edit":
				mostrarFormularioAtualizado(request, response);
				break;
			case "/update":
				atualizarUsuarios(request, response);
				break;
			default:
				listarUsuarios(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
	
	private void mostrarFormularioAtualizado(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Usuarios existingUser = usuarioCONTROLLER.selectUser(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
		request.setAttribute("user", existingUser);
		dispatcher.forward(request, response);
		
	}

	private void atualizarUsuarios(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("nome");
		String email = request.getParameter("email");
		String telefone = request.getParameter("telefone");

		Usuarios usuario = new Usuarios(id, name, email,"", telefone);
		usuarioCONTROLLER.atualizarUsuarios(usuario);
		response.sendRedirect("list");
		
	}

	private void listarUsuarios(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Usuarios> listUser = usuarioCONTROLLER.listarUsuarios();
		request.setAttribute("listUser", listUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
		dispatcher.forward(request, response);
	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException, IOException, SQLException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		usuarioCONTROLLER.deleteUser(id);
		response.sendRedirect("list");
		
	}

	private void registrarUsuarioBD(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
		// TODO Auto-generated method stub
		String nome =  request.getParameter("nome");
		String email =  request.getParameter("email");
		String senha =  request.getParameter("senha");
		String telefone = request.getParameter("telefone");
		Usuarios user = new Usuarios(nome, email, senha, telefone);
		usuarioCONTROLLER.registrarUsuarioBD(user);
		response.sendRedirect("list");
	}

	private void novoFormulario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		doGet(request, response);
		
//		String url = "/index.jsp";
////		String nome =  request.getParameter("nome");
////		String email =  request.getParameter("email");
////		String senha =  request.getParameter("senha");
////		Telefone telefone = new Telefone(request.getParameter("telefone"), Integer.parseInt(request.getParameter("ddd")), request.getParameter("tipo"));
//		
//		//Usuarios userDB = new Usuarios(nome, email, senha, telefone);
//		
//		 // criar o banco de dados se necessario
//		CriarBD();
//		
//		// adiciona novo usuario ao banco de dados
////		RegistrarUsuarioBD(nome, email, senha, telefone);
//		
//		// Listar dados do banco de dados
//		usuarioCONTROLLER.ListarUsuariosBD();
//		
//		request.setAttribute("listaDeUsuarios", usuarioCONTROLLER.ListarUsuariosBD());
//		
//		getServletContext().getRequestDispatcher(url).forward(request, response);
	}
	
	private void CriarBD() {
		usuarioCONTROLLER.criarBD();	
	}

//	protected void RegistrarUsuarioBD(String nome, String email, String senha, Telefone telefone) {
//		usuarioCONTROLLER.RegistrarUsuarioBD(nome, email, senha, telefone.getTelefone());
//	}
}
