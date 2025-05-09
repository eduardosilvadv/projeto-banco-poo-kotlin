package Contas

import Cliente
import Conta

class ContaPoupanca(
    cliente: Cliente,
    numero: String,
    agencia: String
) : Conta(cliente, numero, agencia ) {

    // Sobrescreve o método de depósito (simples)
    override fun depositar(valor: Double) {
        if (valor > 0) {
            saldo += valor
            println("Depósito de R$$valor realizado com sucesso na Conta Poupança.")
        } else {
            println("❌ Valor de depósito inválido.")
        }
    }
}
