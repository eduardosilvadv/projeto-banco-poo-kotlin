import Contas.ContaCorrente
import Contas.ContaPoupanca
import Funcionarios.Diretor
import Funcionarios.Gerente

fun main() {
    val usuarios = mutableListOf<Any>()
    var usuarioLogado: Any? = null

    fun limparTela() {
        print("\u001b[H\u001b[2J")
        System.out.flush()
    }

    val gerente = Gerente("Ricardo", 5000.0, "123456789", "Agência 001", "1").apply {
        cadastrarSenha("123")
    }

    val diretor = Diretor("Carlos", 8000.0, "789456123", "123").apply {
        cadastrarSenha("999")
    }

    val cliente = Cliente("Maria", "123", "00011122233")
    val contaCliente = ContaCorrente(cliente, "102", "456")
    val contaPoupanca = ContaPoupanca(cliente, "101", "456")

    usuarios.addAll(listOf(gerente, diretor, cliente, contaCliente, contaPoupanca))
    while (true) {
        limparTela()
        println("\n=== BANCO ===")

        if (usuarioLogado == null) {
            println("1. Login como Cliente")
            println("2. Login como Funcionário")
            println("0. Sair")
            print("Digite uma opção: ")
            when (readln()) {
                "1" -> {
                    limparTela()
                    println()
                    println("Olá, coloque seus dados abaixo!! ")
                    print("Digite a agência: ")
                    val agencia = readln()
                    print("Digite a conta: ")
                    val conta = readln()
                    print("Digite a senha: ")
                    val senha = readln()

                    val usuario = usuarios.find {
                        it is Conta && it.agencia == agencia && it.numeroConta == conta
                    } as? Conta

                    val cliente = usuario?.cliente

                    if (cliente != null && cliente.autenticar(senha)) {
                        usuarioLogado = cliente
                        println("✅ Login como ${cliente.nome} (Cliente)")
                        println("Saldo da conta ${usuario.numeroConta}: R$ ${usuario.saldo}")
                    } else {
                        println("❌ Cliente não encontrado ou senha inválida.")
                    }
                }
                "2" -> {
                    println()
                    print("Digite o CPF: ")
                    val cpf = readln()
                    print("Digite a senha: ")
                    val senha = readln()

                    val usuario = usuarios.find {
                        (it is Gerente && it.cpf == cpf) || (it is Diretor && it.cpf == cpf)
                    }

                    if (usuario is Funcionario) {
                        if (usuario.autenticar(senha)) {
                            usuarioLogado = usuario
                            println("✅ Login como ${usuario.nome} (${usuario::class.simpleName})")
                        } else {
                            println("❌ Falha na autenticação.")
                        }
                    } else {
                        println("❌ Funcionário não encontrado.")
                    }
                }
                "0" -> return
                else -> println("❌ Opção inválida.")
            }
        } else {
            when (usuarioLogado) {
                is Cliente -> {
                    println("Olá, ${usuarioLogado.nome}")
                    println()
                    println("1. Consultar saldo")
                    println("2. Sacar")
                    println("3. Depositar")
                    println("4. Transferir para outra conta")
                    println("5. Logout")
                    println("0. Sair")
                    print("Digite uma opção: ")
                    when (readln()) {
                        "1" -> {
                            val contasDoCliente = usuarios.filterIsInstance<Conta>().filter { it.cliente == usuarioLogado }
                            contasDoCliente.forEach {
                                println("Conta ${it.numeroConta} - Agência ${it.agencia} | Saldo: R$ ${it.saldo}")
                            }
                        }
                        "2" -> {
                            println("Digite o número da conta para saque:")
                            val numeroConta = readln()
                            val conta = usuarios.find { it is Conta && it.cliente == usuarioLogado && it.numeroConta == numeroConta } as? Conta
                            if (conta != null) {
                                print("Digite o valor para saque: R$ ")
                                val valor = readln().toDouble()
                                conta.sacar(valor)
                            } else {
                                println("❌ Conta não encontrada.")
                            }
                        }
                        "3" -> {
                            println("Digite o número da conta para depósito:")
                            val numeroConta = readln()
                            val conta = usuarios.find { it is Conta && it.cliente == usuarioLogado && it.numeroConta == numeroConta } as? Conta
                            if (conta != null) {
                                print("Digite o valor para depósito: R$ ")
                                val valor = readln().toDouble()
                                conta.depositar(valor)
                            } else {
                                println("❌ Conta não encontrada.")
                            }
                        }
                        "4" -> {
                            println("TRANSFERÊNCIA ENTRE CONTAS")
                            print("Sua conta de origem (número): ")
                            val origemNumero = readln()
                            print("Agência da conta de destino: ")
                            val destinoAgencia = readln()
                            print("Conta de destino (número): ")
                            val destinoNumero = readln()
                            print("Valor a transferir: R$ ")
                            val valor = readln().toDouble()

                            val contaOrigem = usuarios.find { it is Conta && it.cliente == usuarioLogado && it.numeroConta == origemNumero } as? Conta
                            val contaDestino = usuarios.find { it is Conta && it.agencia == destinoAgencia && it.numeroConta == destinoNumero } as? Conta

                            if (contaOrigem != null && contaDestino != null) {
                                contaOrigem.transferir(valor, contaDestino)
                            } else {
                                println("❌ Conta de origem ou destino não encontrada.")
                            }
                        }
                        "5" -> usuarioLogado = null.also { println("🔓 Logout realizado.") }
                        "0" -> return
                        else -> println("❌ Opção inválida.")
                    }
                }
                is Gerente -> {
                    println("1. Desbloquear cliente\n2. Logout\n0. Sair")
                    when (readln()) {
                        "1" -> {
                            println("=== Desbloquear cliente ===")
                            print("Agência do cliente: ")
                            val agenciaCliente = readln()
                            print("CPF do cliente: ")
                            val cpfCliente = readln()

                            val conta = usuarios.find {
                                it is Conta &&
                                        it.agencia == agenciaCliente &&
                                        it.cliente.cpf == cpfCliente
                            } as? Conta

                            val clienteAlvo = conta?.cliente

                            if (clienteAlvo != null && clienteAlvo.estaBloqueado()) {
                                print("Digite nova senha para o cliente: ")
                                val novaSenha = readln()
                                (usuarioLogado as Gerente).desbloquearCliente(clienteAlvo, novaSenha)
                            } else {
                                println("❌ Cliente não encontrado ou não está bloqueado.")
                            }
                        }
                        "2" -> usuarioLogado = null.also { println("🔓 Logout realizado.") }
                        "0" -> return
                        else -> println("❌ Opção inválida.")
                    }
                }
                else -> usuarioLogado = null
            }
        }
    }
}
