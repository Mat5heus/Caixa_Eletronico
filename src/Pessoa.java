import java.util.Calendar;

public abstract class Pessoa {
	private String nome;
	private int cpf, rg;
	private Data nasc;
	
	public Pessoa(String nome, int cpf, int rg, Data nasc) {
		this.setNome(nome);
		this.setRg(rg);
		this.setCpf(cpf);
		this.setNasc(nasc);
	}
	
	public int idade () {
		Calendar cal = Calendar.getInstance();
		
		int soma = cal.get(Calendar.DATE) + (cal.get(Calendar.MONTH) * 100);
		int total = this.nasc.getDia() + (this.nasc.getMes() * 100);
		int ano = cal.get(Calendar.YEAR) - this.nasc.getAno();
		
		if(soma < total)
			return ano - 1;
		
		return ano;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getRg() {
		return rg;
	}
	public void setRg(int rg) {
		this.rg = rg;
	}
	public int getCpf() {
		return cpf;
	}
	public void setCpf(int cpf) {
		this.cpf = cpf;
	}

	public Data getNasc() {
		return nasc;
	}

	public void setNasc(Data nasc) {
		this.nasc = nasc;
	}
	
}
