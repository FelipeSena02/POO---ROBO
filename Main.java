package pacote;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Robo robo = new Robo("Azul");

        System.out.println("Digite a posição do alimento (x y): ");
        int comidaX = scanner.nextInt();
        int comidaY = scanner.nextInt();

        while (!robo.encontrouAlimento(comidaX, comidaY)) {
            System.out.println("Movimente o robô (up/down/right/left): ");
            String movimento = scanner.next();
            try {
                robo.mover(movimento);
            } catch (MovimentoInvalidoException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("O robô encontrou o alimento!");
    }
}