package Front;

import java.util.ArrayList;

import Entidades.Critico;
import Entidades.DadoCustomizado;
import Entidades.DadoDano;
import Entidades.DadoPadrao;
import Entidades.Dano;
import Entidades.Modificador;
import Entidades.PastaDados;
import Entidades.Resultado;
import Entidades.RolagemDano;
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
import Persistencia.ResultadoPersistencia;
import Persistencia.RolagemDanoPersistencia;

public class AppDadosCustomizados {
	
	public static Long pastaUsadaId = null;
	
	public static void iniciarAppDados(Long id) {
		pastaUsadaId = id;
		int respDC = 0;
		gerarMenuDadoCustomizado();
		respDC = Console.readInt("▣ Ir para? ");
		switch(respDC) {
		case 1:
			System.out.println("◯------------------------------------◯");
			System.out.println("Indo para [ Criar Dado ]");
			gerarMenuCriarDado(true);
			break;
		case 2:
			System.out.println("◯------------------------------------◯");
			System.out.println("Indo para [ Rolar Dado ]");
			gerarMenuRolarDado(true);
			break;
		case 3:
			System.out.println("◯------------------------------------◯");
			System.out.println("Indo para [ Remover Dado ]");
			gerarMenuRemoverDado(true);
			break;
		case 4:
			System.out.println("◯------------------------------------◯");
			System.out.println("Voltando para [ Minhas Pastas ]");
			break;
		default:
			System.out.println("◯------------------------------------◯");
			System.out.println("Opção inválida!!!");
			System.out.println("Selecione uma opção novamente.");
			break;
		}
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
		int lastIndex = dadoEnviado.getDano().getDados().size();
		int index = 0;
		for(DadoDano dadosDano : dadoEnviado.getDano().getDados()) {
			index++;			
			System.out.print(dadosDano.getQuantidade() + "d" + dadosDano.getFaces() + " [" + dadosDano.getTipo() + "] ");
			if(index != lastIndex) {
				System.out.print("+ ");
			}
		}
		index = 0;
		if(dadoEnviado.getDano().getFixos() != null) {
			if(!dadoEnviado.getDano().getFixos().isEmpty()) {
				System.out.print("+ ");
				for(Modificador modDano : dadoEnviado.getDano().getFixos()) {
					index++;
					System.out.print(modDano.getNome() + "(" + modDano.getValor() + ") ");
					if(index != lastIndex) {
						System.out.print("+ ");
					}
				}
			}
		}
		System.out.println("|| Crítico " + dadoEnviado.getCritico().getMargem() + "/x" + dadoEnviado.getCritico().getMultiplicador());
		System.out.println();
	}
	
	public static void gerarMenuListaDados(boolean pasta) {
		System.out.println("◯------------------------------------◯");
		if(pasta) {
			if(PastaDadosPersistencia.procurarPastaDadosId(pastaUsadaId) != null) {
				PastaDados pastaUsada = PastaDadosPersistencia.procurarPastaDadosId(pastaUsadaId);
				int i = 1;
				for(DadoCustomizado dadosPasta : pastaUsada.getDadosPasta()) {
					System.out.println();
					System.out.println("□ " + i + " - " + dadosPasta.getNome());
					gerarDadosDescricao(dadosPasta);
					i++;
				}
			}
		} else {
			if(DadoCustomizadoPersistencia.listarDadoCustomizado() != null) {
				if(!DadoCustomizadoPersistencia.listarDadoCustomizado().isEmpty()) {
					int i = 1;
					for(DadoCustomizado dados : DadoCustomizadoPersistencia.listarDadoCustomizado()) {
						System.out.println("□ " + i + " - " + dados.getNome());
						gerarDadosDescricao(dados);
						i++;
					}
				}
			}
		}
	}
	
	public static void gerarMenuCriarDado(boolean comPasta) {
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
		Resultado resultado = new Resultado();
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
							resultado.setModificadores(modificadores);
							ModificadorPersistencia.criarModificador(mod);
						} else {
							mod = ModificadorPersistencia.procurarModificador(mod);
							modificadores.add(mod);
							dadoNovo.setModificadores(modificadores);
							resultado.setModificadores(modificadores);
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
						resultado.setModificadores(modificadores);
						ModificadorPersistencia.criarModificador(mod);
					} else {
						mod = ModificadorPersistencia.procurarModificador(mod);
						modificadores.add(mod);
						dadoNovo.setModificadores(modificadores);
						resultado.setModificadores(modificadores);
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
		dadoNovo.setResultado(resultado);
		ResultadoPersistencia.criarResultado(resultado);
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
		if(comPasta) {
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
		} else {
			if(DadoCustomizadoNegocios.verificarDadoExistente(dadoNovo)) {
				if(DadoCustomizadoPersistencia.criarDadoCustomizado(dadoNovo)) {
					System.out.println("▣ Dado criado com sucesso!");
					gerarMenuMeusDados();
				} else {
					System.out.println("◊ Falha ao criar o dado!");
					System.out.println("◊ Tente novamente.");
					gerarMenuMeusDados();
				}
			} else {
				System.out.println("◊ Dado já existente!");
				gerarMenuMeusDados();
			}
		}
		
	}
	
	public static void gerarMenuRemoverDado(boolean comPasta) {
		gerarMenuListaDados(comPasta);
		if(comPasta) {
			if(PastaDadosPersistencia.procurarPastaDadosId(pastaUsadaId) != null) {
				if(PastaDadosPersistencia.procurarPastaDadosId(pastaUsadaId).getDadosPasta() != null && !PastaDadosPersistencia.procurarPastaDadosId(pastaUsadaId).getDadosPasta().isEmpty()) {
					int dadoApagar = Console.readInt("▣ Remover qual dado? ");
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
			} else {
				System.out.println("\nNão há nenhum dado nessa pasta!\n");
			}
		} else {
			if(DadoCustomizadoPersistencia.listarDadoCustomizado() != null) {
				if(!DadoCustomizadoPersistencia.listarDadoCustomizado().isEmpty()) {
					int dadoApagar = Console.readInt("▣ Remover qual dado? ");
					int i = 1;
					for(DadoCustomizado dadosLista : DadoCustomizadoPersistencia.listarDadoCustomizado()) {
						if(dadoApagar == i) {
							if(DadoCustomizadoPersistencia.removerDadoCustomizado(dadosLista)) {
								System.out.println("▣ Dado removido com sucesso!");
							} else {
								System.out.println("◊ Falha ao remover o dado!");
								System.out.println("◊ Tente novamente.");
							}
						}
						i++;
					}
				} else {
					System.out.println("\nNão há nenhum dado criado!\n");
				}
			} else {
				System.out.println("\nNão há nenhum dado criado!\n");
			}
		}
	}
	
	public static void gerarMenuRolarDado(boolean comPasta) { 
		if(DadoCustomizadoPersistencia.listarDadoCustomizado() != null) {
			if(!DadoCustomizadoPersistencia.listarDadoCustomizado().isEmpty()) {
				gerarMenuListaDados(comPasta);
				System.out.println("◯------------------------------------◯");
				int respRdD = Console.readInt("▣ Rolar qual dado? ");
				int i = 1;
				for(DadoCustomizado dados : DadoCustomizadoPersistencia.listarDadoCustomizado()) {
					if(respRdD == i) {
						if(DadoCustomizadoPersistencia.procurarDadoCustomizado(dados) != null) {
							DadoCustomizado dado = DadoCustomizadoPersistencia.procurarDadoCustomizado(dados);
							gerarRolagemDado(dado);
						}
					}
					i++;
				}
			} else {
				System.out.println("\nNão há nenhum dado crido!\n");
			}
		} else {
			System.out.println("\nNão há nenhum dado crido!\n");
		}
	}
	
	public static void gerarRolagemDado(DadoCustomizado dado) {
		dado.rolarDado();
		System.out.println("◯------------------------------------◯");
		System.out.println("◉ Nome: " + dado.getNome());
		System.out.println("◉ Rolagem: " + dado.getTeste().getQuantidade() + "d20 = " + dado.getTeste().getRolagens());
		System.out.println("◉ Crítico: " + dado.getCritico().getMargem() + "/x" + dado.getCritico().getMultiplicador());
		System.out.print("◉ Modificadores: ");
		if(!dado.getModificadores().isEmpty()) {
			if(dado.getModificadores() != null) {
				int i = 0;
				int last = dado.getModificadores().size() - 1;
				for(Modificador modTeste : dado.getModificadores()) {
					System.out.print(modTeste.getNome() + "(" + modTeste.getValor() + ") ");
					if(i != last) {
						System.out.print("+ ");
					}
					i++;
				}
			} else {
				System.out.print("Nenhum");
			}
		} else {
			System.out.print("Nenhum");
		}
		int last = 0;
		System.out.println("\n◉ Resultado: " + dado.obterValor());
		boolean critico = dado.getResultado().getValorEscolhido() >= dado.getCritico().getMargem();
		if(critico) {
			System.out.print("◉ Dano [Crítico!]: ");
			int totalGeral = 0;
			int index = 0;
			last = dado.getDano().getDados().size() - 1 ;
			for(DadoDano dadosDano : dado.getDano().getDados()) {
				if(dadosDano.getTipo().equalsIgnoreCase("Morte") || dadosDano.getTipo().equalsIgnoreCase("Sangue") || dadosDano.getTipo().equalsIgnoreCase("Energia") || dadosDano.getTipo().equalsIgnoreCase("Conhecimento") || dadosDano.getTipo().equalsIgnoreCase("Medo")) {
					System.out.print(dadosDano.getQuantidade() + "d" + dadosDano.getFaces() + "[" + dadosDano.getTipo() + "] ");
				} else {
					System.out.print((dadosDano.getQuantidade() * dado.getCritico().getMultiplicador()) + "d" + dadosDano.getFaces() + "[" + dadosDano.getTipo() + "] ");
				}
				if(index != last) {
					System.out.print("+ ");
				}
				index++;
			}
			System.out.print("\n◉ Causado: ");
			index = 0;
			for(RolagemDano dano : dado.getDano().rolarDanoCritico()) {
				int total = 0;
				for(Integer danosTipo : dano.getDanos()) {
					total += danosTipo;
					totalGeral += danosTipo;
				}
				System.out.print(total + " " + dado.getDano().getDados().get(index).getTipo() + " " + dano.getDanos());
				if(index != last) {
					System.out.print(" + ");
				}
				index++;
			}
			System.out.print("\n◉ Extras: ");
			if(dado.getDano().getFixos() != null) {
				if(!dado.getDano().getFixos().isEmpty()) {
					index = 0;
					last = dado.getDano().getFixos().size() - 1;
					for(Modificador danoFix : dado.getDano().getFixos()) {
						totalGeral += danoFix.getValor();
						System.out.print(danoFix.getNome() + "(" + danoFix.getValor() + ") ");
						if(index != last) {
							System.out.print("+ ");
						}
						index++;
					}
				} else {
					System.out.print("Nenhum");
				}
			} else {
				System.out.print("Nenhum");
			}
			System.out.print("\n◉ Total Final: " + totalGeral + " de dano\n");
		} else {
			System.out.print("◉ Dano: ");
			int totalGeral = 0;
			int index = 0;
			last = dado.getDano().getDados().size() - 1 ;
			for(DadoDano dadosDano : dado.getDano().getDados()) {
				System.out.print(dadosDano.getQuantidade() + "d" + dadosDano.getFaces() + "[" + dadosDano.getTipo() + "] ");
				if(index != last) {
					System.out.print("+ ");
				}
				index++;
			}
			System.out.print("\n◉ Causado: ");
			index = 0;
			for(RolagemDano dano : dado.getDano().rolarDano()) {
				int total = 0;
				for(Integer danosTipo : dano.getDanos()) {
					total += danosTipo;
					totalGeral += danosTipo;
				}
				System.out.print(total + " " + dado.getDano().getDados().get(index).getTipo() + " " + dano.getDanos());
				if(index != last) {
					System.out.print(" + ");
				}
				index++;
			}
			System.out.print("\n◉ Extras: ");
			if(dado.getDano().getFixos() != null) {
				if(!dado.getDano().getFixos().isEmpty()) {
					index = 0;
					last = dado.getDano().getFixos().size() - 1;
					for(Modificador danoFix : dado.getDano().getFixos()) {
						totalGeral += danoFix.getValor();
						System.out.print(danoFix.getNome() + "(" + danoFix.getValor() + ") ");
						if(index != last) {
							System.out.print("+ ");
						}
						index++;
					}
				} else {
					System.out.print("Nenhum");
				}
			} else {
				System.out.print("Nenhum");
			}
			System.out.print("\n◉ Total Final: " + totalGeral + " de dano\n");
		}
		for(RolagemDano danoSalvar : dado.getDano().getRolagemDano()) {
			RolagemDanoPersistencia.criarRolagemDano(danoSalvar);	
		}
		ResultadoPersistencia.atualizarResultado(dado.getResultado());
		DadoCustomizadoPersistencia.atualizarDadoCustomizado(dado);
	}
	
	public static void gerarMenuMeusDados() {
		if(DadoCustomizadoPersistencia.listarDadoCustomizado() != null) {
			if(!DadoCustomizadoPersistencia.listarDadoCustomizado().isEmpty()) {
				gerarMenuListaDados(false);
			} else {
				System.out.println("\nNão há nenhum dado crido!\n");
			}
		} else {
			System.out.println("\nNão há nenhum dado crido!\n");
		}
		int respMD;
		do {
			gerarMenuDadoCustomizado();
			respMD = Console.readInt("▣ Ir para? ");
			switch(respMD) {
			case 1:
				System.out.println("◯------------------------------------◯");
				System.out.println("Indo para [ Criar Dado ]");
				gerarMenuCriarDado(false);
				break;
			case 2:
				System.out.println("◯------------------------------------◯");
				System.out.println("Indo para [ Rolar Dado ]");
				gerarMenuRolarDado(false);
				break;
			case 3:
				System.out.println("◯------------------------------------◯");
				System.out.println("Indo para [ Remover Dado ]");
				gerarMenuRemoverDado(false);
				break;
			case 4:
				System.out.println("◯------------------------------------◯");
				System.out.println("Voltando para [ Menu Inicial ]");
				break;
			default:
				System.out.println("◯------------------------------------◯");
				System.out.println("Opção inválida!!!");
				System.out.println("Selecione uma opção novamente.");
				break;
			}
		} while(respMD != 4);
	}
	
	public static void gerarUltimasRolagens() {
		if(ResultadoPersistencia.listarUltimos15DadosCustomizados() != null) {
			int tamanhoLista = ResultadoPersistencia.listarUltimos15DadosCustomizados().size();
		    int indiceInicio = Math.max(tamanhoLista - 15, 0);
		    int indiceFim = tamanhoLista;
		    
			System.out.println("◯------------------------------------◯\n");
			int i = 1;
			int last = 0;
			for(Resultado resultados : ResultadoPersistencia.listarUltimos15DadosCustomizados().subList(indiceInicio, indiceFim)) {
				DadoCustomizado dadoAchado = DadoCustomizadoPersistencia.procurarDadoCustomizadoResultadoId(resultados.getId());
				if(dadoAchado != null) {
					System.out.println("◈ " + dadoAchado.getNome() + " || Teste: " + dadoAchado.obterValor());
					System.out.print("◈ Dano: ");
					int total = 0;
					i = 0;
					last = dadoAchado.getDano().getRolagemDano().size() - 1;
					for(RolagemDano danoResult : dadoAchado.getDano().getRolagemDano()) {
						for(int val : danoResult.getDanos()) {
							total += val;
						}
						System.out.print(total + " " + dadoAchado.getDano().getDados().get(i).getTipo());
						if(i != last) {
							System.out.print(" + ");
						}
						i++;
					}
					i = 0;
					last = dadoAchado.getDano().getFixos().size() - 1 ;
					if(last != -1) {
						System.out.print(" + ");
						for(Modificador fixos : dadoAchado.getDano().getFixos()) {
							System.out.print(fixos.getNome() + "(" + fixos.getValor() + ") ");
						}
						if(i != last) {
							System.out.print(" + ");
						}
						i++;
					}
					System.out.println("\n");
				}
			}
		} else {
			System.out.println("◊ Não há resultados!");
		}
	}
	
	
}
