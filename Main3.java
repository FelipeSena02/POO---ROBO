package pacote;

import java.util.Random;
import java.util.Scanner;

public class Main3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        final int TAMANHO = 4;


        System.out.println("Digite a posição do alimento (valores entre 0 e 3 para X e Y):");
        int alimentoX, alimentoY;

        while (true) {
            System.out.print("X: ");
            alimentoX = scanner.nextInt();
            System.out.print("Y: ");
            alimentoY = scanner.nextInt();

            if (alimentoX >= 0 && alimentoX < TAMANHO && alimentoY >= 0 && alimentoY < TAMANHO) {
                break;
            } else {
                System.out.println("Posição inválida. Tente novamente.");
            }
        }


        Robo roboNormal = new Robo("Amarelo");
        RoboInteligente roboInteligente = new RoboInteligente("Vermelho");

        System.out.println("\nAlimento posicionado em: (" + alimentoX + ", " + alimentoY + ")");

        boolean alimentoNormalEncontrado = false;
        boolean alimentoInteligenteEncontrado = false;
        int movimentosNormal = 0;
        int movimentosInteligente = 0;


        while (!alimentoNormalEncontrado || !alimentoInteligenteEncontrado) {
            try {

                String[] direcoes = {"up", "down", "right", "left"};
                if (!alimentoNormalEncontrado) {
                    String direcaoNormal = direcoes[random.nextInt(direcoes.length)];
                    System.out.println("\nMovendo robô normal (" + roboNormal.getCor() + ") para: " + direcaoNormal);
                    roboNormal.mover(direcaoNormal);
                    movimentosNormal++;
                }

                if (!alimentoInteligenteEncontrado) {
                    String direcaoInteligente = direcoes[random.nextInt(direcoes.length)];
                    System.out.println("Movendo robô inteligente (" + roboInteligente.getCor() + ") para: " + direcaoInteligente);
                    roboInteligente.mover(direcaoInteligente);
                    movimentosInteligente++;
                }


                if (!alimentoNormalEncontrado && roboNormal.encontrouAlimento(alimentoX, alimentoY)) {
                    System.out.println("Robô normal encontrou o alimento!");
                    alimentoNormalEncontrado = true;
                }

                if (!alimentoInteligenteEncontrado && roboInteligente.encontrouAlimento(alimentoX, alimentoY)) {
                    System.out.println("Robô inteligente encontrou o alimento!");
                    alimentoInteligenteEncontrado = true;
                }


                mostrarTabuleiro(TAMANHO, alimentoX, alimentoY, roboNormal, roboInteligente);

            } catch (MovimentoInvalidoException e) {
                System.out.println(e.getMessage());
            }
        }

        // Exibir o número total de movimentos
        System.out.println("\nNúmero de movimentos:");
        System.out.println("Robô normal: " + movimentosNormal);
        System.out.println("Robô inteligente: " + movimentosInteligente);

        scanner.close();
    }

    // tabuleiro
    private static void mostrarTabuleiro(int tamanho, int alimentoX, int alimentoY, Robo roboNormal, Robo roboInteligente) {
        System.out.println("\nTabuleiro:");

        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                if (i == alimentoY && j == alimentoX) {
                    System.out.print(" A ");
                } else if (i == roboNormal.getY() && j == roboNormal.getX()) {
                    System.out.print(" N ");
                } else if (i == roboInteligente.getY() && j == roboInteligente.getX()) {
                    System.out.print(" I ");
                } else {
                    System.out.print(" . ");
                }
            }
            System.out.println();
        }
    }
}




