import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import base.*;

class Main {
	public static int rng(int min, int max) {//gerar numeros aleatorios
		return ThreadLocalRandom.current().nextInt(min, max + 1);
	}

	public static void main(String[] args) {
		for(int i = 0; i < 40; i++ )//teste do rng
			System.out.println(rng(0,90));
		double taxafixa = 4.2;
		List<Parada> paradas = new ArrayList<>();
		paradas.add(new Parada("beleleu"));
		paradas.add(new Parada("coqueiros"));
		paradas.add(new Parada("parada178903"));
		paradas.add(new Parada("ponto2"));
		Rota rota1 = new Rota("rota1",taxafixa,"cidade que nao existe","ponto imaginario","alguma coisa","lugar inexistente","bairro nunca mais","morro 123");
		Rota rota2 = new Rota("rota2",taxafixa,paradas;
	}
}