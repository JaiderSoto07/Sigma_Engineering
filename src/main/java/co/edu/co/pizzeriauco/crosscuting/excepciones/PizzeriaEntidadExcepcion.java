package co.edu.co.pizzeriauco.crosscuting.excepciones;

import co.edu.co.pizzeriauco.crosscuting.excepciones.enums.Capa;

public class PizzeriaEntidadExcepcion extends PizzeriaExcepcion {

    private static final long serialVersionUID = -7823416590374126840L;

    private PizzeriaEntidadExcepcion(String mensajeUsuario, String mensajeTecnico, Exception excepcionRaiz) {
        super(Capa.ENTIDAD, mensajeUsuario, mensajeTecnico, excepcionRaiz);
    }

    public static PizzeriaExcepcion crear(String mensajeUsuario) {
        return new PizzeriaEntidadExcepcion(mensajeUsuario, mensajeUsuario,
                new Exception(mensajeUsuario));
    }

    public static PizzeriaExcepcion crear(String mensajeUsuario, String mensajeTecnico) {
        return new PizzeriaEntidadExcepcion(mensajeUsuario, mensajeTecnico,
                new Exception(mensajeTecnico));
    }

    public static PizzeriaExcepcion crear(String mensajeUsuario, String mensajeTecnico, Exception excepcionRaiz) {
        return new PizzeriaEntidadExcepcion(mensajeUsuario, mensajeTecnico,
                excepcionRaiz);
    }

}
