package base;

class VEM {
	private String nome;
	private String tipo = "comum"; // comum, idoso, estudante
	private double saldo = 0.00;
	public VEM(String nome, String tipo)
	{
		this.nome = nome;
		this.tipo = tipo;
	}
	protected void recarga(double valor)
	{
		this.saldo += valor;
	}
	protected void debito(double valor)
	{
		this.saldo -= valor;
	}
	protected double getSaldo()
	{
		return this.saldo;
	}
}