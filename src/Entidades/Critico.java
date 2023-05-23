package Entidades;

public class Critico {
    private int margem;
    private int multiplicador;
	
    public Critico() {
	}
    
    public Critico(int margem, int multiplicador) {
		this.margem = margem;
		this.multiplicador = multiplicador;
	}

	public int getMargem() {
		return margem;
	}

	public void setMargem(int margem) {
		this.margem = margem;
	}

	public int getMultiplicador() {
		return multiplicador;
	}

	public void setMultiplicador(int multiplicador) {
		this.multiplicador = multiplicador;
	}
        
}
