package co.edu.co.pizzeriauco.transversal.excepciones;

import co.edu.co.pizzeriauco.transversal.excepciones.enums.Capa;

public class PizzeriaFachadaExcepcion extends PizzeriaExcepcion {

    private static final long serialVersionUID = -7823416590374126830L;

    private PizzeriaFachadaExcepcion(String mensajeUsuario, String mensajeTecnico, Exception excepcionRaiz) {
        super(Capa.FACHADA, mensajeUsuario, mensajeTecnico, excepcionRaiz);
    }

    public static PizzeriaExcepcion crear(String mensajeUsuario) {
        return new PizzeriaFachadaExcepcion(mensajeUsuario, mensajeUsuario,
                new Exception(mensajeUsuario));
    }

    public static PizzeriaExcepcion crear(String mensajeUsuario, String mensajeTecnico) {
        return new PizzeriaFachadaExcepcion(mensajeUsuario, mensajeTecnico,
                new Exception(mensajeTecnico));
    }

    public static PizzeriaExcepcion crear(String mensajeUsuario, String mensajeTecnico, Exception excepcionRaiz) {
        return new PizzeriaFachadaExcepcion(mensajeUsuario, mensajeTecnico,
                excepcionRaiz);
    }

}
