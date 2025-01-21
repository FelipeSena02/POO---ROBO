package robo;

import java.util.Random;
import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        Robo robo1 = new Robo("Vermelho");
        Robo robo2 = new Robo("Azul");

        System.out.println("Digite a posição do alimento (x y): ");
        int comidaX = scanner.nextInt();
        int comidaY = scanner.nextInt();

        boolean encontrado = false;
        int movimentosValidosRobo1 = 0, movimentosInvalidosRobo1 = 0;
        int movimentosValidosRobo2 = 0, movimentosInvalidosRobo2 = 0;

        while (!encontrado) {
            int direcao = random.nextInt(4) + 1;
            try {
                robo1.mover(direcao);
                movimentosValidosRobo1++;
            } catch (MovimentoInvalidoException e) {
                System.out.println("Robo 1: " + e.getMessage());
                movimentosInvalidosRobo1++;
            }

            if (robo1.encontrouAlimento(comidaX, comidaY)) {
                encontrado = true;
                System.out.println("Robo 1 encontrou o alimento!");
                break;
            }

            direcao = random.nextInt(4) + 1;
            try {
                robo2.mover(direcao);
                movimentosValidosRobo2++;
            } catch (MovimentoInvalidoException e) {
                System.out.println("Robo 2: " + e.getMessage());
                movimentosInvalidosRobo2++;
            }

            if (robo2.encontrouAlimento(comidaX, comidaY)) {
                encontrado = true;
                System.out.println("Robo 2 encontrou o alimento!");
                break;
            }
        }

        System.out.println("Movimentos válidos Robo 1: " + movimentosValidosRobo1);
        System.out.println("Movimentos inválidos Robo 1: " + movimentosInvalidosRobo1);
        System.out.println("Movimentos válidos Robo 2: " + movimentosValidosRobo2);
        System.out.println("Movimentos inválidos Robo 2: " + movimentosInvalidosRobo2);
    }
}