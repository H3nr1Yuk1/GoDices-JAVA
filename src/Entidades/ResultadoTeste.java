package Entidades;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//@Entity
public class ResultadoTeste extends Resultado {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
    public ResultadoTeste(){
        this.tipo = "Teste";
    }

    public ResultadoTeste(int valorEscolhido, ArrayList<Modificador> modificadores){
        super("Teste", valorEscolhido, modificadores);
    }

    public ArrayList<Modificador> getModificadores() {
        return super.getModificadores();
    }

    public String getTipo() {
        return super.getTipo();
    }

    public int getValorEscolhido() {
        return super.getValorEscolhido();
    }

    public void setModificadores(ArrayList<Modificador> modificadores) {
    }

    public void setTipo(String tipo) {
        super.setTipo(tipo);
    }

    public void setValorEscolhido(int valorEscolhido) {
        super.setValorEscolhido(valorEscolhido);
    }
    
    public int gerarResultado() {
        int valor = 0;
        valor = valor + this.valorEscolhido;
        for(int i = 0; i < this.modificadores.size(); i++){
            valor = valor + modificadores.get(i).getValor();
        }
        return valor;
    }
}
