import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import base.*;

class Main {
	public static int rng(int min, int max) {// gerar numeros aleatorios
		return ThreadLocalRandom.current().nextInt(min, max + 1);
	}

	public static base.Passageiro cadastrarVem(List<base.Parada> paradas) {
		String[] nomes = { "joao", "maria", "roberval", "jose", "lucas", "roberto", "ronaldo", "joana", "antonieta",
				"renata", "josefa", "antonio", "antonia", "carlos", "juliana", "julio", "beatriz", "clara", "mario",
				"miranda", "silva" };
		String[] tipos = { "comum", "comum", "comum", "comum", "comum", "comum", "comum", "comum", "comum", "idoso",
				"idoso", "estudante", "estudante", "estudante", "estudante" };
		base.Passageiro pessoa = new base.Passageiro(paradas.get(rng(0, paradas.size() - 1)),
				nomes[rng(0, nomes.length - 1)], tipos[rng(0, tipos.length - 1)]);
		pessoa.passe.recarga(rng(50, 800));
		System.out.println(pessoa.passe.nome + " " + pessoa.passe.tipo + " R$" + pessoa.passe.getSaldo());
		return pessoa;
	}

	public static void main(String[] args) {
		List<base.Parada> paradas = new ArrayList<>();
		List<base.Parada> todasParadas = new ArrayList<>();
		double taxafixa = 4.2;
		List<base.Passageiro> pessoas = new ArrayList<>();
		paradas.add(new base.Parada("beleleu"));
		paradas.add(new base.Parada("coqueiros"));
		paradas.add(new base.Parada("parada178903"));
		paradas.add(new base.Parada("ponto2"));
		base.Rota rota1 = new base.Rota("rota1", taxafixa, "cidade que nao existe", "ponto imaginario", "alguma coisa",
				"lugar inexistente", "bairro nunca mais", "morro 123");
		paradas.add(rota1.paradas.get(4));
		paradas.add(rota1.paradas.get(5));
		base.Rota rota2 = new base.Rota("rota2", taxafixa, paradas);
		for (int i = 0; i < 6; i++) {
			todasParadas.remove(rota1.paradas.get(i));
			todasParadas.add(rota1.paradas.get(i));
			todasParadas.remove(rota2.paradas.get(i));
			todasParadas.add(rota2.paradas.get(i));
		}

		for (int i = 0; i < rng(1000, 5000); i++)
			pessoas.add(cadastrarVem(todasParadas));
	}
}