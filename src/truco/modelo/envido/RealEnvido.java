package truco.modelo.envido;

import truco.modelo.Equipo;
import truco.modelo.envido.Envido;
import truco.modelo.excepciones.NoSePuedeCantarEnvidoDespuesDeRealEnvidoException;


public class RealEnvido extends Envido {

    private final int puntos;
    private Envido subEnvido;

    public RealEnvido(){
        this.puntos=3;
    }

    @Override
    public void anidarEnvido(Envido envido){

       if(envido.getClass().equals(Envido.class))
            throw new NoSePuedeCantarEnvidoDespuesDeRealEnvidoException();
       else if(subEnvido==null)
            subEnvido=envido;
        else
            subEnvido.anidarEnvido(envido);
    }

    @Override
    public int getPuntos(Equipo equipoGanador,Equipo equipoPerdedor){
        if(subEnvido==null)
            return this.puntos;
        return this.puntos+subEnvido.getPuntos(equipoGanador,equipoPerdedor);
    }
}
