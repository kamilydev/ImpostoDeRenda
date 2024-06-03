package org.example;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite o nome: ");
        String nome = scanner.nextLine();

        System.out.print("Digite o salário: ");
        double salario = scanner.nextDouble();

        scanner.close();

        Pessoa pessoa = new Pessoa(nome, salario);
        ImpostoDeRenda impostoDeRenda = new ImpostoDeRenda(pessoa);

        impostoDeRenda.imprimir();
    }
}