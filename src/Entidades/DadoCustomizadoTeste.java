package Entidades;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DadoCustomizadoTeste extends DadoCustomizado {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
    private ResultadoTeste resultado;

    public DadoCustomizadoTeste(){
        this.tipo = "Teste";
    }

    public DadoCustomizadoTeste(String nome, D20 teste, ArrayList<Modificador> modificadores){
        super("Teste", nome, teste, modificadores);
    }

    public ResultadoTeste getResultado() {
        return resultado;
    }

    public int obterValor(){
        return this.resultado.gerarResultado();
    }

    public int rolarDado() {
        if(this.teste.getQuantidade() <= 0){
            int escolhido = 20;
            this.teste.rolarDado();
            for(int i = 0; i < this.teste.getRolagens().size(); i++){
                if(this.teste.getRolagens().get(i) < escolhido){
                    escolhido = this.teste.getRolagens().get(i);
                }
            }
            this.resultado = new ResultadoTeste(escolhido, this.modificadores);
            return this.resultado.gerarResultado();
        } else {
            int escolhido = 0;
            this.teste.rolarDado();
            for(int i = 0; i < this.teste.getRolagens().size(); i++){
                if(this.teste.getRolagens().get(i) > escolhido){
                    escolhido = this.teste.getRolagens().get(i);
                }
            }
            this.resultado = new ResultadoTeste(escolhido, this.modificadores);
            return this.resultado.gerarResultado();
        }
    }

}
