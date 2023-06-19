package Entidades;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.persistence.Entity;

@Entity
public class DadoPadrao extends Dado {

	public DadoPadrao() {
	}

	public DadoPadrao(int faces) {
		super(faces);
	}

	public DadoPadrao(int faces, int quantidade) {
		super(faces, quantidade);
	}

	@Override
	public List<Integer> rolarDado() {
		this.setRolagens(new ArrayList<Integer>());
        Random random = new Random();

        if(super.getQuantidade() <= 0){
            int dadosReais = -this.getQuantidade() + 2;
            for(int i = 0; i < dadosReais; i++){
                int valor = random.nextInt(this.getFaces()) + 1;
                this.getRolagens().add(valor);
            }
            return this.getRolagens();
        } else {
            for(int i = 0; i < this.getQuantidade(); i++){
                int valor = random.nextInt(this.getFaces()) + 1;
                this.getRolagens().add(valor);
            }
            return this.getRolagens();
        }
	}

}
