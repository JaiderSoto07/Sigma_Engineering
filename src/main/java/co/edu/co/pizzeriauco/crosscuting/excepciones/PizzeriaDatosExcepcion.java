package co.edu.co.pizzeriauco.crosscuting.excepciones;

import co.edu.co.pizzeriauco.crosscuting.excepciones.enums.Capa;

public class PizzeriaDatosExcepcion extends PizzeriaExcepcion {

    private static final long serialVersionUID = -7823416590374126810L;

    private PizzeriaDatosExcepcion(String mensajeUsuario, String mensajeTecnico, Exception excepcionRaiz) {
        super(Capa.DATOS, mensajeUsuario, mensajeTecnico, excepcionRaiz);
    }

    public static PizzeriaExcepcion crear(String mensajeUsuario) {
        return new PizzeriaDatosExcepcion(mensajeUsuario, mensajeUsuario,
                new Exception(mensajeUsuario));
    }

    public static PizzeriaExcepcion crear(String mensajeUsuario, String mensajeTecnico) {
        return new PizzeriaDatosExcepcion(mensajeUsuario, mensajeTecnico,
                new Exception(mensajeTecnico));
    }

    public static PizzeriaExcepcion crear(String mensajeUsuario, String mensajeTecnico, Exception excepcionRaiz) {
        return new PizzeriaDatosExcepcion(mensajeUsuario, mensajeTecnico,
                excepcionRaiz);
    }

}
