package Entidades;

import java.util.ArrayList;
import java.util.Random;

public class D20 extends Dado {
    
    public D20(){
        this.faces = 20;
    }

    public D20(int quantidade){
        this.quantidade = quantidade;
        this.faces = 20;
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
