package com.UsersMODAL;

public class Telefone {
	private String telefone;
	private int ddd;
	private String tipo;
	
	public Telefone(String telefone, int ddd, String tipo) {
		this.telefone = telefone;
		this.ddd = ddd;
		this.tipo = tipo;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public int getDdd() {
		return ddd;
	}

	public void setDdd(int ddd) {
		this.ddd = ddd;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
