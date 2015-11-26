package truco.modelo.envido;

import truco.modelo.Equipo;

public class EnvidoNoCantado extends Envido{
    private Envido subEnvido;
    private int puntos;

    public EnvidoNoCantado(){
        puntos=1;
    }

    public void anidarEnvido(Envido envido){
        if(subEnvido==null)
            subEnvido=envido;
        else
            subEnvido.anidarEnvido(envido);
    }

    @Override
    public int getPuntos(Equipo equipoGanador,Equipo equipoPerdedor){
        if(subEnvido==null)
            return puntos;
        return subEnvido.getPuntos(equipoGanador,equipoPerdedor);
    }
}

