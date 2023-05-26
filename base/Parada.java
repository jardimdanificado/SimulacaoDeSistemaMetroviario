package base;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import base.Passageiro;
import base.Metro;

public class Parada {
	private String nome;
	private List<base.Passageiro> passageiros = new ArrayList<>();
	protected base.Metro veiculo;
	public Parada(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return this.nome;
	}

	public void addPassageiro(base.Passageiro p) {
		this.passageiros.add(p);
	}

	public void rmPassageiro(base.Passageiro p) {
		this.passageiros.remove(p);
	}
}