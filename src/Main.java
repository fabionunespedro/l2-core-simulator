import java.util.Scanner;

public class Main {
    private static final int DURACAO_BUFF = 754;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Initialize project lineage 2 core project");
        String buff = "Haste";
        String personagem;
        char grade;
        int nivelMinimoGrade;
        String opcaoDeEntrada;

        Arma arma = new Arma("Arcana Mace", 45, 125, 1.5);

        do {
            System.out.println("Informe o nome do personagem!");
            personagem = scanner.nextLine().trim();
            if (!validarNome(personagem)) {
                System.out.println("Nome inválido!");
            }
        } while (!validarNome(personagem));

        Personagem personagem1 = new Personagem(personagem, 20, 500, 360, arma);

        do {
            menu();
            opcaoDeEntrada = scanner.nextLine();

            switch (opcaoDeEntrada) {


                case "1" -> {
                    int porcentagem = personagem1.calcularPorcentagem();
                    String estado = personagem1.definirEstado();
                    System.out.println("Personagem: " + personagem1.getNome() + " Nivel: " + personagem1.getNivel());
                    System.out.println("HP: " + personagem1.getHp() + "/" + personagem1.getHpMaximo() + " (" + porcentagem + "%)" + ", Estado: " + estado);
                    System.out.println("Dano critico: " + personagem1.getArmaEquipada().calcularCritico());
                    System.out.println("Buff " + buff + ": " + formatarDuracao(DURACAO_BUFF));
                }
                case "2" -> {

                    Monstro goblin = new Monstro("Goblin", 250);
                    Monstro orc = new Monstro("Orc", 180);
                    Monstro lobo = new Monstro("Lobo", 90);

                    Monstro[] monstros = {goblin, orc, lobo};

                    int contadorDerrotados = 0;
                    int limiteDeTurnos = 20;
                    int turnosDaCacada = 0;
                    if (personagem1.getArmaEquipada().getDano() <= 0) {
                        System.out.println("Dano inválido");
                    } else {
                        for (int i = 0; i < monstros.length && limiteDeTurnos > turnosDaCacada; i++) {
                            while (!monstros[i].estaMorto() && turnosDaCacada < limiteDeTurnos) {
                                turnosDaCacada++;
                                if (errouGolpe(turnosDaCacada)) {
                                    System.out.println("Turno " + turnosDaCacada + ": " + personagem1.getNome() + " errou o golpe!");
                                    continue;
                                }
                                personagem1.atacar(monstros[i]);
                                System.out.println("Turno " + turnosDaCacada + ": " + monstros[i].getNome() + " recebeu " + personagem1.getArmaEquipada().getDano() + " de dano (HP restante: " + monstros[i].getHp() + ")");
                            }
                            if (monstros[i].estaMorto()) {
                                contadorDerrotados++;
                                System.out.println(monstros[i].getNome() + " derrotado no turno " + turnosDaCacada);
                            } else {
                                System.out.println(monstros[i].getNome() + " fugiu!");
                            }

                        }
                        System.out.println(contadorDerrotados + "/" + monstros.length + " monstros derrotados em " + turnosDaCacada + " turnos!");
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
                        if (personagem1.getNivel() >= nivelMinimoGrade) {
                            System.out.println("Pode usar");
                        } else {
                            System.out.println("não pode usar");
                        }
                    } else {
                        System.out.println("Grau de equipamento inválido");
                    }
                }
                case "0" -> System.out.println("Até mais, " + personagem1.getNome() + "!");
                default -> System.out.println("Opção inválida.");
            }
        } while (!opcaoDeEntrada.equals("0"));
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

    static boolean errouGolpe(int turnoPorCombate) {
        return turnoPorCombate % 3 == 0;
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
