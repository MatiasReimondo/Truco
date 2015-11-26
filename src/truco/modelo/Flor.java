package truco.modelo;

public class Flor {

    private int puntos;

    public Flor(){
        puntos=3;
    }

    public void contraflor(){
        puntos=6;
    }

    public void contraflorAlResto(){
        puntos=30;
    }

    public int getPuntos(){
            return puntos;
    }

}
