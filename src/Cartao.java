import java.util.Scanner;

public class Cartao {
	private int codigo, senha;
	private String bandeira;
	private boolean estaBloqueado;
	
	public Cartao (int codigo, String bandeira, int senha) {
		this.setCodigo(codigo);
		this.setBandeira(bandeira);
		this.setSenha(senha);
		this.setEstaBloqueado(false);
	}
	
	public boolean validar_senha(Cliente cliente) {
		Scanner scan = new Scanner(System.in);
		int senha;
		
		// Verifica se o cartão do usuario já esta bloqueado
		if(this.estaBloqueado) {
			Conta.mensagens(cliente, 6);
			Conta.mensagens(cliente, 7);
			return false;
		}
		
		// Da direito a 3 tentativas antes de bloquear o cartão
		for(int ctd = 1; ctd <= 3; ctd++) {
			if(ctd > 1) System.out.print(ctd+" tentativa...\n");
			System.out.print("Digite a senha do cartão: ");
			senha = Integer.parseInt(scan.nextLine());
			if(senha == this.getSenha())
				return true;
			else
				Conta.mensagens(cliente, 5);
		}
		
		// Avisa o usuario e bloqueia o cartão
		Conta.mensagens(cliente, 6);
		this.setEstaBloqueado(true);
		return false;
	}
	
	//-----------------------------//
	//----- Getters & Setters -----//
	//-----------------------------//
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getBandeira() {
		return bandeira;
	}
	public void setBandeira(String bandeira) {
		this.bandeira = bandeira;
	}

	private int getSenha() {
		return senha;
	}

	public void setSenha(int senha) {
		this.senha = senha;
	}

	public boolean isEstaBloqueado() {
		return estaBloqueado;
	}

	public void setEstaBloqueado(boolean estaBloqueado) {
		this.estaBloqueado = estaBloqueado;
	}
}
