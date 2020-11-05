public class Conta implements funcionalidades_conta {
	private int saldo, cheque_especial, codigo;
	private boolean aberta;
	private Cliente cliente;
	private Cartao cartao;
	private Data data;
	
	public Conta(Cliente cliente, int codigo, String bandeira, int senha, Data data) {
		this.setCliente(cliente);
		this.setCodigo(codigo);
		this.setCartao(new Cartao(codigo, bandeira, senha));
		this.setSaldo(0);
		this.setCheque_especial(300);
		this.setData(data);
		this.setAberta(false);
	}
	
	public void deposito(int valor) {
		if(this.aberta) {
			if(this.getCheque_especial() + valor <= 300) {
				this.setCheque_especial(this.getCheque_especial() + valor);
			} else if (this.getCheque_especial() <= 300) {
				valor -= 300 - this.getCheque_especial();
				if(valor > 0) {
					this.setCheque_especial(300);
					this.setSaldo(this.getSaldo() + valor);
				}
			} else 
				this.setSaldo(this.getSaldo() + valor);
			
			Conta.mensagens(this.getCliente(), 3);
		} else {
			Conta.mensagens(this.cliente, 9);
			Conta.mensagens(this.cliente, 7);
		}
	}
	
	public static void mensagens(Cliente cliente, int numero, int valor) {
		System.out.print("Conta de "+cliente.getNome()+": ");
		switch(numero) {
		case 1:
			System.out.println("Saldo "+valor);
			break;
		default:
			System.out.println("Mensagem não encontrada!");
		}
	}

	public static void mensagens(Cliente cliente, int numero) {
		System.out.print("Conta de "+cliente.getNome()+": ");
		switch(numero) {
		case 1:
			System.out.println("Saldo insuficiente");
			break;
		case 2:
			System.out.println("Transferencia cancelada!");
			break;
		case 3:
			System.out.println("Deposito realizado!");
			break;
		case 4:
			System.out.println("Saque cancelado!");
			break;
		case 5:
			System.out.println("Senha incorreta");
			break;
		case 6:
			System.out.println("Cartão bloqueado!");
			break;
		case 7:
			System.out.println("Operação cancelada");
			break;
		case 8:
			System.out.println("Saque realizado!");
			break;
		case 9:
			System.out.println("Conta não ativa!");
			break;
		default:
			System.out.println("Código de mensagem invalido!");
		}
	}
	
	public int sacar(int valor) {
		if(this.aberta) {
			int aux = valor;
			if(this.getSaldo() + this.getCheque_especial() >= valor) {
				valor = valor - this.getSaldo();
				if(valor > 0) {
					this.setSaldo(0);
					this.setCheque_especial(this.getCheque_especial() - valor);
				} else 
					this.setSaldo(this.getSaldo() - aux);
				Conta.mensagens(this.getCliente(), 8);
				return aux;
			} else {
				Conta.mensagens(this.getCliente(),1);
				return 0;
			}
		} else {
			Conta.mensagens(this.cliente, 9);
			Conta.mensagens(this.cliente, 7);
		}
		return 0;
		
	}
	
	public void transferencia(Conta conta, int valor) {
		if(this.aberta) {
			if (this.sacar(valor) != 0)
				conta.deposito(valor);
			else
				Conta.mensagens(this.getCliente(),2);
		} else {
			Conta.mensagens(this.cliente, 9);
			Conta.mensagens(this.cliente, 7);
		}
	}
	
	public void consulta() {
		if(this.aberta) 
			Conta.mensagens(this.getCliente(), 1, this.getSaldo());
		else {
			Conta.mensagens(this.cliente, 9);
			Conta.mensagens(this.cliente, 7);
		}
	}

	public void informacoesGerais() {
		int valor;
		String saldo_devedor = "";
		
		if(this.cheque_especial < 300) { 
			valor = 300 - this.getCheque_especial();
		    saldo_devedor = "(devendo "+valor+")";
		}
		
		System.out.println(
				"========= Informações ===========\n"+
				"Cliente: "+this.getCliente().getNome()+"\n"+
				"Idade do cliente: "+this.getCliente().idade()+"\n"+
				"Conta:"+this.getCodigo()+"\n"+
				"Data de criação: "+this.getData().dataSimples()+"\n"+
				"Status da conta: "+(this.isAberta() ? "Ativa" : "Desativada")+"\n"+
				"Saldo: "+this.getSaldo()+"\n"+
				"Cheque Especial: "+this.getCheque_especial()+" "+saldo_devedor
				);
	}
	
    //-----------------------------//
	//----- Getters & Setters -----//
	//-----------------------------//
	public int getSaldo() {
		return saldo;
	}

	public void setSaldo(int saldo) {
		this.saldo = saldo;
	}

	public int getCheque_especial() {
		return cheque_especial;
	}

	public void setCheque_especial(int cheque_especial) {
		this.cheque_especial = cheque_especial;
	}

	public boolean isAberta() {
		return aberta;
	}

	public void setAberta(boolean aberta) {
		this.aberta = aberta;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Cartao getCartao() {
		return cartao;
	}

	public void setCartao(Cartao cartao) {
		this.cartao = cartao;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	};
}
