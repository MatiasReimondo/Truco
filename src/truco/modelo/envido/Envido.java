package truco.modelo.envido;

import truco.modelo.Equipo;
import truco.modelo.excepciones.EnvidoEnvidoYaCantadoException;

public class Envido {

    private Envido subEnvido;
    private final int puntos;

    public Envido(){
        puntos=2;
    }

    public void anidarEnvido(Envido envido){
        if(subEnvido==null)
            subEnvido=envido;
        else if(envido.getClass().equals(Envido.class))
            throw new EnvidoEnvidoYaCantadoException();
        else
            subEnvido.anidarEnvido(envido);
    }

    public int getPuntos(Equipo equipoGanador,Equipo equipoPerdedor){
        if(subEnvido==null)
            return puntos;
        if(subEnvido.getClass().equals(FaltaEnvido.class))
            return subEnvido.getPuntos(equipoGanador,equipoPerdedor);
        return puntos+subEnvido.getPuntos(equipoGanador,equipoPerdedor);
    }
}
