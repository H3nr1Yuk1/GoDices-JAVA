package Front;

public class AppFront {

	public static void main(String[] args) {
		int resp = 0;
		
		do{
			gerarMenuInicial();
			resp = Console.readInt("▣ Ir para? ");
		}while(resp!=4);
		
		System.out.println("●------------------------------------●");
		System.out.println("Espero que sua experiência com o");
		System.out.println("[ GO DICES ] tenha sido ótima!");
		System.out.println("Tchauzinho :)");
        System.out.println("●------------------------------------●");

	}
	
	public static void gerarMenuInicial() {
		System.out.println("●------------------------------------●");
		System.out.println("Olá! Seja bem-vindo(a) ao [GO DICES]");
        System.out.println("Espero que nossa aplicação seja de");
        System.out.println("extremo valor para você e seu RPG!");
        System.out.println("●------------------------------------●");
        System.out.println("■ 1 - Minhas pastas de dados");
        System.out.println("■ 2 - Últimas rolagens");
        System.out.println("■ 3 - Rolagem Rápida");
        System.out.println("■ 4 - Sair");
        System.out.println("●------------------------------------●");
	}

}
