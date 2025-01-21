package com.java.br.uece.aluno;

public abstract class Obstaculo {
	protected int id;
	
	protected abstract void bater(Robo robo) throws MovimentoInvalidoException;

}
