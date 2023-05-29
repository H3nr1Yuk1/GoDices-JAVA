package Entidades;

import java.util.ArrayList;
import java.util.Random;

public class D12 extends Dado {

	public D12() { 
		this.faces = 12;
	}

	public D12(int quantidade) {
		super(quantidade);
		this.faces = 12;
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
