open class Conta(
    private val cliente: Cliente,  // Cliente associado à conta (privado)
    private val numeroConta: String,
    private  val agencia: String// Número da conta (privado)
) {
    private var saldo: Double = 0.0  // Saldo da conta (privado)

    // Método para depositar valor na conta
    open fun depositar(valor: Double) {
        if (valor > 0) {
            saldo += valor
            println("Depósito de R$$valor realizado com sucesso!")
        } else {
            println("❌ Valor de depósito inválido.")
        }
    }

    // Método para sacar valor da conta (genérico)
    open fun sacar(valor: Double): Double {
        if (valor <= saldo) {
            saldo -= valor
            println("Saque de R$$valor realizado com sucesso!")
            return valor
        } else {
            println("❌ Saldo insuficiente para o saque de R$$valor.")
            return 0.0
        }
    }

    // Método para consultar saldo da conta
    fun consultarSaldo(): Double {
        return saldo
    }

    // Método para transferir valor para outra conta
    fun transferir(valor: Double, contaDestino: Conta): Boolean {
        if (this.sacar(valor) > 0) {
            contaDestino.depositar(valor)
            println("Transferência de R$$valor realizada com sucesso para a conta ${contaDestino.getNumeroConta()}.")
            return true
        }
        return false
    }

    // Método para adicionar saldo (usado nas classes filhas, como no caso de Poupança)
    protected fun adicionarSaldo(valor: Double) {
        saldo += valor
    }

    // Getter para acessar o número da conta (privado)
    fun getSaldo(): Double {
        return saldo
    }

    // Getter para acessar o cliente associado à conta (privado)
    fun getCliente(): Cliente {
        return cliente
    }
    fun getNumeroConta(): String{
        return numeroConta
    }

    // Método protegido para efetuar saque (chamado pelas subclasses)
    protected fun efetuarSaque(valor: Double): Double {
        if (valor <= saldo) {
            saldo -= valor
            return valor
        } else {
            println("❌ Saldo insuficiente para o saque.")
            return 0.0
        }
    }
    fun getAgencia(): String = agencia
}
