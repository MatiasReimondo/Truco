package truco.modelo.envido;

import truco.modelo.Equipo;

public class EnvidoNoCantado extends Envido{

    private Envido envidoCantado;
    private int puntos=1;

    public EnvidoNoCantado(){}

    @Override
    public void setEnvidoCantado(Envido envido){
        envidoCantado=envido;
    }

    @Override
    public Envido cambiarEnvido(){
        return envidoCantado;
    }

    @Override
    public int getPuntos(Equipo equipoGanador,Equipo equipoPerdedor){
        return puntos;
    }

    @Override
    public Envido getEnvidoCantado() {
        return envidoCantado;
    }
}

