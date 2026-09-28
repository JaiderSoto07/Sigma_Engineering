package co.edu.co.pizzeriauco.crosscuting.excepciones;

import co.edu.co.pizzeriauco.crosscuting.excepciones.enums.Capa;

public class PizzeriaNegocioExcepcion extends PizzeriaExcepcion {

    private static final long serialVersionUID = -7823416590374126820L;

    private PizzeriaNegocioExcepcion(String mensajeUsuario, String mensajeTecnico, Exception excepcionRaiz) {
        super(Capa.NEGOCIO, mensajeUsuario, mensajeTecnico, excepcionRaiz);
    }

    public static PizzeriaExcepcion crear(String mensajeUsuario) {
        return new PizzeriaNegocioExcepcion(mensajeUsuario, mensajeUsuario,
                new Exception(mensajeUsuario));
    }

    public static PizzeriaExcepcion crear(String mensajeUsuario, String mensajeTecnico) {
        return new PizzeriaNegocioExcepcion(mensajeUsuario, mensajeTecnico,
                new Exception(mensajeTecnico));
    }

    public static PizzeriaExcepcion crear(String mensajeUsuario, String mensajeTecnico, Exception excepcionRaiz) {
        return new PizzeriaNegocioExcepcion(mensajeUsuario, mensajeTecnico,
                excepcionRaiz);
    }

}
