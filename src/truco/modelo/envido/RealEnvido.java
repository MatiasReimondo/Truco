package truco.modelo.envido;

import truco.modelo.Equipo;
import truco.modelo.envido.Envido;


public class RealEnvido extends Envido {

    private int puntos;
    private Envido subEnvido;

    public RealEnvido(){
        this.puntos=3;
    }

    @Override
    public int getPuntos(Equipo equipoGanador,Equipo equipoPerdedor){
        if(subEnvido==null)
            return this.puntos;
        return this.puntos+subEnvido.getPuntos(equipoGanador,equipoPerdedor);
    }
}
