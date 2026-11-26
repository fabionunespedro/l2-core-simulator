public class Personagem {
    private String nome;
    private int nivel;
    private int hpMaximo;
    private int hp;

    public Personagem(String nome, int nivel, int hpMaximo, int hp) {
        this.nome = nome;
        this.nivel = nivel;
        this.hpMaximo = hpMaximo;
        this.hp = hp;
    }

    public int calcularPorcentagem() {
        int porcentagem = (int) ((double) hp / hpMaximo * 100);
        if (porcentagem == 0 && hp > 0) {
            return 1;
        }
        return porcentagem;
    }

    public String definirEstado() {
        if (calcularPorcentagem() >= 70) {
            return "Saudavel";
        } else if (calcularPorcentagem() >= 30) {
            return "Ferido";
        } else if (calcularPorcentagem() >= 1) {
            return "Critico";
        } else {
            return "Morto";
        }
    }

    public String getNome() {
        return nome;
    }

    public int getNivel() {
        return nivel;
    }

    public int getHpMaximo() {
        return hpMaximo;
    }

    public int getHp() {
        return hp;
    }
}
