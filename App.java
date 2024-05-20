package letsburn;

import letsburn.model.Cliente;
import letsburn.model.Mesa;
import letsburn.model.Restaurante;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class App {

    private static final Scanner entrada = new Scanner(System.in);
    private static boolean funcionamento = true;
    private static Restaurante restauranteLetsBurn = null;
    private static List<Cliente> listClientes = new ArrayList<>();

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public static CommandLineRunner initializer() {
        return args -> {
            rotinaDeInicializacao();
            do {
                MenuPrincipal();
            } while (funcionamento);
        };
    }

    private static void SelecaoMenuPrincipal() {
        switch (EntradaDoUsuario()) {
            case 0 -> rotinaDeSaida();
            case 1 -> rotinaDeAtenderRequisicao();
            case 2 -> rotinaDeLiberarRequisicao();
            case 3 -> rotinaDeExibirListaDeEspera();
            default-> rotinaOpcaoInexistente();
        }
    }


    private static void rotinaDeInicializacao(){
                List<Mesa> mesas = new ArrayList<>();
                mesas.addAll(criaMesas(4, 4));
                mesas.addAll(criaMesas(4, 6));
                mesas.addAll(criaMesas(2, 8));
                restauranteLetsBurn = new Restaurante(mesas);
    }

    private static void rotinaDeSaida(){
            funcionamento = false;
            System.out.println("Saindo do sistema...");
    }

    private static void rotinaDeAtenderRequisicao(){
            System.out.println("Qual o nome do Cliente?");
            String nome = entrada.nextLine();
            salvaCliente(new Cliente(nome));
            System.out.println("Quantas pessoas são?");
            int qtdPessoas = Integer.parseInt(entrada.nextLine());

            restauranteLetsBurn.gerarRequisicao(listClientes.get(listClientes.size() - 1), qtdPessoas);
    }

    private static void rotinaDeLiberarRequisicao(){
            System.out.println("Qual o nome do Cliente?");
            final String finalNome = entrada.nextLine();
            listClientes.stream().filter(c -> c.getNome().equals(finalNome)).findFirst().ifPresent(restauranteLetsBurn::liberarRequisicao);
    }

    private static void rotinaDeExibirListaDeEspera(){
            restauranteLetsBurn.exibirListaEspera();
    }

    private static void rotinaOpcaoInexistente(){
            System.out.println("Essa opção não existe!");
            AdormeceSistema();
    }


    private static void MenuPrincipal() {
        StringBuilder menu = new StringBuilder();
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
