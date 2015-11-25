package truco.modelo.envido;

import truco.modelo.Equipo;

public class Envido {

    private Envido subEnvido;
    private int puntos;

    public Envido(){
        puntos=2;
    }

    public void anidarEnvido(Envido envido){
        if(subEnvido==null)
            subEnvido=envido;
        else
            subEnvido.anidarEnvido(envido);
    }

    public void subirApuesta(Envido envido){
        subEnvido=envido;
    }

    public int getPuntos(Equipo equipoGanador,Equipo equipoPerdedor){
        if(subEnvido==null)
            return puntos;
        return puntos+subEnvido.getPuntos(equipoGanador,equipoPerdedor);
    }
}
