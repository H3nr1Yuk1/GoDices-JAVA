package Entidades;

import java.util.ArrayList;
import java.util.Random;

public class D3 extends Dado {

	public D3() { 
		this.faces = 3;
	}

	public D3(int quantidade) {
		super(quantidade);
		this.faces = 3;
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
