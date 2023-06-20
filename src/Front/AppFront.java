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
				System.out.println("Indo para [ Meus dados ]");
				AppDadosCustomizados.gerarMenuMeusDados();
				break;
			case 3:
				System.out.println("●------------------------------------●");
				System.out.println("Indo para [ Últimas Rolagens ]");
				break;
			case 4:
				System.out.println("●------------------------------------●");
				System.out.println("Indo para [ Rolagens Rápidas ]");
				break;
			case 5:
				break;
			default:
				System.out.println("●------------------------------------●");
				System.out.println("Opção inválida!!!");
				System.out.println("Selecione uma opção novamente.");
				break;
			}
		}while(resp!=5);
		
		System.out.println("●------------------------------------●");
		System.out.println("Espero que sua experiência com o");
		System.out.println("[ GO DICES ] tenha sido ótima!");
		System.out.println("Tchauzinho :)");
        System.out.println("●------------------------------------●");

	}
	
	public static void gerarMenuInicial() {
        System.out.println("●------------------------------------●");
        System.out.println("■ 1 - Minhas pastas");
        System.out.println("■ 2 - Meus dados");
        System.out.println("■ 3 - Últimas rolagens");
        System.out.println("■ 4 - Rolagem Rápida");
        System.out.println("■ 5 - Sair");
        System.out.println("●------------------------------------●");
	}
	
	public static String formatarOneUpper(String frase) {
	    if (frase == null || frase.isEmpty()) {
	        return frase;
	    }
	    
	    String[] palavras = frase.split("\\s+");
	    StringBuilder fraseFormatada = new StringBuilder();
	    
	    for (String palavra : palavras) {
	        if (!palavra.isEmpty()) {
	            String primeiraLetra = palavra.substring(0, 1).toUpperCase();
	            String restantePalavra = palavra.substring(1).toLowerCase();
	            String palavraFormatada = primeiraLetra + restantePalavra;
	            fraseFormatada.append(palavraFormatada).append(" ");
	        }
	    }
	    
	    return fraseFormatada.toString().trim();
	}



}
