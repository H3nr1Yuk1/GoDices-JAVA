package Entidades;

import java.util.ArrayList;
import java.util.Random;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Dado {
	private int faces;
	@Id
    @Column(unique = true)
    private int quantidade;
    private ArrayList<Integer> rolagens;

    public Dado() {
    }

    public Dado(int quantidade) {
        this.quantidade = quantidade;
    }
    
    public Dado(int faces, int quantidade) {
        this.faces = faces;
        this.quantidade = quantidade;
    }

    public int getFaces() {
        return faces;
    }
    
    public void setFaces(int faces) {
        this.faces = faces;
    }
    
    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public ArrayList<Integer> getRolagens() {
        return rolagens;
    }

    public void setRolagens(ArrayList<Integer> rolagens) {
        this.rolagens = rolagens;
    }

    public ArrayList<Integer> rolarDado(){
    	this.rolagens = new ArrayList<Integer>();
        Random random = new Random();

        if(this.quantidade <= 0){
            int dadosReais = -quantidade + 2;
            for(int i = 0; i < dadosReais; i++){
                int valor = random.nextInt(this.faces) + 1;
                this.rolagens.add(valor);
            }
            return rolagens;
        } else {
            for(int i = 0; i < this.quantidade; i++){
                int valor = random.nextInt(this.faces) + 1;
                this.rolagens.add(valor);
            }
            return this.rolagens;
        }	
    }

}
