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
        get() = salario * 2.0

    override fun autenticar(senha: String): Boolean {
        return senha == senhaAcesso
    }

    fun bonificar(comissao: Double) {
        println("Diretor ${nome} recebeu bonificação de comissão: R$$comissao")
    }

    fun adicionarGerente(gerente: Gerente) {
        listaGerentes.add(gerente)
        println("Gerente ${gerente.nome} adicionado à lista do diretor ${nome}.")
    }

    fun exibirListaGerentes() {
        if (listaGerentes.isEmpty()) {
            println("O diretor ${nome} não tem gerentes sob sua responsabilidade.")
        } else {
            println("Gerentes sob responsabilidade de ${nome}:")
            for (gerente in listaGerentes) {
                println("Nome: ${gerente.nome}, Salário: R$${gerente.salario}, Bonificação: R$${gerente.bonificacao}")
            }
        }
    }

    fun aumentarSalario(funcionario: Funcionario, aumento: Double) {
        if (aumento > 0) {
            println("Aumento de R$$aumento proposto para ${funcionario.nome}.")
            // Aqui só estamos simulando — o atributo salário permanece imutável
        } else {
            println("Aumento inválido! Valor precisa ser positivo.")
        }
    }

    fun alterarNomeFuncionario(funcionario: Funcionario, novoNome: String) {
        println("Nome de ${funcionario.nome} alterado para $novoNome (simulado).")
        // Se tiver método em Funcionario para isso, use aqui
    }
}
