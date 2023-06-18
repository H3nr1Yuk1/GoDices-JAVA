package Entidades;

import java.util.ArrayList;
import java.util.Random;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public abstract class Dado {
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

    public abstract ArrayList<Integer> rolarDado();
}
