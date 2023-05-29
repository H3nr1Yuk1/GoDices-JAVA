package Entidades;

import java.util.ArrayList;
import java.util.Random;

public class D4 extends Dado {

	public D4() { 
		this.faces = 4;
	}

	public D4(int quantidade) {
		super(quantidade);
		this.faces = 4;
	}
	
	public ArrayList<Integer> rolarDado() {
		this.rolagens = new ArrayList<Integer>();
        Random random = new Random();

        for(int i = 0; i < this.quantidade; i++){
            int valor = random.nextInt(this.faces) + 1;
            this.rolagens.add(valor);
        }
        return this.rolagens;
	}


}
