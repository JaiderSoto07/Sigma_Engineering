package co.edu.co.pizzeriauco.crosscuting.excepciones;

import co.edu.co.pizzeriauco.crosscuting.excepciones.enums.Capa;

public class PizzeriaControladorExcepcion extends PizzeriaExcepcion {

    private static final long serialVersionUID = -7823416590374126830L;

    private PizzeriaControladorExcepcion(String mensajeUsuario, String mensajeTecnico, Exception excepcionRaiz) {
        super(Capa.CONTROLADOR, mensajeUsuario, mensajeTecnico, excepcionRaiz);
    }

    public static PizzeriaExcepcion crear(String mensajeUsuario) {
        return new PizzeriaControladorExcepcion(mensajeUsuario, mensajeUsuario,
                new Exception(mensajeUsuario));
    }

    public static PizzeriaExcepcion crear(String mensajeUsuario, String mensajeTecnico) {
        return new PizzeriaControladorExcepcion(mensajeUsuario, mensajeTecnico,
                new Exception(mensajeTecnico));
    }

    public static PizzeriaExcepcion crear(String mensajeUsuario, String mensajeTecnico, Exception excepcionRaiz) {
        return new PizzeriaControladorExcepcion(mensajeUsuario, mensajeTecnico,
                excepcionRaiz);
    }

}
