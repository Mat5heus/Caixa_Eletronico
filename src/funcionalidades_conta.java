
public interface funcionalidades_conta {
	
	public abstract void deposito(int valor);
	
	public abstract int sacar(int valor);
	
	public void transferencia(Conta conta, int valor);
	
	public void consulta();

	public abstract void informacoesGerais();
	
	public static void mensagens(Cliente cliente, int numero) {}
	
	public static void mensagens(Cliente cliente,Cliente clienteAlvo, int numero) {}
	
	public static void mensagens(Cliente cliente, int numero, int valor) {}
}
