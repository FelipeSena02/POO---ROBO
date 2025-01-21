package com.java.br.uece.aluno;

public class Bomba extends Obstaculo {
	private int x, y;
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setX(int X) {
		if (X < 0) throw new IllegalArgumentException("Coordenada X inválida para a Bomba.");
		this.x = X;
	}
	
	public void setY(int Y) {
		if (Y < 0) throw new IllegalArgumentException("Coordenada Y inválida para a Bomba.");
		this.y = Y;
	}
	
	public void bater(Robo robo) {
		int roboX = robo.getX();
		int roboY = robo.getY();
		
		if(roboX == x && roboY == y ) {
			robo.setAtivo(false);
			System.out.println("O robô atingiu uma bomba! Você não pode mais movê-lo.");
			
			x = -999;
			y = -999;
			
			System.out.println("A bomba foi removida do jogo!");
		}
	}
}
