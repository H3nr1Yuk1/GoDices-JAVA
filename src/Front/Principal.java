package Front;

import java.util.ArrayList;

import Entidades.D20;
import Entidades.DadoCustomizadoTeste;
import Entidades.Modificador;

public class Principal {
	public static void main(String[] args) {

		D20 teste1 = new D20(3);

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
			System.out.println(teste2.getModificadores().get(i).getNome() + " " + teste2.getModificadores().get(i).getValor());
		}
		System.out.println(teste2.obterValor());
	}

}