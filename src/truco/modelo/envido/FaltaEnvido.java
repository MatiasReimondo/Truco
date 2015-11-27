package truco.modelo.envido;

import truco.modelo.Equipo;

public class  FaltaEnvido extends Envido {

    public FaltaEnvido() {

    }

    @Override
    public int getPuntos(Equipo equipoGanador,Equipo equipoPerdedor) {
        if(equipoGanador.getPuntaje()<15 && equipoPerdedor.getPuntaje()<15 || equipoGanador.getPuntaje()>15 && equipoPerdedor.getPuntaje()<15)
            return 30 - equipoGanador.getPuntaje();

        return 30 - equipoPerdedor.getPuntaje();
    }
}
