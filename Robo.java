package robo;

public class Robo {
    private int x, y;
    private String cor;

    public Robo(String cor) {
        this.cor = cor;
        this.x = 0;
        this.y = 0;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }
    
    public void mover(String direcao) throws MovimentoInvalidoException {
        switch(direcao.toLowerCase()) {
            case "up":
                if (y + 1 < 0) throw new MovimentoInvalidoException("Movimento inválido: " + direcao);
                y += 1;
                break;
            case "down":
                if (y - 1 < 0) throw new MovimentoInvalidoException("Movimento inválido: " + direcao);
                y -= 1;
                break;
            case "right":
                if (x + 1 < 0) throw new MovimentoInvalidoException("Movimento inválido: " + direcao);
                x += 1;
                break;
            case "left":
                if (x - 1 < 0) throw new MovimentoInvalidoException("Movimento inválido: " + direcao);
                x -= 1;
                break;
            default:
                throw new IllegalArgumentException("Direção inválida: " + direcao);
        }
        System.out.println("Posição atual: (" + x + ", " + y + ")");
    }
    
    public void mover(int direcao) throws MovimentoInvalidoException {
        switch(direcao) {
            case 1:
                mover("up");
                break;
            case 2:
                mover("down");
                break;
            case 3:
                mover("right");
                break;
            case 4:
                mover("left");
                break;
            default:
                throw new IllegalArgumentException("Direção inválida: " + direcao);
        }
    }
    public boolean encontrouAlimento(int comidaX, int comidaY) {
        return this.x == comidaX && this.y == comidaY;
    }
}
