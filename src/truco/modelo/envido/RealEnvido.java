package truco.modelo.envido;

import truco.modelo.Equipo;
import truco.modelo.excepciones.SoloSePuedeCantarFaltaEnvidoDespuesDeRealEnvidoException;


public class RealEnvido extends Envido {

    private final int puntos=3;
    private Envido envidoAnidado;
    private Envido envidoCantado;

    public RealEnvido(){}

    @Override
    public Envido cambiarEnvido(){
        envidoCantado.acumularEnvido(this);
        return envidoCantado;
    }

    @Override
    public void setEnvidoCantado(Envido envido){
        if(!envido.getClass().equals(FaltaEnvido.class))
            throw new SoloSePuedeCantarFaltaEnvidoDespuesDeRealEnvidoException();
        envidoCantado=envido;
    }

    @Override
    public void acumularEnvido(Envido envido) {
        envidoAnidado=envido;
    }

    @Override
    public int getPuntos(Equipo equipoGanador,Equipo equipoPerdedor){
        if(this.noHayMas(this))
            return puntos;
        return puntos+envidoAnidado.getPuntos(equipoGanador,equipoPerdedor);
    }

    @Override
    public boolean noHayMas(Envido envido){
        return envidoAnidado==null;
    }

    @Override
    public Envido getEnvidoCantado() {
        return envidoCantado;
    }
}
