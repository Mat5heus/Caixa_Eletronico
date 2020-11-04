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
		
		if(this.estaBloqueado) {
			Conta.mensagens(cliente, 6);
			Conta.mensagens(cliente, 7);
			return false;
		}
			
		for(int ctd = 0; ctd < 3; ctd++) {
			System.out.print("Digite a senha do cartão: ");
			senha = Integer.parseInt(scan.nextLine());
			if(senha == this.senha)
				return true;
			else
				Conta.mensagens(cliente, 5);
		}
		
		scan.close();
		
		Conta.mensagens(cliente, 6);
		this.setEstaBloqueado(true);
		return false;
	}
	
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

	public int getSenha() {
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
