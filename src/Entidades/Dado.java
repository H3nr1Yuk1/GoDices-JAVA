package Entidades;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public abstract class Dado {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int faces;
    private int quantidade;
    @ElementCollection
    private List<Integer> rolagens;

    public Dado() {
    }

    public Dado(int faces) {
        this.faces = faces;
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

    public List<Integer> getRolagens() {
        return rolagens;
    }

    public void setRolagens(List<Integer> rolagens) {
        this.rolagens = rolagens;
    }

    public abstract List<Integer> rolarDado();
}
