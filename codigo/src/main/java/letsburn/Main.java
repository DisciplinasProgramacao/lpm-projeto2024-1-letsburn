package letsburn;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner entrada = new Scanner(System.in);
    private static boolean funcionamento = true;
    private static Restaurante restauranteLetsBurn = null;
    private static List<Cliente> listClientes = new ArrayList<>();

    public static void main(String[] args) {
        List<Mesa> mesas = new ArrayList<>();
        mesas.addAll(criaMesas(4, 4));
        mesas.addAll(criaMesas(4, 6));
        mesas.addAll(criaMesas(2, 8));
        restauranteLetsBurn = new Restaurante(mesas);

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
                System.out.println("Qual o nome do Cliente?");
                String nome = entrada.nextLine();
                salvaCliente(new Cliente(nome));

                System.out.println("Quantas pessoas são?");
                int qtdPessoas = Integer.parseInt(entrada.nextLine());

                restauranteLetsBurn.gerarRequisicao(listClientes.get(listClientes.size() - 1), qtdPessoas);
                break;
            case 2:
                System.out.println("Qual o nome do Cliente?");
                final String finalNome = entrada.nextLine();
                listClientes.stream().filter(c -> c.getNome().equals(finalNome)).findFirst().ifPresent(restauranteLetsBurn::liberarRequisicao);
                break;
            case 3:
                restauranteLetsBurn.exibirListaEspera();
                break;
            default:
                System.out.println("Essa opção não existe!");
                AdormeceSistema();
        }
    }

    private static void MenuPrincipal() {
        StringBuilder menu = new StringBuilder();
        limpaTela();
        imprimeLogo();
        menu.append("Restaurante Let's Burn - Menu Principal\n");
        menu.append("1 - Atender Cliente\n");
        menu.append("2 - Liberar Cliente\n");
        menu.append("3 - Exibir Lista de Espera\n");
        menu.append("0 - Sair\n\n");
        System.out.println(menu);
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

    //O restaurante tem:
    //	10 mesas
    //		4 - 4 pessoas
    //		4 - 6 pessoas
    //		2 - 8 pessoas
    private static List<Mesa> criaMesas(int qtdMesas, int pessoas){
        List<Mesa> mesas = new ArrayList<>();
        for (int i = 0; i < qtdMesas; i++) {
            mesas.add(new Mesa(pessoas));
        }
        return mesas;
    }

    private static void salvaCliente(Cliente cliente){
        listClientes.add(cliente);
    }


}
