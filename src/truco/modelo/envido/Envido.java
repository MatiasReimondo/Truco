package truco.modelo.envido;

import truco.modelo.Equipo;
import truco.modelo.excepciones.EnvidoEnvidoYaCantadoException;

public class Envido {

    private Envido envidoAnidado;
    private Envido envidoCantado;
    private int puntos=2;

    public Envido(){}

    public Envido cambiarEnvido(){
        envidoCantado.acumularEnvido(this);
        return envidoCantado;
    }
    public void setEnvidoCantado(Envido envido){
        if(envido.getClass().equals(Envido.class) && envidoAnidado!=null && envidoAnidado.getClass().equals(Envido.class))
            throw new EnvidoEnvidoYaCantadoException();
        envidoCantado=envido;
    }

    public void acumularEnvido(Envido envido) {
        envidoAnidado=envido;
    }

    public int getPuntos(Equipo equipoGanador,Equipo equipoPerdedor){
        if(this.noHayMas(this))
            return puntos;
        return puntos+envidoAnidado.getPuntos(equipoGanador,equipoPerdedor);
    }

    public boolean noHayMas(Envido envido){
        return envidoAnidado==null;
    }

    public Envido getEnvidoCantado() {
        return envidoCantado;
    }
}