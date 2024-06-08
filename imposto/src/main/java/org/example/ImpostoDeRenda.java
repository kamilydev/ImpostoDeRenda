package org.example;

public class ImpostoDeRenda implements FaixaImpostoDeRenda {
    private Pessoa pessoa;

    public ImpostoDeRenda(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    @Override
    public int getFaixa(double salario) {
        if (salario <= 2112) {
            return 1;
        } else if (salario <= 2826.66) {
            return 2;
        } else if (salario <= 3751.06) {
            return 3;
        } else if (salario <= 4664.68) {
            return 4;
        } else {
            return 5;
        }
    }

    public double calcular() {
        double salario = pessoa.salario;
        double imposto = 0.0;

        if (salario > 4664.68) {
            imposto += (salario - 4664.68) * 0.275;
            salario = 4664.68;
        }
        if (salario > 3751.06) {
            imposto += (salario - 3751.06) * 0.225;
            salario = 3751.06;
        }
        if (salario > 2826.66) {
            imposto += (salario - 2826.66) * 0.15;
            salario = 2826.66;
        }
        if (salario > 2112.01) {
            imposto += (salario - 2112.01) * 0.075;
        }

        return imposto;
    }

    public void imprimir() {
        int faixa = getFaixa(pessoa.salario);
        double imposto = calcular();
        System.out.printf("%s, seu salário é R$ %.2f, sua faixa de imposto é %d e o valor do imposto de renda a ser pago é: R$ %.2f%n",
                pessoa.nome, pessoa.salario, faixa, imposto);
    }
}