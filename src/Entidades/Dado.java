package Entidades;

import java.util.ArrayList;

public abstract class Dado {
    int faces;
    int quantidade;
    ArrayList<Integer> rolagens;

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
