package com.java.br.uece.aluno;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Robo robo = new Robo("Azul");
        Bomba bomba = new Bomba();
        Rocha rocha = new Rocha();
        
        String movimento = "";

        System.out.println("Digite a posição do alimento (x y): ");
        int comidaX = scanner.nextInt();
        int comidaY = scanner.nextInt();
        
        System.out.println("Digite a posição da bomba (x y): ");
        int bombaX = scanner.nextInt();
        int bombaY = scanner.nextInt();
        
        System.out.println("Digite a posição da rocha (x y)");
        int rochaX = scanner.nextInt();
        int rochaY = scanner.nextInt();
        
        
        bomba.setX(bombaX);
        bomba.setY(bombaY);
        
        rocha.setX(rochaX);
        rocha.setY(rochaY);
        
        while (!robo.encontrouAlimento(comidaX, comidaY)) {
        	robo.setMovimentoAnterior(movimento);
        	
            System.out.println("Movimente o robô (up/down/right/left): ");
            movimento = scanner.next();
            
            
            if(robo.getAtivo()) {
            	try {
                    robo.mover(movimento);
                    bomba.bater(robo);
                    rocha.bater(robo);
                    
                } catch (MovimentoInvalidoException e) {
                    System.out.println(e.getMessage());
                }
            	
            	
            }else {
            	System.out.println("O robô não pode se mover, ele foi atingido por uma bomba.");
            }
                  
        }
        
        System.out.println("O robô encontrou o alimento!");

        
    }
}