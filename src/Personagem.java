public class Personagem {
    private String nome;
    private int nivel;
    private int hpMaximo;
    private int hp;
    private Arma armaEquipada;

    public Personagem(String nome, int nivel, int hpMaximo, int hp, Arma armaEquipada) {
        this.nome = nome;
        this.nivel = nivel;
        this.hpMaximo = hpMaximo;
        this.hp = hp;
        this.armaEquipada = armaEquipada;
    }

    public void atacar(Monstro alvo) {
        alvo.receberDano(armaEquipada.getDano());
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

    public Arma getArmaEquipada() {
        return armaEquipada;
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
