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
		dadoNovo.setNome(Console.readString("▣ Nome do dado: "));
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
				mod.setNome(Console.readString("▣ Nome do modificador: "));
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
			dadoDmg.setTipo(Console.readString("▣ Tipo de dano: "));
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
				modDano.setNome(Console.readString("▣ Nome do modificador: "));
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
		if(DadoCustomizadoNegocios.verificarDadoExistente(dadoNovo)) {
			if(DadoCustomizadoPersistencia.criarDadoCustomizado(dadoNovo)) {
				PastaDados pasta = PastaDadosPersistencia.procurarPastaDadosId(pastaUsadaId);
				ArrayList<DadoCustomizado> dados = new ArrayList<DadoCustomizado>();
				System.out.println(pasta.getDadosPasta());
				if(pasta.getDadosPasta() == null) {
					dados.add(dadoNovo);
					pasta.setDadosPasta(dados);
				} else {
					pasta.getDadosPasta().add(dadoNovo);
				}
				System.out.println(pasta.getDadosPasta());
				if(PastaDadosPersistencia.atualizarPastaDados(pasta)) {
					System.out.println("▣ Dado criado e adicionado com sucesso!");
				} else {
					System.out.println("◊ Falha ao adicionar o dado!");
					System.out.println("◊ Tente novamente.");
				}
			} else {
				System.out.println("◊ Houve um erro!");
				System.out.println("◊ Tente novamente.");
			}
		} else {
			System.out.println("◊ Dado já existente!");
		}
	}
}
