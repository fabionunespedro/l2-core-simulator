public class Monstro {
    private String nome;
    private int hp;

    public Monstro(String nome, int hp) {
        this.nome = nome;
        this.hp = hp;
    }

    public void receberDano(int dano) {
        hp -= dano;
        if (hp < 0) {
            hp = 0;
        }
    }

    public boolean estaMorto() {
        return hp <= 0;
    }

    public String getNome() {
        return nome;
    }

    public int getHp() {
        return hp;
    }
}
