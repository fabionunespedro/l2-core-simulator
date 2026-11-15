public class Main {
    public static void main(String[] args) {
        System.out.println("Initialize project lineage 2 core project");
        String buff = "Haste";
        String personagem = "Aria";
        String estado;
        char grade = 'X';
        final int HP_MAXIMO = 500;
        final double MULTIPLICADOR_DE_CRITICO = 1.5;
        final int DURACAO_BUFF = 754;
        int nivel = 62;
        int hpAtual = 360;
        int danoBase = 125;
        int calculoPorcentagem = (int) ((double) hpAtual / HP_MAXIMO * 100);
        int calculoCritico = (int) (MULTIPLICADOR_DE_CRITICO * danoBase);
        int buffTimeMinutos = DURACAO_BUFF / 60;
        int buffTimeSegundos = DURACAO_BUFF % 60;
        int nivelMinimoGrade;

        System.out.println("Personagem: " + personagem + " Nivel: " + nivel);

       if (calculoPorcentagem == 0 && hpAtual > 0){
           calculoPorcentagem = 1;
       }

       if (calculoPorcentagem >= 70) {
           estado = "Saudavel";
       } else if (calculoPorcentagem >= 30) {
           estado = "Ferido";
       } else if (calculoPorcentagem >= 1) {
           estado = "Critico";
       } else {
           estado = "Morto";
       }

       switch (grade) {
           case 'D' -> nivelMinimoGrade = 20;
           case 'C' -> nivelMinimoGrade = 40;
           case 'B' -> nivelMinimoGrade = 52;
           case 'A' -> nivelMinimoGrade = 61;
           case 'S' -> nivelMinimoGrade = 76;
           default -> nivelMinimoGrade = 0;
       }

       if (nivelMinimoGrade > 0) {
           if (nivel >= nivelMinimoGrade) {
               System.out.println("Equipamento grade " + grade + ": requer nivel " + nivelMinimoGrade);
               System.out.println("Pode usar");
           } else {
               System.out.println("Equipamento grade " + grade + ": requer nivel " + nivelMinimoGrade);
               System.out.println("não pode usar");
           }
       } else {
           System.out.println("Grau de equipamento inválido");
       }

        System.out.println("HP: " + hpAtual + "/" + HP_MAXIMO + " (" + calculoPorcentagem + "%)" + ", Estado: " + estado);
        System.out.println("Dano critico: " + calculoCritico);
        System.out.println("Buff " + buff + ": " + buffTimeMinutos + "min " + buffTimeSegundos + "s");
    }
}
