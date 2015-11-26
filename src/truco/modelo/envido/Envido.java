package truco.modelo.envido;

import truco.modelo.Equipo;
import truco.modelo.excepciones.NoSePuedeCantarMasDeDosVecesEnvidoException;

public class Envido {

    private Envido subEnvido;
    private int puntos;

    public Envido(){
        puntos=2;
    }

    public void anidarEnvido(Envido envido){
        if(subEnvido==null)
            subEnvido=envido;
        else if(envido.getClass().equals(Envido.class))
            throw new NoSePuedeCantarMasDeDosVecesEnvidoException();
        else
            subEnvido.anidarEnvido(envido);
    }

    public int getPuntos(Equipo equipoGanador,Equipo equipoPerdedor){
        if(subEnvido==null)
            return puntos;
        return puntos+subEnvido.getPuntos(equipoGanador,equipoPerdedor);
    }
}
