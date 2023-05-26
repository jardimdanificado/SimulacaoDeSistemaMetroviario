package base;

public class VEM {
	public String nome;
	public String tipo = "comum"; // comum, idoso, estudante
	private double saldo = 0.00;

	public VEM(String nome, String tipo) {
		this.nome = nome;
		this.tipo = tipo;
	}

	public void recarga(double valor) {
		this.saldo += valor;
	}

	public void debito(double valor) {
		this.saldo -= valor;
	}

	public double getSaldo() {
		return this.saldo;
	}
}