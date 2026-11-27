public class Arma {
    private String nome;
    private int dano;
    private int danoBase;
    private double multiplicadorDeCritico = 1.5;

    public Arma(String nome, int dano, int danoBase) {
        this.nome = nome;
        this.dano = dano;
        this.danoBase = danoBase;
    }

    public int calcularCritico() {
        return (int) (multiplicadorDeCritico * danoBase);
    }

    public String getNome() {
        return nome;
    }

    public int getDano() {
        return dano;
    }

    public int getDanoBase() {
        return danoBase;
    }
}
