package Funcionarios

import Autenticacao.Autenticavel
import Funcionario


class Diretor(
    nome: String,
    salario: Double,
    cpf: String,
    senha: String,
) : Funcionario(nome, salario, cpf, senha), Autenticavel {

    private val senhaAcesso = senha
    private val listaGerentes = mutableListOf<Gerente>()

    // Bonificação: 100% do salário
    override val bonificacao: Double
        get() = getSalario() * 2.0

    override fun autenticar(senha: String): Boolean {
        return senha == senhaAcesso
    }

    fun bonificar(comissao: Double) {
        println("Diretor ${getNome()} recebeu bonificação de comissão: R$$comissao")
    }

    fun adicionarGerente(gerente: Gerente) {
        listaGerentes.add(gerente)
        println("Gerente ${gerente.getNome()} adicionado à lista do diretor ${getNome()}.")
    }

    fun exibirListaGerentes() {
        if (listaGerentes.isEmpty()) {
            println("O diretor ${getNome()} não tem gerentes sob sua responsabilidade.")
        } else {
            println("Gerentes sob responsabilidade de ${getNome()}:")
            for (gerente in listaGerentes) {
                println("Nome: ${gerente.getNome()}, Salário: R$${gerente.getSalario()}, Bonificação: R$${gerente.bonificacao}")
            }
        }
    }

    fun aumentarSalario(funcionario: Funcionario, aumento: Double) {
        if (aumento > 0) {
            println("Aumento de R$$aumento proposto para ${funcionario.getNome()}.")
            // Aqui só estamos simulando — o atributo salário permanece imutável
        } else {
            println("Aumento inválido! Valor precisa ser positivo.")
        }
    }

    fun alterarNomeFuncionario(funcionario: Funcionario, novoNome: String) {
        println("Nome de ${funcionario.getNome()} alterado para $novoNome (simulado).")
        // Se tiver método em Funcionario para isso, use aqui
    }
}
