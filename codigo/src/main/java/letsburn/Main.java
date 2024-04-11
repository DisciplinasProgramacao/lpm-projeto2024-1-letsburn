package letsburn;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    private static final Scanner entrada = new Scanner(System.in);
    private static boolean funcionamento = true;

    public static void main(String[] args) {
        Restaurante restaurante = new Restaurante();
        do {
            MenuPrincipal();
        } while (funcionamento);
    }

    private static void SelecaoMenuPrincipal() {
        switch (EntradaDoUsuario()) {
            case 0:
                funcionamento = false;
                System.out.println("Saindo do sistema...");
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            default:
                System.out.println("Essa opção não existe!");
                AdormeceSistema();
        }
    }

    private static void MenuPrincipal() {
        limpaTela();
        imprimeLogo();
        System.out.println("Restaurante Let's Burn - Menu Principal");
        System.out.println("1 - Atender Cliente");
        System.out.println("2 - Liberar CLiente");
        System.out.println("3 - Exibir Lista de Espera");
        System.out.println("0 - Sair");
        System.out.println();
        SelecaoMenuPrincipal();
    }

    private static int EntradaDoUsuario() {
        int opcao = -1;
        boolean entradaInvalida = false;
        do {
            entradaInvalida = false;
            try {
                System.out.print("Digite uma opção: ");
                opcao = Integer.parseInt(entrada.nextLine());
                if (opcao < 0) {
                    entradaInvalida = true;
                    System.out.println(
                            "\nA opção precisa ser um número maior ou igual a zero! - Digite novamente.");
                }
            } catch (Exception e) {
                System.out.println("\nA opção precisa ser um número inteiro! - Digite novamente.");
                entradaInvalida = true;
            }
        } while (entradaInvalida);
        limpaTela();
        return opcao;
    }

    private static void imprimeLogo() {
        System.out.println(" ___      _______  _______  __   _______    _______  __   __  ______    __    _ ");
        System.out.println("|   |    |       ||       ||  | |       |  |  _    ||  | |  ||    _ |  |  |  | |");
        System.out.println("|   |    |    ___||_     _||__| |  _____|  | |_|   ||  | |  ||   | ||  |   |_| |");
        System.out.println("|   |    |   |___   |   |       | |_____   |       ||  |_|  ||   |_||_ |       |");
        System.out.println("|   |___ |    ___|  |   |       |_____  |  |  _   | |       ||    __  ||  _    |");
        System.out.println("|       ||   |___   |   |        _____| |  | |_|   ||       ||   |  | || | |   |");
        System.out.println("|_______||_______|  |___|       |_______|  |_______||_______||___|  |_||_|  |__|");
        System.out.println();
    }

    private static void limpaTela() {
        try {
            String os = System.getProperty("os.name");
            if (os.contains("windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void AdormeceSistema(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void AdormeceSistema() {
        AdormeceSistema(2000L);
    }
}
