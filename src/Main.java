import java.util.Scanner;

public class Main {
    private static final double MULTIPLICADOR_DE_CRITICO = 1.5;
    private static final int HP_MAXIMO = 500;
    private static final int DURACAO_BUFF = 754;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Initialize project lineage 2 core project");
        String buff = "Haste";
        String personagem;
        char grade;
        int nivel = 20;
        int hpAtual = 360;
        int danoBase = 125;
        int dano = 45;
        int porcentagem = calcularPorcentagem(hpAtual, HP_MAXIMO);
        String estado = definirEstado(porcentagem);
        int calculoCritico = calcularCritico(danoBase);
        int nivelMinimoGrade;
        String[] nomesMonstro = {"Goblin", "Orc", "Lobo"};
        String opcaoDeEntrada;

        do {
            System.out.println("Informe o nome do personagem!");
            personagem = scanner.nextLine().trim();
            if (!validarNome(personagem)) {
                System.out.println("Nome inválido!");
            }
        }while (!validarNome(personagem));

        do {
            menu();
            opcaoDeEntrada = scanner.nextLine();

            switch (opcaoDeEntrada) {
                case "1" -> {
                    System.out.println("Personagem: " + personagem + " Nivel: " + nivel);
                    System.out.println("HP: " + hpAtual + "/" + HP_MAXIMO + " (" + porcentagem + "%)" + ", Estado: " + estado);
                    System.out.println("Dano critico: " + calculoCritico);
                    System.out.println("Buff " + buff + ": " + formatarDuracao(DURACAO_BUFF));
                }
                case "2" -> {
                    int contadorDerrotados = 0;
                    int limiteDeTurnos = 20;
                    int turnosDaCacada = 0;
                    int[] hpsMonstro = {250, 180, 90};
                    if (dano <= 0) {
                        System.out.println("Dano inválido");
                    } else {
                        for (int i = 0; i < nomesMonstro.length && limiteDeTurnos > turnosDaCacada; i++) {
                            while (!estaMorto(hpsMonstro[i]) && turnosDaCacada < limiteDeTurnos) {
                                turnosDaCacada++;
                                if (errouGolpe(turnosDaCacada)) {
                                    System.out.println("Turno " + turnosDaCacada + ": " + personagem + " errou o golpe!");
                                    continue;
                                }
                                hpsMonstro[i] = aplicarDano(hpsMonstro[i], dano);
                                System.out.println("Turno " + turnosDaCacada + ": " + nomesMonstro[i] + " recebeu " + dano + " de dano (HP restante: " + hpsMonstro[i] + ")");
                            }
                            if (estaMorto(hpsMonstro[i])) {
                                contadorDerrotados++;
                                System.out.println(nomesMonstro[i] + " derrotado no turno " + turnosDaCacada);
                            } else {
                                System.out.println(nomesMonstro[i] + " fugiu!");
                            }

                        }
                        System.out.println(contadorDerrotados + "/" + hpsMonstro.length + " monstros derrotados em " + turnosDaCacada + " turnos!");
                    }
                }
                case "3" -> {
                    System.out.println("Informe a grade!");
                    String gradeString = scanner.nextLine().toUpperCase().trim();

                    if (gradeString.isEmpty()) {
                        System.out.println("Grade inválida!");
                        continue;
                    } else {
                        grade = gradeString.charAt(0);
                    }
                    nivelMinimoGrade = obterNivelMinimo(grade);
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
                }
                case "0" -> System.out.println("Até mais, " + personagem + "!");
                default -> System.out.println("Opção inválida.");
            }
        } while (!opcaoDeEntrada.equals("0"));
    }

    static int calcularPorcentagem(int hpAtual, int hpMaximo) {
        int porcentagem = (int) ((double) hpAtual / hpMaximo * 100);
        if (porcentagem == 0 && hpAtual > 0) {
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

    static int calcularCritico(int danoBase) {
        return (int) (MULTIPLICADOR_DE_CRITICO * danoBase);
    }

    static int obterNivelMinimo(char grade) {
        return switch (grade) {
            case 'D' -> 20;
            case 'C' -> 40;
            case 'B' -> 52;
            case 'A' -> 61;
            case 'S' -> 76;
            default -> 0;
        };
    }

    static String formatarDuracao(int time) {
        int buffTimeMinutos = time / 60;
        int buffTimeSegundos = time % 60;
        return buffTimeMinutos + "min " + buffTimeSegundos + "s";
    }

    static int aplicarDano(int hp, int dano) {
        int hpRestante = hp - dano;
        return hpRestante < 0 ? 0 : hpRestante;
    }

    static boolean errouGolpe(int turnoPorCombate) {
        return turnoPorCombate % 3 == 0;
    }

    static boolean estaMorto(int hpMonstro) {
        return hpMonstro <= 0;
    }

    static boolean validarNome(String nome) {
        return nome.length() > 2 && nome.length() < 17;
    }

    static void menu() {
        System.out.println("Informe opção correto de entrada!");
        System.out.println("""
                1 - Ver Status
                2 - Caçar
                3 - Verificar equipamento
                0 - Sair
                """);
    }
}
