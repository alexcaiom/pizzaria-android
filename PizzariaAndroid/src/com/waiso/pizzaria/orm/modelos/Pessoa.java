/**
 * 
 */
package com.waiso.pizzaria.orm.modelos;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Dell
 *
 */
public class Pessoa {

	private String nome;
	private Integer idade;
	private Calendar dataDeNascimento;
	private String email;
	
	public Pessoa() {}

	public Pessoa(String nome, Integer idade,
			GregorianCalendar dataDeNascimento, String email) {
		this.nome = nome;
		this.idade = idade;
		this.dataDeNascimento = dataDeNascimento;
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public Calendar getDataDeNascimento() {
		return dataDeNascimento;
	}

	public void setDataDeNascimento(Calendar dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
