package Front;

import java.util.ArrayList;

import Entidades.Critico;
import Entidades.DadoCustomizado;
import Entidades.DadoDano;
import Entidades.DadoPadrao;
import Entidades.Dano;
import Entidades.Modificador;
import Entidades.PastaDados;
import Negocios.CriticoNegocios;
import Negocios.DadoCustomizadoNegocios;
import Negocios.DadoDanoNegocios;
import Negocios.DadoPadraoNegocios;
import Negocios.DanoNegocios;
import Negocios.ModificadorNegocios;
import Persistencia.CriticoPersistencia;
import Persistencia.DadoCustomizadoPersistencia;
import Persistencia.DadoDanoPersistencia;
import Persistencia.DadoPadraoPersistencia;
import Persistencia.DanoPersistencia;
import Persistencia.ModificadorPersistencia;
import Persistencia.PastaDadosPersistencia;

public class AppDadosCustomizados {
	
	public static Long pastaUsadaId = null;
	
	public static int iniciarAppDados(Long id) {
		pastaUsadaId = id;
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
			gerarMenuRemoverDado();
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
	
	public static void gerarDadosDescricao(DadoCustomizado dadoEnviado) {
		System.out.print("◌ " + dadoEnviado.getTeste().getQuantidade() + "d20 ");
		if(dadoEnviado.getModificadores() != null) {
			if(!dadoEnviado.getModificadores().isEmpty()) {
				for(Modificador listaMod : dadoEnviado.getModificadores()) {
					System.out.print("+ " + listaMod.getNome() + "(" + listaMod.getValor() + ") ");
				}
			}
		}
		System.out.print("|| Dano: ");
		for(DadoDano dadosDano : dadoEnviado.getDano().getDados()) {
			System.out.print(dadosDano.getQuantidade() + "d" + dadosDano.getFaces() + " [" + dadosDano.getTipo() + "] ");
		}
		if(dadoEnviado.getDano().getFixos() != null) {
			if(!dadoEnviado.getDano().getFixos().isEmpty()) {
				System.out.print("+ ");
				for(Modificador modDano : dadoEnviado.getDano().getFixos()) {
					System.out.print(modDano.getNome() + "(" + modDano.getValor() + ") ");
				}
			}
		}
		System.out.println("|| Crítico " + dadoEnviado.getCritico().getMargem() + "/x" + dadoEnviado.getCritico().getMultiplicador());
	}
	
	public static void gerarMenuCriarDado() {
		DadoCustomizado dadoNovo = new DadoCustomizado();
		Dano dano = new Dano();
		DadoPadrao teste = new DadoPadrao(20);
		DadoDano dadoDmg;
		ArrayList<DadoDano> dadosDano = new ArrayList<DadoDano>();
		ArrayList<Modificador> modificadores = new ArrayList<Modificador>();
		ArrayList<Modificador> modificadoresDano = new ArrayList<Modificador>();
		Modificador mod;
		Modificador modDano;
		Critico critico = new Critico();
		System.out.println("◯------------------------------------◯");
		dadoNovo.setNome(AppFront.formatarOneUpper(Console.readString("▣ Nome do dado: ")));
		teste.setQuantidade(Console.readInt("▣ Quantidade de D20: "));
		if(DadoPadraoNegocios.verificarDadoPadrao(teste)) {
			dadoNovo.setTeste(teste);
			DadoPadraoPersistencia.criarDadoPadrao(teste);
		} else {
			teste = DadoPadraoPersistencia.procurarDadoPadrao(teste);
			dadoNovo.setTeste(teste);
		}
		if(Console.readString("▣ Adicionar modificadores ao teste? [S/N] ").equalsIgnoreCase("S")) {
			String respMod = "";
			do {
				int i = 0;
				mod = new Modificador();
				mod.setNome(AppFront.formatarOneUpper(Console.readString("▣ Nome do modificador: ")));
				mod.setValor(Console.readInt("▣ Valor do modificador: "));
				if(i != 0) {
					if(!modificadores.get(i).equals(mod)) {
						if(ModificadorNegocios.verificarModificador(mod)) {
							modificadores.add(mod);
							dadoNovo.setModificadores(modificadores);
							ModificadorPersistencia.criarModificador(mod);
						} else {
							mod = ModificadorPersistencia.procurarModificador(mod);
							modificadores.add(mod);
							dadoNovo.setModificadores(modificadores);
						}
						System.out.println("▣ Modificador adicionado!");
						i++;
					} else {
						System.out.println("◊ Modificador já existente");
					}
				} else {
					if(ModificadorNegocios.verificarModificador(mod)) {
						modificadores.add(mod);
						dadoNovo.setModificadores(modificadores);
						ModificadorPersistencia.criarModificador(mod);
					} else {
						mod = ModificadorPersistencia.procurarModificador(mod);
						modificadores.add(mod);
						dadoNovo.setModificadores(modificadores);
					}
					System.out.println("▣ Modificador adicionado!");
					i++;
				}
				respMod = Console.readString("▣ Adicionar mais modificadores?  [S/N] ");
				if(respMod.equalsIgnoreCase("S")) {
					System.out.println();
				}
			} while (respMod.equalsIgnoreCase("S"));
		}
		critico.setMargem(Console.readInt("▣ Margem de ameaça: "));
		critico.setMultiplicador(Console.readInt("▣ Multiplicador (Número): X"));
		if(CriticoNegocios.verificarCritico(critico)) {
			dano.setCritico(critico);
			dadoNovo.setCritico(critico);
			CriticoPersistencia.criarCritico(critico);
		} else {
			critico = CriticoPersistencia.procurarCritico(critico);
			dano.setCritico(critico);
			dadoNovo.setCritico(critico);
		}
		String respDmg = "";
		do {
			int i = 0;
			dadoDmg = new DadoDano();
			dadoDmg.setFaces(Console.readInt("▣ Dado de dano (Apenas número): D"));
			dadoDmg.setQuantidade(Console.readInt("▣ Quantidade de dados: "));
			dadoDmg.setTipo(AppFront.formatarOneUpper(Console.readString("▣ Tipo de dano: ")));
			if(i != 0) {
				if(!dadosDano.get(i).equals(dadoDmg)) {
					if(DadoDanoNegocios.verificarDadoDano(dadoDmg)) {
						dadosDano.add(dadoDmg);
						dano.setDados(dadosDano);
						DadoDanoPersistencia.criarDadoDano(dadoDmg);
					} else {
						dadoDmg = DadoDanoPersistencia.procurarDadoDano(dadoDmg);
						dadosDano.add(dadoDmg);
						dano.setDados(dadosDano);
					}
					System.out.println("▣ Dado adicionado!");
					i++;
				} else {
					System.out.println("◊ Dado já existente");
				}
			} else {
				if(DadoDanoNegocios.verificarDadoDano(dadoDmg)) {
					dadosDano.add(dadoDmg);
					dano.setDados(dadosDano);
					DadoDanoPersistencia.criarDadoDano(dadoDmg);
				} else {
					dadoDmg = DadoDanoPersistencia.procurarDadoDano(dadoDmg);
					dadosDano.add(dadoDmg);
					dano.setDados(dadosDano);
				}
				System.out.println("▣ Dado adicionado!");
				i++;
			}
			respDmg = Console.readString("▣ Adicionar mais dados? [S/N] ");
			if(respDmg.equalsIgnoreCase("S")) {
				System.out.println();
			}
		} while(respDmg.equalsIgnoreCase("S"));
		respDmg = Console.readString("▣ Adicionar valor inteiro ao dano? [S/N] ");
		if(respDmg.equalsIgnoreCase("S")) {
			respDmg = "";
			do {
				int i = 0;
				modDano = new Modificador();
				modDano.setNome(AppFront.formatarOneUpper(Console.readString("▣ Nome do modificador: ")));
				modDano.setValor(Console.readInt("▣ Valor do modificador: "));
				if(i != 0) {
					if(!modificadoresDano.get(i).equals(modDano)) {
						if(ModificadorNegocios.verificarModificador(modDano)) {
							modificadoresDano.add(modDano);
							dano.setFixos(modificadoresDano);
							ModificadorPersistencia.criarModificador(modDano);
						} else {
							modDano = ModificadorPersistencia.procurarModificador(modDano);
							modificadoresDano.add(modDano);
							dano.setFixos(modificadoresDano);
						}
						System.out.println("▣ Modificador adicionado!");
						i++;
					} else {
						System.out.println("◊ Modificador já existente");
					}
				} else {
					if(ModificadorNegocios.verificarModificador(modDano)) {
						modificadoresDano.add(modDano);
						dano.setFixos(modificadoresDano);
						ModificadorPersistencia.criarModificador(modDano);
					} else {
						modDano = ModificadorPersistencia.procurarModificador(modDano);
						modificadoresDano.add(modDano);
						dano.setFixos(modificadoresDano);
					}
					System.out.println("▣ Modificador adicionado!");
					i++;
				}
				respDmg = Console.readString("▣ Adicionar mais modificadores?  [S/N] ");
				if(respDmg.equalsIgnoreCase("S")) {
					System.out.println();
				}
			} while(respDmg.equalsIgnoreCase("S"));
		}
		if(DanoNegocios.verificarDano(dano)) {
			dadoNovo.setDano(dano);
			DanoPersistencia.criarDano(dano);
		} else {
			dano = DanoPersistencia.procurarDano(dano);
			dadoNovo.setDano(dano);
		}
		PastaDados pasta = PastaDadosPersistencia.procurarPastaDadosId(pastaUsadaId);
		if(DadoCustomizadoNegocios.verificarDadoExistente(dadoNovo)) {
			if(DadoCustomizadoPersistencia.criarDadoCustomizado(dadoNovo)) {
				if(pasta.getDadosPasta() == null) {
					pasta.setDadosPasta(new ArrayList<>());
				}
				pasta.getDadosPasta().add(dadoNovo);
				if(PastaDadosPersistencia.atualizarPastaDados(pasta)) {
					System.out.println("▣ Dado criado e adicionado com sucesso!");
					AppPastaDados.gerarMenuPastaDadoEscolhida(pasta);
				} else {
					System.out.println("◊ Falha ao adicionar o dado!");
					System.out.println("◊ Tente novamente.");
					AppPastaDados.gerarMenuPastaDadoEscolhida(pasta);
				}
			} else {
				System.out.println("◊ Houve um erro!");
				System.out.println("◊ Tente novamente.");
				AppPastaDados.gerarMenuPastaDadoEscolhida(pasta);
			}
		} else {
			System.out.println("◊ Dado já existente!");
			AppPastaDados.gerarMenuPastaDadoEscolhida(pasta);
		}
	}
	
	public static void gerarMenuListaDados() {
		System.out.println("◯------------------------------------◯");
		if(PastaDadosPersistencia.procurarPastaDadosId(pastaUsadaId) != null) {
			PastaDados pastaUsada = PastaDadosPersistencia.procurarPastaDadosId(pastaUsadaId);
			int i = 1;
			for(DadoCustomizado dadosPasta : pastaUsada.getDadosPasta()) {
				System.out.println("□ " + i + " - " + dadosPasta.getNome());
				gerarDadosDescricao(dadosPasta);
				i++;
			}
		}
	}
	
	public static void gerarMenuRemoverDado() {
		gerarMenuListaDados();
		if(PastaDadosPersistencia.procurarPastaDadosId(pastaUsadaId) != null) {
			if(PastaDadosPersistencia.procurarPastaDadosId(pastaUsadaId).getDadosPasta() != null && !PastaDadosPersistencia.procurarPastaDadosId(pastaUsadaId).getDadosPasta().isEmpty()) {
				int dadoApagar = Console.readInt("\n▣ Remover qual dado? ");
				PastaDados pastaUsada = PastaDadosPersistencia.procurarPastaDadosId(pastaUsadaId);
				int i = 1;
				for(DadoCustomizado dadoPasta : pastaUsada.getDadosPasta()) {
					if(i == dadoApagar) {
						if(PastaDadosPersistencia.removerDadoDaPasta(pastaUsada, dadoPasta)) {
							System.out.println("▣ Dado removido com sucesso!");
							AppPastaDados.gerarMenuPastaDadoEscolhida(pastaUsada);
						} else {
							System.out.println("◊ Falha ao remover o dado!");
							System.out.println("◊ Tente novamente.");
							AppPastaDados.gerarMenuPastaDadoEscolhida(pastaUsada);
						}
						return;
					}
					i++;
				}
			} else {
				System.out.println("\nNão há nenhum dado nessa pasta!\n");
			}
		}
	}
}
