package truco.modelo.envido;

import truco.modelo.Equipo;
import truco.modelo.excepciones.FaltaEnvidoYaCantadoException;
import truco.modelo.excepciones.SoloSePuedeCantarFaltaEnvidoDespuesDeRealEnvidoException;


public class RealEnvido extends Envido {

    private final int puntos;
    private Envido subEnvido;

    public RealEnvido(){
        this.puntos=3;
    }

    @Override
    public void anidarEnvido(Envido envido){
       if(!envido.getClass().equals(FaltaEnvido.class))
            throw new SoloSePuedeCantarFaltaEnvidoDespuesDeRealEnvidoException();
        if(subEnvido!=null)
            throw new FaltaEnvidoYaCantadoException();
        subEnvido=envido;
    }

    @Override
    public int getPuntos(Equipo equipoGanador,Equipo equipoPerdedor){
        if(subEnvido==null)
            return this.puntos;
        return subEnvido.getPuntos(equipoGanador,equipoPerdedor);
    }
}
