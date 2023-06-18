package Front;

import java.util.ArrayList;

import Entidades.Critico;
import Entidades.Dado;
import Entidades.DadoCustomizado;
import Entidades.DadoDano;
import Entidades.DadoPadrao;
import Entidades.Dano;
import Entidades.Modificador;
import Negocios.CriticoNegocios;
import Negocios.DadoCustomizadoNegocios;
import Negocios.DanoNegocios;
import Persistencia.CriticoPersistencia;
import Persistencia.DadoCustomizadoPersistencia;
import Persistencia.DanoPersistencia;

public class AppDadosCustomizados {
	
	public static int iniciarAppDados() {
		int respDC = 0;
		gerarMenuDadoCustomizado();
		respDC = Console.readInt("▣ Ir para? ");
		switch(respDC) {
		case 1:
			System.out.println("◯------------------------------------◯");
			System.out.println("Indo para [ Criar dado ]");
			gerarMenuCriarDado();
			break;
		case 2:
			System.out.println("◯------------------------------------◯");
			System.out.println("Indo para [ Rolar dado ]");
			break;
		case 3:
			System.out.println("◯------------------------------------◯");
			System.out.println("Indo para [ Remover dado ]");
			break;
		case 4:
			System.out.println("◯------------------------------------◯");
			System.out.println("Indo para [ Minhas Pastas ]");
			return 4;
		default:
			System.out.println("◯------------------------------------◯");
			System.out.println("Opção inválida!!!");
			System.out.println("Selecione uma opção novamente.");
			break;
		}
		return 0;
	}
	
	public static void gerarMenuDadoCustomizado() {
		System.out.println("◯------------------------------------◯");
		System.out.println("■ 1 - Criar dado");
		System.out.println("■ 2 - Rolar dado");
		System.out.println("■ 3 - Remover dado");
		System.out.println("■ 4 - Voltar");
		System.out.println("◯------------------------------------◯");
	}
	
	public static void gerarMenuCriarDado() {
		DadoCustomizado dadoNovo = new DadoCustomizado();
		DadoPadrao teste = new DadoPadrao(20);
		DadoDano dadoDmg = new DadoDano();
		ArrayList<Dado> dadosDano = new ArrayList<Dado>();
		ArrayList<Modificador> modificadores = new ArrayList<Modificador>();
		ArrayList<Modificador> modificadoresDano = new ArrayList<Modificador>();
		Modificador mod = new Modificador();
		Modificador modDano = new Modificador();
		Critico critico = new Critico();
		Dano dano = new Dano();
		System.out.println("◯------------------------------------◯");
		dadoNovo.setNome(Console.readString("▣ Nome do dado: "));
		teste.setQuantidade(Console.readInt("▣ Quantidade de D20: "));
		dadoNovo.setTeste(teste);
		if(Console.readString("▣ Adicionar modificadores ao teste? [S/N] ").equalsIgnoreCase("S")) {
			String respMod = "";
			do {
				int i = 0;
				mod.setNome(Console.readString("▣ Nome do modificador: "));
				mod.setValor(Console.readInt("▣ Valor do modificador: "));
				if(i != 0) {
					if(!modificadores.get(i).equals(mod)) {
						modificadores.add(mod);
						System.out.println("▣ Modificador adicionado!");
						i++;
					} else {
						System.out.println("◊ Modificador já existente");
					}
				} else {
					modificadores.add(mod);
					System.out.println("▣ Modificador adicionado!");
					i++;
				}
				respMod = Console.readString("▣ Adicionar mais modificadores?  [S/N] ");
				if(respMod.equalsIgnoreCase("S")) {
					System.out.println();
				}
			} while (respMod.equalsIgnoreCase("S"));
		}
		dadoNovo.setModificadores(modificadores);
		critico.setMargem(Console.readInt("▣ Margem de ameaça: "));
		critico.setMultiplicador(Console.readInt("▣ Multiplicador (Número): X"));
		if(CriticoNegocios.verificarCritico(critico)) {
			CriticoPersistencia.criarCritico(critico);
		} else {
			critico = CriticoPersistencia.procurarCritico(critico);
		}
		dadoNovo.setCritico(critico);
		String respDmg = "";
		do {
			int i = 0;
			dadoDmg.setFaces(Console.readInt("▣ Dado de dano (Apenas número): D"));
			dadoDmg.setQuantidade(Console.readInt("▣ Quantidade de dados: "));
			dadoDmg.setTipo(Console.readString("▣ Tipo de dano: "));
			if(i != 0) {
				if(!dadosDano.get(i).equals(dadoDmg)) {
					dadosDano.add(dadoDmg);
					System.out.println("▣ Dado adicionado!");
					i++;
				} else {
					System.out.println("◊ Dado já existente");
				}
			} else {
				dadosDano.add(dadoDmg);
				System.out.println("▣ Dado adicionado!");
				i++;
			}
			respDmg = Console.readString("▣ Adicionar mais dados? [S/N] ");
			if(respDmg.equalsIgnoreCase("S")) {
				System.out.println();
			}
		} while(respDmg.equalsIgnoreCase("S"));
		dano.setDados(dadosDano);
		respDmg = Console.readString("▣ Adicionar valor inteiro ao dano? [S/N] ");
		if(respDmg.equalsIgnoreCase("S")) {
			respDmg = "";
			do {
				int i = 0;
				modDano.setNome(Console.readString("▣ Nome do modificador: "));
				modDano.setValor(Console.readInt("▣ Valor do modificador: "));
				if(i != 0) {
					if(!modificadoresDano.get(i).equals(modDano)) {
						modificadoresDano.add(modDano);
						System.out.println("▣ Modificador adicionado!");
						i++;
					} else {
						System.out.println("◊ Modificador já existente");
					}
				} else {
					modificadoresDano.add(modDano);
					System.out.println("▣ Modificador adicionado!");
					i++;
				}
				respDmg = Console.readString("▣ Adicionar mais modificadores?  [S/N] ");
				if(respDmg.equalsIgnoreCase("S")) {
					System.out.println();
				}
			} while(respDmg.equalsIgnoreCase("S"));
		}
		dano.setFixos(modificadoresDano);
		if(DanoNegocios.verificarDano(dano)) {
			DanoPersistencia.criarDano(dano);
		} else {
			dano = DanoPersistencia.procurarDano(dano);
		}
		dano.setCritico(critico);
		dadoNovo.setDano(dano);
		if(DadoCustomizadoNegocios.verificarDadoExistente(dadoNovo)) {
			if(DadoCustomizadoPersistencia.criarDadoCustomizado(dadoNovo) == true) {
				System.out.println("▣ Dado criado e adicionado com sucesso!");
			} else {
				System.out.println("◊ Houve um erro!");
				System.out.println("◊ Tente novamente.");
			}
		} else {
			System.out.println("◊ Dado já existente!");
		}
	}
}
