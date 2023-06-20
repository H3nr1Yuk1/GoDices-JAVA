package Front;

import java.util.ArrayList;

import Entidades.DadoCustomizado;
import Entidades.PastaDados;
import Persistencia.DadoCustomizadoPersistencia;
import Persistencia.PastaDadosPersistencia;

public class AppPastaDados {

	public static int resp = 0;
	public static boolean keyC1 = true; 
	
	public static void gerarMenuPastaDados() {
		do {
			gerarMenu();
	        resp = Console.readInt("▣ Ir para? ");
	        switch(resp) {
	        case 1:
	        	int respC1 = 0;
	        	System.out.println("●------------------------------------●");
				System.out.println("Indo para [ Acessar Pastas ]");
				gerarListaPastas();
				if(keyC1 == false) {
					keyC1 = true;
					break;
				}
				respC1 = Console.readInt("▣ Entrar em qual pasta? ");
				System.out.println("●------------------------------------●");
				System.out.println("Indo para a pasta [ " + PastaDadosPersistencia.procurarPastaDadosIndex(respC1).getNomePasta() + " ]");
				gerarMenuPastaDadoEscolhida(PastaDadosPersistencia.procurarPastaDadosIndex(respC1));
	        	break;
	        case 2:
	        	System.out.println("●------------------------------------●");
				System.out.println("Indo para [ Criar Pasta ]");
				PastaDados pastaNova = new PastaDados();
				pastaNova.setNomePasta(AppFront.formatarOneUpper(Console.readString("▣ Qual será o nome da pasta? ")));
				String verifica = Console.readString("▣ Quer adicionar dados à ela? [S / N] ");
				if(verifica.equalsIgnoreCase("S") || verifica.equalsIgnoreCase("SIM")) {
					System.out.println("●------------------------------------●");
					if(!DadoCustomizadoPersistencia.listarDadoCustomizado().isEmpty()) {
						int i = 1;
						for(DadoCustomizado dadoAdicionar : DadoCustomizadoPersistencia.listarDadoCustomizado()) {
							System.out.println("□ " + i + " - " + dadoAdicionar.getNome());
							AppDadosCustomizados.gerarDadosDescricao(dadoAdicionar);
							i++;
						}
						String respAddDado = "";
						do{
							int respDA = Console.readInt("▣ Qual dado adicionar? ");
							i = 1;
							for(DadoCustomizado dadoAdicionar : DadoCustomizadoPersistencia.listarDadoCustomizado()) {
								if(respDA == i) {
									if(pastaNova.getDadosPasta() == null) {
										pastaNova.setDadosPasta(new ArrayList<>());
									}
									pastaNova.getDadosPasta().add(dadoAdicionar);
								}
								i++;
							}
							respAddDado = Console.readString("▣ Deseja adicionar mais dados? [S/N] ");
						} while(respAddDado.equalsIgnoreCase("S"));
						System.out.println("▣ Beleza!");
						if(PastaDadosPersistencia.criarPastaDados(pastaNova) != false) {
							System.out.println("▣ Pasta criada!");
						} else {
							System.out.println("◊ Houve um erro na criação da pasta.");
							System.out.println("◊ Tente novamente.");
						}
					} else {
						System.out.println("◊ Não há dados para adicionar!");
						if(PastaDadosPersistencia.criarPastaDados(pastaNova) != false) {
							System.out.println("▣ Pasta criada!");
						} else {
							System.out.println("◊ Houve um erro na criação da pasta.");
							System.out.println("◊ Tente novamente.");
						}
					}
				} else {
					System.out.println("▣ Beleza!");
					if(PastaDadosPersistencia.criarPastaDados(pastaNova) != false) {
						System.out.println("▣ Pasta criada!");
					} else {
						System.out.println("◊ Houve um erro na criação da pasta.");
						System.out.println("◊ Tente novamente.");
					}
				}
	        	break;
	        case 3:
	        	int respC3 = 0;
	        	System.out.println("●------------------------------------●");
				System.out.println("Indo para [ Remover Pasta ]");
				gerarListaPastas();
				if(keyC1 == false) {
					keyC1 = true;
					break;
				}
				respC3 = Console.readInt("▣ Remover em qual pasta? ");
				PastaDados pastaDeletar = PastaDadosPersistencia.procurarPastaDadosIndex(respC3);				
				if(PastaDadosPersistencia.procurarPastaDados(pastaDeletar) != null) {
					if(PastaDadosPersistencia.removerPastaDados(PastaDadosPersistencia.procurarPastaDados(pastaDeletar)) != false) {
						System.out.println("▣ Pasta deletada com sucesso!");
					} else {
						System.out.println("◊ Houve um erro na remoção da pasta.");
						System.out.println("◊ Tente novamente.");
					}
				} else {
					System.out.println("◊ Pasta não encontrada!");
				}
	        	break;
	        case 4:
	        	int respC4 = 0;
	        	System.out.println("●------------------------------------●");
				System.out.println("Indo para [ Editar Pasta ]");
				gerarListaPastas();
				if(keyC1 == false) {
					keyC1 = true;
					break;
				}
				respC4 = Console.readInt("▣ Editar nome de qual pasta? ");
				PastaDados pastaEditar = PastaDadosPersistencia.procurarPastaDadosIndex(respC4);
				if(PastaDadosPersistencia.procurarPastaDados(pastaEditar) != null) {
					pastaEditar = PastaDadosPersistencia.procurarPastaDados(pastaEditar);
					pastaEditar.setNomePasta(Console.readString("▣ Novo nome: "));
					if(PastaDadosPersistencia.atualizarPastaDados(pastaEditar) != false) {
						System.out.println("▣ Pasta atualizada com sucesso!");
					} else {
						System.out.println("◊ Houve um erro na edição da pasta.");
						System.out.println("◊ Tente novamente.");
					}
				}else {
					System.out.println("◊ Pasta não encontrada!");
				}
	        	break;
	        case 5:
	        	System.out.println("●------------------------------------●");
				System.out.println("Indo para [ Menu Inicial ]");
	        	break;
	        default:
	        	System.out.println("●------------------------------------●");
				System.out.println("Opção inválida!!!");
				System.out.println("Selecione uma opção novamente.");
	        	break;
	        }
		}while (resp != 5);
	}
	
	public static void gerarMenu() {
		System.out.println("●------------------------------------●");
		System.out.println("■ 1 - Acessar Pastas");
        System.out.println("■ 2 - Criar Pasta");
        System.out.println("■ 3 - Remover Pasta");
        System.out.println("■ 4 - Editar Pasta");
        System.out.println("■ 5 - Voltar");
        System.out.println("●------------------------------------●");
	}
	
	public static void gerarListaPastas() {
		System.out.println("●------------------------------------●");
		if(PastaDadosPersistencia.listarPastaDados() != null) {
			if(!PastaDadosPersistencia.listarPastaDados().isEmpty()) {
				int i = 1;
				for(PastaDados listaPastas : PastaDadosPersistencia.listarPastaDados()) {
					System.out.println("■ " + i + " - " + listaPastas.getNomePasta());
					i++;
				}
				System.out.println("●------------------------------------●");
			} else {
				System.out.println("◊ Não há nenhuma pasta criada!");
				keyC1 = false;
			}	
		} else {
			System.out.println("◊ Não há nenhuma pasta criada!");
			keyC1 = false;
		}
	}
	
	public static void gerarMenuPastaDadoEscolhida(PastaDados pastaEscolhida) {
		System.out.println("●------------------------------------●");
		System.out.println("Pasta: " + pastaEscolhida.getNomePasta());
		if(pastaEscolhida.getDadosPasta() == null || pastaEscolhida.getDadosPasta().isEmpty()) {
			System.out.println("\nNão há nenhum dado em sua pasta\n");
			AppDadosCustomizados.iniciarAppDados(pastaEscolhida.getId());
		} else {
			System.out.println("Dados : ");
			for(DadoCustomizado dado : pastaEscolhida.getDadosPasta()) {
				System.out.println("□ " + dado.getNome());
				AppDadosCustomizados.gerarDadosDescricao(dado);
			}
			AppDadosCustomizados.iniciarAppDados(pastaEscolhida.getId());
		}
	}
}