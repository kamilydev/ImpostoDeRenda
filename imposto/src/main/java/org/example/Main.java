package org.example;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Map<String, Object>> listaPessoas = new ArrayList<>();

        while (true) {
            System.out.println("\n### CÁLCULO DE IMPOSTO DE RENDA ###");
            System.out.println("_____________________________________________________________________________________________________");
            System.out.println("## O que deseja executar? ##");
            System.out.println("Opção 1 - Cálculo de imposto");
            System.out.println("Opção 2 - Consultar pessoas registradas por faixa de alíquota");
            System.out.println("Opção 0 - Encerrar programa");
            System.out.println("_____________________________________________________________________________________________________");

            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            if (opcao == 1) {
                System.out.print("Quantas pessoas você deseja realizar o cálculo de imposto de renda? ");
                int numPessoas = scanner.nextInt();
                scanner.nextLine();

                for (int i = 0; i < numPessoas; i++) {
                    System.out.println("Pessoa número " + (i + 1));

                    System.out.print("Digite o nome: ");
                    String nome = scanner.nextLine();

                    System.out.print("Digite o salário: ");
                    double salario = scanner.nextDouble();
                    scanner.nextLine();

                    System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

                    Map<String, Object> dadosPessoa = new HashMap<>();
                    dadosPessoa.put("nome", nome);
                    dadosPessoa.put("salario", salario);
                    listaPessoas.add(dadosPessoa);

                    Pessoa pessoa = new Pessoa(nome, salario);
                    ImpostoDeRenda impostoDeRenda = new ImpostoDeRenda(pessoa);

                    impostoDeRenda.imprimir();
                }
            } else if (opcao == 2) {
                if (listaPessoas.isEmpty()) {
                    System.out.println("Nenhuma pessoa registrada. Por favor, registre pelo menos uma pessoa.");
                } else {
                    System.out.println("Digite a alíquota: ");
                    System.out.println("1: Até R$2.112 isento");
                    System.out.println("2: De 2.112,01 até 2.826,66      7,5%");
                    System.out.println("3: De 2.826,67 até 3.751,06      15%");
                    System.out.println("4: De 3.751,07 até 4.664,68      22,5%");
                    System.out.println("5: Acima de R$4.664,68           27,5%");
                    int aliquota = scanner.nextInt();
                    scanner.nextLine();

                    buscarPessoasPorAliquota(listaPessoas, aliquota);
                }
            } else if (opcao == 0) {
                System.out.println("Programa encerrado.");
                break;
            } else {
                System.out.println("Opção inválida. Tente novamente.");
            }
        }

        scanner.close();
    }

    public static void buscarPessoasPorAliquota(List<Map<String, Object>> listaPessoas, int aliquota) {
        boolean encontrou = false;
        for (Map<String, Object> dadosPessoa : listaPessoas) {
            String nome = (String) dadosPessoa.get("nome");
            double salario = (double) dadosPessoa.get("salario");

            Pessoa pessoa = new Pessoa(nome, salario);
            ImpostoDeRenda impostoDeRenda = new ImpostoDeRenda(pessoa);

            int faixa = impostoDeRenda.getFaixa(salario);
            if (faixa == aliquota) {
                String faixaTexto = switch (faixa) {
                    case 1 -> "isento";
                    case 2 -> "7,5%";
                    case 3 -> "15%";
                    case 4 -> "22,5%";
                    case 5 -> "27,5%";
                    default -> "";
                };
                System.out.printf("%s está na alíquota %s com salário R$ %.2f%n", nome, faixaTexto, salario);
                encontrou = true;
            }
        }
        if (!encontrou) {
            System.out.println("Nenhuma pessoa encontrada na alíquota especificada.");
        }
    }
}