package truco.modelo.envido;

import truco.modelo.Equipo;

public class FaltaEnvido extends Envido {

    private int ptsEquipo1;
    private int ptsEquipo2;

    public FaltaEnvido(int ptsEquipo1, int ptsEquipo2) {
        this.ptsEquipo1=ptsEquipo1;
        this.ptsEquipo2=ptsEquipo2;
    }

    @Override
    public int getPuntos(Equipo equipoGanador,Equipo equipoPerdedor) {
        if(equipoGanador.getPuntaje()<15 && equipoPerdedor.getPuntaje()<15 || equipoGanador.getPuntaje()>15 && equipoPerdedor.getPuntaje()<15)
            return 30-equipoGanador.getPuntaje();
        if(equipoGanador.getPuntaje()<15 && equipoPerdedor.getPuntaje()>15 || equipoGanador.getPuntaje()>15 && equipoPerdedor.getPuntaje()>15)
            return 30-equipoPerdedor.getPuntaje();
        return 0;
    }
}
