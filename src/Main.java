public class Main {
    public static void main(String[] args) {
        System.out.println("Initialize project lineage 2 core project");
        String buff = "Haste";
        String personagem = "Aria";
        final int HP_MAXIMO = 500;
        final double MULTIPLICADOR_DE_CRITICO = 1.5;
        final int DURACAO_BUFF = 754;
        int nivel = 20;
        int hpAtual = 360;
        int danoBase = 125;
        int calculoPorcentagem = (int) ((double) hpAtual / HP_MAXIMO * 100);
        int calculoCritico = (int) (MULTIPLICADOR_DE_CRITICO * danoBase);
        int buffTimeMinutos = DURACAO_BUFF / 60;
        int buffTimeSegundos = DURACAO_BUFF % 60;

        System.out.println("Personagem: " + personagem + " Nivel: " + nivel);

       if (calculoPorcentagem == 0 && hpAtual > 0){
           calculoPorcentagem = 1;
       }

        System.out.println("HP: " + hpAtual + "/" + HP_MAXIMO + " (" + calculoPorcentagem + "%)");
        System.out.println("Dano critico: " + calculoCritico);
        System.out.println("Buff " + buff + ": " + buffTimeMinutos + "min " + buffTimeSegundos + "s");
    }
}
