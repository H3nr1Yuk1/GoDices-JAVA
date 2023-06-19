package Entidades;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity
public class DadoDano extends Dado {
	private String tipo;
	
	public DadoDano() {
	}

	public DadoDano(int faces) {
		super(faces);
	}

	public DadoDano(int faces, int quantidade) {
		super(faces, quantidade);
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public List<Integer> rolarDado() {
		this.setRolagens(new ArrayList<Integer>());
        Random random = new Random();

        for(int i = 0; i < this.getQuantidade(); i++){
            int valor = random.nextInt(this.getFaces()) + 1;
            this.getRolagens().add(valor);
        }
        return this.getRolagens();
	}
}
