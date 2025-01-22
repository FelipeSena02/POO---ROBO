import java.util.Random;

public class RoboInteligente extends Robo {
    public RoboInteligente(String cor) {
        super(cor);
    }
  @Override
    public void mover (String direcao) throws MovimentoInvalidoException {
      try {
          super.mover(direcao);
      } catch (MovimentoInvalidoException e) {
          System.out.println("Movimento inválido. Tentando outra direção...");
          Random random = new Random();
          String[] direcoes = {"up", "down", "right", "left"};
          String novaDirecao;

          do {
              novaDirecao = direcoes[random.nextInt(direcoes.length)];
          } while (novaDirecao.equals(direcao));

          super.mover(novaDirecao);
      }

  }

}