package robo;

public class Rocha extends Obstaculo {
    private int x, y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int X) {
        if (X < 0) throw new IllegalArgumentException("Coordenada X inválida para a Rocha.");
        this.x = X;
    }

    public void setY(int Y) {
        if (Y < 0) throw new IllegalArgumentException("Coordenada Y inválida para a Rocha.");
        this.y = Y;
    }

    public void bater(Robo robo) throws MovimentoInvalidoException {
        int roboX = robo.getX();
        int roboY = robo.getY();
        String movimentoAnterior = robo.getMovimentoAnterior();

        if (roboX == x && roboY == y) {
            System.out.println("O robô bateu em uma rocha!");

            switch (movimentoAnterior) {
                case "up":
                    robo.mover("down");
                    break;
                case "down":
                    robo.mover("up");
                    break;
                case "right":
                    robo.mover("left");
                    break;
                case "left":
                    robo.mover("right");
                    break;
                default:
                    System.out.println("Movimento anterior inválido ou desconhecido.");
            }

            System.out.println("O robô voltou para a posição anterior.");
            System.out.println("Posição do robô: X: " + robo.getX() + " Y: " + robo.getY());
        }
    }
}