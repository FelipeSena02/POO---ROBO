package pacote;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main4 {
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

        List<Bomba> bombas = new ArrayList<>();
        List<Rocha> rochas = new ArrayList<>();

        System.out.println("\nQuantas bombas deseja adicionar?");
        int numBombas = scanner.nextInt();

        for (int i = 0; i < numBombas; i++) {
            Bomba bomba = new Bomba();
            System.out.println("Digite a posição da bomba " + (i + 1) + " (valores entre 0 e 3 para X e Y):");

            while (true) {
                System.out.print("X: ");
                int x = scanner.nextInt();

                System.out.print("Y: ");
                int y = scanner.nextInt();

                if (x >= 0 && x < TAMANHO && y >= 0 && y < TAMANHO && (x != alimentoX || y != alimentoY)) {
                    bomba.setX(x);
                    bomba.setY(y);
                    bombas.add(bomba);
                    break;

                } else {

                    System.out.println("Posição inválida ou já ocupada pelo alimento. Tente novamente.");
                }
            }
        }

        System.out.println("\nQuantas rochas deseja adicionar?");
        int numRochas = scanner.nextInt();

        for (int i = 0; i < numRochas; i++) {
            Rocha rocha = new Rocha();
            System.out.println("Digite a posição da rocha " + (i + 1) + " (valores entre 0 e 3 para X e Y):");

            while (true) {
                System.out.print("X: ");
                int x = scanner.nextInt();

                System.out.print("Y: ");
                int y = scanner.nextInt();

                if (x >= 0 && x < TAMANHO && y >= 0 && y < TAMANHO && (x != alimentoX || y != alimentoY)) {
                    rocha.setX(x);
                    rocha.setY(y);
                    rochas.add(rocha);
                    break;

                } else {
                    System.out.println("Posição inválida ou já ocupada pelo alimento. Tente novamente.");
                }
            }
        }

        System.out.println("\nAlimento posicionado em: (" + alimentoX + ", " + alimentoY + ")");

        boolean alimentoNormalEncontrado = false;
        boolean alimentoInteligenteEncontrado = false;
        int movimentosNormal = 0;
        int movimentosInteligente = 0;

        scanner.nextLine();

        boolean continuarInterativo = true;

        while ((!alimentoNormalEncontrado || !alimentoInteligenteEncontrado) &&
                (roboNormal.getAtivo() || roboInteligente.getAtivo())) {

            if (continuarInterativo) {
                System.out.println("Pressione Enter para o próximo round ou digite 'sair' para simular até o fim:");
                String entrada = scanner.nextLine();

                if (entrada.equalsIgnoreCase("sair")) {
                    continuarInterativo = false;
                }
            }

            try {
                String[] direcoes = {"up", "down", "right", "left"};

                if (!alimentoNormalEncontrado && roboNormal.getAtivo()) {
                    String direcaoNormal = direcoes[random.nextInt(direcoes.length)];
                    System.out.println("\nMovendo robô normal (" + roboNormal.getCor() + ") para: " + direcaoNormal);
                    roboNormal.mover(direcaoNormal);
                    movimentosNormal++;

                    for (Bomba bomba : bombas) {
                        bomba.bater(roboNormal);
                    }
                    for (Rocha rocha : rochas) {
                        rocha.bater(roboNormal);
                    }

                    if (roboNormal.encontrouAlimento(alimentoX, alimentoY)) {
                        System.out.println("Robô normal encontrou o alimento!");
                        alimentoNormalEncontrado = true;
                    }
                }

                if (!alimentoInteligenteEncontrado && roboInteligente.getAtivo()) {
                    String direcaoInteligente = direcoes[random.nextInt(direcoes.length)];
                    System.out.println("Movendo robô inteligente (" + roboInteligente.getCor() + ") para: " + direcaoInteligente);
                    roboInteligente.mover(direcaoInteligente);
                    movimentosInteligente++;

                    for (Bomba bomba : bombas) {
                        bomba.bater(roboInteligente);
                    }
                    for (Rocha rocha : rochas) {
                        rocha.bater(roboInteligente);
                    }

                    if (roboInteligente.encontrouAlimento(alimentoX, alimentoY)) {
                        System.out.println("Robô inteligente encontrou o alimento!");
                        alimentoInteligenteEncontrado = true;
                    }
                }

                mostrarTabuleiro(TAMANHO, alimentoX, alimentoY, roboNormal, roboInteligente, bombas, rochas);

            } catch (MovimentoInvalidoException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("\nNúmero de movimentos:");
        System.out.println("Robô normal: " + movimentosNormal);
        System.out.println("Robô inteligente: " + movimentosInteligente);

        scanner.close();
    }

    private static void mostrarTabuleiro(int tamanho, int alimentoX, int alimentoY, Robo roboNormal, Robo roboInteligente, List<Bomba> bombas, List<Rocha> rochas) {
        System.out.println("\nTabuleiro:");

        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                final int finalI = i;
                final int finalJ = j;

                if (i == alimentoY && j == alimentoX) {
                    System.out.print(" A ");

                } else if (i == roboNormal.getY() && j == roboNormal.getX() && roboNormal.getAtivo()) {
                    System.out.print(" N ");

                } else if (i == roboInteligente.getY() && j == roboInteligente.getX() && roboInteligente.getAtivo()) {
                    System.out.print(" I ");

                } else if (bombas.stream().anyMatch(b -> b.getX() == finalJ && b.getY() == finalI)) {
                    System.out.print(" B ");

                } else if (rochas.stream().anyMatch(r -> r.getX() == finalJ && r.getY() == finalI)) {
                    System.out.print(" R ");

                } else {
                    System.out.print(" . ");
                }
            }
            System.out.println();
        }
    }
}

