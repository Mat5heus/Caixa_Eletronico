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
	
	// Calcula a idade da pessoa com base na data de nascimento
	public int idade () {
		Calendar cal = Calendar.getInstance();
		
		// soma dia e mes de forma que o dia sempre esteja nos ultímos 2 digitos
		int soma = cal.get(Calendar.DATE) + (cal.get(Calendar.MONTH) * 100);
		int total = this.nasc.getDia() + (this.nasc.getMes() * 100);
		
		// Faz a diferença entre os anos para achar a idade
		int ano = cal.get(Calendar.YEAR) - this.nasc.getAno();
		
		// Tira uma ano caso a pessoa não tenha feito aniversario
		if(soma < total)
			return ano - 1;
		
		return ano;
	}
	
	//-----------------------------//
	//----- Getters & Setters -----//
	//-----------------------------//
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
