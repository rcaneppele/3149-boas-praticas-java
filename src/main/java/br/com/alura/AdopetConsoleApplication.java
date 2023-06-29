package br.com.alura;

import java.util.Scanner;

public class AdopetConsoleApplication {

    public static void main(String[] args) {
        CommandExecutor commandExecutor = new CommandExecutor();
        System.out.println("##### BOAS VINDAS AO SISTEMA ADOPET CONSOLE #####");

        int opcaoEscolhida = 0;
        while (opcaoEscolhida != 5) {
            exibirMenu();

            String textoDigitado = lerDoTeclado();
            opcaoEscolhida = Integer.parseInt(textoDigitado);

            switch (opcaoEscolhida) {
                case 1 -> commandExecutor.executeCommand(new ListarAbrigoCommand());
                case 2 -> commandExecutor.executeCommand(new CadastrarAbrigoCommand());
                case 3 -> commandExecutor.executeCommand(new ListarPetsDoAbrigoCommand());
                case 4 -> commandExecutor.executeCommand(new ImportarPetsDoAbrigoCommand());
                case 5 -> System.exit(0);
            }
        }
        System.out.println("Finalizando o programa...");
    }

    private static String lerDoTeclado() {
        return new Scanner(System.in).nextLine();
    }

    private static void exibirMenu() {
        System.out.println("\nDIGITE O NÚMERO DA OPERAÇÃO DESEJADA:");
        System.out.println("1 -> Listar abrigos cadastrados");
        System.out.println("2 -> Cadastrar novo abrigo");
        System.out.println("3 -> Listar pets do abrigo");
        System.out.println("4 -> Importar pets do abrigo");
        System.out.println("5 -> Sair");
    }
}
