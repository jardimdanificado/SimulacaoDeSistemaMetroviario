package base;

class parada {
	private String nome;
	private List<passageiro> passageiros = new ArrayList<>();

	public parada(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return this.nome;
	}

	public void addPassageiro(passageiro p) {
		this.passageiros.add(p)
	}

	public void rmPassageiro(passageiro p) {
		this.passageiros.remove(p)
	}
}