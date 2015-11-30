package truco.modelo.envido;

import truco.modelo.Equipo;
import truco.modelo.excepciones.FaltaEnvidoYaCantadoException;

public class  FaltaEnvido extends Envido {

    public FaltaEnvido() {}


    @Override
    public void setEnvidoCantado(Envido envidoCantado){
        throw new FaltaEnvidoYaCantadoException();
    }

    @Override
    public int getPuntos(Equipo equipoGanador,Equipo equipoPerdedor) {
        if(equipoGanador.getPuntaje()<15 && equipoPerdedor.getPuntaje()<15 || equipoGanador.getPuntaje()>15 && equipoPerdedor.getPuntaje()<15)
            return 30 - equipoGanador.getPuntaje();
        return 30 - equipoPerdedor.getPuntaje();
    }
}

