package Front;

import java.util.ArrayList;

import Entidades.Critico;
import Entidades.D10;
import Entidades.D20;
import Entidades.D8;
import Entidades.Dado;
import Entidades.DadoCustomizado;
import Entidades.DadoCustomizadoAtaque;
import Entidades.DadoCustomizadoTeste;
import Entidades.Dano;
import Entidades.Modificador;
import Entidades.PastaDados;

public class Principal {
	public static void main(String[] args) {
		
		// Teste de rolagem de teste

		D20 teste1 = new D20(2);

		ArrayList<Modificador> modificadores = new ArrayList<Modificador>();

		Modificador mod = new Modificador("Treinado", 5);
		modificadores.add(mod);
		mod = new Modificador("Vestimenta", 5);
		modificadores.add(mod);

		DadoCustomizadoTeste teste2 = new DadoCustomizadoTeste("Rolagem Teste", teste1, modificadores);

		teste2.rolarDado();
		System.out.println(teste2.getNome());
		System.out.println(teste2.getTipo());
		System.out.println(teste2.getTeste().getRolagens());
		System.out.println(teste2.getResultado().getValorEscolhido());
		for(int i = 0; i < teste2.getModificadores().size(); i++){
			System.out.println(teste2.getModificadores().get(i).getNome() + " +" + teste2.getModificadores().get(i).getValor());
		}
		System.out.println("Teste: " + teste2.obterValor());
		
		
		
		// Teste de rolagem de ataque
		
		
		
		D20 testATK = new D20(3);

		ArrayList<Modificador> mods = new ArrayList<Modificador>();

		Modificador modif = new Modificador("Ataque Especial", 10);
		mods.add(modif);
		modif = new Modificador("Treinado", 5);
		mods.add(modif);

		Critico critico = new Critico(19, 3);
		
		D10 dadoNovo = new D10(2);
		ArrayList<Dado> dadoDano = new ArrayList<Dado>();
		dadoDano.add(dadoNovo);
		D8 dadoNovo2 = new D8(2);
		dadoDano.add(dadoNovo2);
		
		Modificador modder = new Modificador("Ataque Especial", 10);
		ArrayList<Modificador> modificas = new ArrayList<Modificador>();
		modificas.add(modder);
		
		Dano dano = new Dano(dadoDano, modificas, critico);
		
		
		DadoCustomizadoAtaque testeATK = new DadoCustomizadoAtaque("Rolagem Ataque", testATK, mods, critico, dano);

		System.out.println("\n*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");
		
		testeATK.rolarDado();
		int danoTotal = 0;
		System.out.println(testeATK.getNome());
		System.out.println(testeATK.getTipo());
		System.out.println(testeATK.getTeste().getRolagens());
		System.out.println(testeATK.getResultado().getValorEscolhido());
		for(int i = 0; i < testeATK.getModificadores().size(); i++){
			System.out.println(testeATK.getModificadores().get(i).getNome() + " +" + testeATK.getModificadores().get(i).getValor());
		}
		System.out.println("Teste: " + testeATK.obterValor());
		System.out.println(testeATK.getCritico().getMargem() + " / x" + testeATK.getCritico().getMultiplicador());
		if(testeATK.getResultado().verificarCritico()) {
			System.out.println("Crítico!");
			testeATK.getDano().rolarDanoCritico();
			System.out.println();
			for(int i = 0; i < testeATK.getDano().getDados().size(); i++) {
				if(i == (testeATK.getDano().getDados().size() - 1)) {
					System.out.print((testeATK.getDano().getDados().get(i).getQuantidade() * testeATK.getCritico().getMultiplicador()) + "D" + testeATK.getDano().getDados().get(i).getFaces() + "\n");	
				} else {
					System.out.print((testeATK.getDano().getDados().get(i).getQuantidade() * testeATK.getCritico().getMultiplicador()) + "D" + testeATK.getDano().getDados().get(i).getFaces() + " + ");
				}
			}
			for(int i = 0; i < testeATK.getDano().getRolagemDano().size(); i++) {
				for(int j = 0; j < testeATK.getDano().getRolagemDano().get(i).getDanos().size(); j++) {
					danoTotal += testeATK.getDano().getRolagemDano().get(i).getDanos().get(j);
				}
			}
			for(int i = 0; i < testeATK.getDano().getRolagemDano().size(); i++) {
				System.out.print(testeATK.getDano().getRolagemDano().get(i).getDadoUsado());
				System.out.print(testeATK.getDano().getRolagemDano().get(i).getDanos());
				System.out.print(" / ");
			}
			System.out.println("= " + danoTotal);
		} else {
			System.out.println();
			testeATK.getDano().rolarDano();
			danoTotal = 0;
			for(int i = 0; i < testeATK.getDano().getDados().size(); i++) {
				if(i == (testeATK.getDano().getDados().size() - 1)) {
					System.out.print(testeATK.getDano().getDados().get(i).getQuantidade() + "D" + testeATK.getDano().getDados().get(i).getFaces() + "\n");	
				} else {
					System.out.print(testeATK.getDano().getDados().get(i).getQuantidade() + "D" + testeATK.getDano().getDados().get(i).getFaces() + " + ");
				}
			}
			for(int i = 0; i < testeATK.getDano().getRolagemDano().size(); i++) {
				for(int j = 0; j < testeATK.getDano().getRolagemDano().get(i).getDanos().size(); j++) {
					danoTotal += testeATK.getDano().getRolagemDano().get(i).getDanos().get(j);
				}
			}
			for(int i = 0; i < testeATK.getDano().getRolagemDano().size(); i++) {
				System.out.print(testeATK.getDano().getRolagemDano().get(i).getDadoUsado());
				System.out.print(testeATK.getDano().getRolagemDano().get(i).getDanos());
				System.out.print(" / ");
			}
			System.out.println("= " + danoTotal);
		}
		int danoFinal = danoTotal;
		for(int i = 0; i < testeATK.getDano().getFixos().size(); i++){
			System.out.println(testeATK.getDano().getFixos().get(i).getNome() + " +" + testeATK.getDano().getFixos().get(i).getValor());
			danoFinal += testeATK.getDano().getFixos().get(i).getValor();
		}
		System.out.println("Dano: " + danoFinal);
		
		System.out.println("\n*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");
		
		
		
		// Teste com a pasta de dados

		
		
		PastaDados pasta = new PastaDados("Pastinha Legal");
		
		ArrayList<DadoCustomizado> dadinhos = new ArrayList<DadoCustomizado>();
		
		dadinhos.add(testeATK);
		dadinhos.add(teste2);
		
		pasta.setDadosPasta(dadinhos);
		
		System.out.println("Pasta: " + pasta.getNomePasta());
		System.out.println("Dados: ");
		for(int i = 0; i < pasta.getDadosPasta().size(); i++) {
			System.out.print("[" + pasta.getDadosPasta().get(i).getNome() + " | ");
			System.out.print(pasta.getDadosPasta().get(i).getTipo() + " | ");
			System.out.println(pasta.getDadosPasta().get(i).getTeste().getQuantidade() + "D20 ]");
		}
	}

}