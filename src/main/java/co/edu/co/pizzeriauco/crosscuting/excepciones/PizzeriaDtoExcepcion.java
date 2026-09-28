package co.edu.co.pizzeriauco.crosscuting.excepciones;

import co.edu.co.pizzeriauco.crosscuting.excepciones.enums.Capa;

public class PizzeriaDtoExcepcion extends PizzeriaExcepcion {

    private static final long serialVersionUID = -7823416590374126850L;

    private PizzeriaDtoExcepcion(String mensajeUsuario, String mensajeTecnico, Exception excepcionRaiz) {
        super(Capa.DTO, mensajeUsuario, mensajeTecnico, excepcionRaiz);
    }

    public static PizzeriaExcepcion crear(String mensajeUsuario) {
        return new PizzeriaDtoExcepcion(mensajeUsuario, mensajeUsuario,
                new Exception(mensajeUsuario));
    }

    public static PizzeriaExcepcion crear(String mensajeUsuario, String mensajeTecnico) {
        return new PizzeriaDtoExcepcion(mensajeUsuario, mensajeTecnico,
                new Exception(mensajeTecnico));
    }

    public static PizzeriaExcepcion crear(String mensajeUsuario, String mensajeTecnico, Exception excepcionRaiz) {
        return new PizzeriaDtoExcepcion(mensajeUsuario, mensajeTecnico,
                excepcionRaiz);
    }

}
