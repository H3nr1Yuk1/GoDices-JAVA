package Front;

import Persistencia.EntityManagerFactory;

public class AppFront {

	public static void main(String[] args) {
		EntityManagerFactory.getInstance();
		int resp = 0;
		
		System.out.println("●------------------------------------●");
		System.out.println("Olá! Seja bem-vindo(a) ao [GO DICES]");
        System.out.println("Espero que nossa aplicação seja de");
        System.out.println("extremo valor para você e seu RPG!");
        
		do{
			gerarMenuInicial();
			resp = Console.readInt("▣ Ir para? ");
			switch(resp) {
			case 1:
				System.out.println("●------------------------------------●");
				System.out.println("Indo para [ Minhas Pastas ]");
				AppPastaDados.gerarMenuPastaDados();
				break;
			case 2:
				System.out.println("●------------------------------------●");
				System.out.println("Indo para [ Últimas Rolagens ]");
				break;
			case 3:
				System.out.println("●------------------------------------●");
				System.out.println("Indo para [ Rolagens Rápidas ]");
				break;
			case 4:
				break;
			default:
				System.out.println("●------------------------------------●");
				System.out.println("Opção inválida!!!");
				System.out.println("Selecione uma opção novamente.");
				break;
			}
		}while(resp!=4);
		
		System.out.println("●------------------------------------●");
		System.out.println("Espero que sua experiência com o");
		System.out.println("[ GO DICES ] tenha sido ótima!");
		System.out.println("Tchauzinho :)");
        System.out.println("●------------------------------------●");

	}
	
	public static void gerarMenuInicial() {
        System.out.println("●------------------------------------●");
        System.out.println("■ 1 - Minhas pastas de dados");
        System.out.println("■ 2 - Últimas rolagens");
        System.out.println("■ 3 - Rolagem Rápida");
        System.out.println("■ 4 - Sair");
        System.out.println("●------------------------------------●");
	}

}
