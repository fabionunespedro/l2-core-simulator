public class Main {
    public static void main(String[] args) {
        System.out.println("Initialize project lineage 2 core project");
        String buff = "Haste";
        String personagem = "Aria";
        String monstro = "Goblin";
        char grade = 'C';
        final int HP_MAXIMO = 500;
        final double MULTIPLICADOR_DE_CRITICO = 1.5;
        final int DURACAO_BUFF = 754;
        int limiteDeTurnos = 10;
        int turnosPorCombate = 0;
        int nivel = 20;
        int hpAtual = 360;
        int hpMonstro = 250;
        int danoBase = 125;
        int dano = 45;
        int calculoCritico = (int) (MULTIPLICADOR_DE_CRITICO * danoBase);
        int buffTimeMinutos = DURACAO_BUFF / 60;
        int buffTimeSegundos = DURACAO_BUFF % 60;
        int nivelMinimoGrade;
        int porcentagem = calcularPorcentagem(hpAtual, HP_MAXIMO);
        String estado = definirEstado(porcentagem);

        System.out.println("Personagem: " + personagem + " Nivel: " + nivel);

       switch (grade) {
           case 'D' -> nivelMinimoGrade = 20;
           case 'C' -> nivelMinimoGrade = 40;
           case 'B' -> nivelMinimoGrade = 52;
           case 'A' -> nivelMinimoGrade = 61;
           case 'S' -> nivelMinimoGrade = 76;
           default -> nivelMinimoGrade = 0;
       }

       if (nivelMinimoGrade > 0) {
           System.out.println("Equipamento grade " + grade + ": requer nivel " + nivelMinimoGrade);
           if (nivel >= nivelMinimoGrade) {
               System.out.println("Pode usar");
           } else {
               System.out.println("não pode usar");
           }
       } else {
           System.out.println("Grau de equipamento inválido");
       }

       if (dano <= 0) {
           System.out.println("Dano inválido");
       } else {
           while (hpMonstro > 0 && turnosPorCombate < limiteDeTurnos) {
               turnosPorCombate++;
               if (turnosPorCombate % 3 == 0) {
                   System.out.println("Turno " + turnosPorCombate + ": " + personagem + " errou o golpe!");
                   continue;
               }
               hpMonstro -= dano;
               if (hpMonstro < 0) {
                   hpMonstro = 0;
               }
               System.out.println("Turno " + turnosPorCombate + ": " + monstro + " recebeu " + dano + " de dano (HP restante: " + hpMonstro + ")");
           }
           if(hpMonstro <= 0) {
               System.out.println(monstro + " derrotado em " + turnosPorCombate + " turnos");
           } else {
               System.out.println(monstro + " fugiu!");
           }
       }

       System.out.println("HP: " + hpAtual + "/" + HP_MAXIMO + " (" + porcentagem + "%)" + ", Estado: " + estado);
       System.out.println("Dano critico: " + calculoCritico);
       System.out.println("Buff " + buff + ": " + buffTimeMinutos + "min " + buffTimeSegundos + "s");
    }

    static int calcularPorcentagem(int hpAtual, int hpMaximo) {
        int porcentagem = (int) ((double) hpAtual / hpMaximo * 100);
        if (porcentagem == 0 && hpAtual > 0){
            return 1;
        }
        return porcentagem;
    }

    static String definirEstado(int porcentagem) {
        if (porcentagem >= 70) {
            return "Saudavel";
        } else if (porcentagem >= 30) {
            return "Ferido";
        } else if (porcentagem >= 1) {
            return "Critico";
        } else {
            return "Morto";
        }
    }
}
