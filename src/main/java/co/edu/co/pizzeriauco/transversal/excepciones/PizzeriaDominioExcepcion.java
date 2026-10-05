package co.edu.co.pizzeriauco.transversal.excepciones;

import co.edu.co.pizzeriauco.transversal.excepciones.enums.Capa;

public class PizzeriaDominioExcepcion extends PizzeriaExcepcion {

    private static final long serialVersionUID = -7823416590374126860L;

    private PizzeriaDominioExcepcion(String mensajeUsuario, String mensajeTecnico, Exception excepcionRaiz) {
        super(Capa.DOMINIO, mensajeUsuario, mensajeTecnico, excepcionRaiz);
    }

    public static PizzeriaExcepcion crear(String mensajeUsuario) {
        return new PizzeriaDominioExcepcion(mensajeUsuario, mensajeUsuario,
                new Exception(mensajeUsuario));
    }

    public static PizzeriaExcepcion crear(String mensajeUsuario, String mensajeTecnico) {
        return new PizzeriaDominioExcepcion(mensajeUsuario, mensajeTecnico,
                new Exception(mensajeTecnico));
    }

    public static PizzeriaExcepcion crear(String mensajeUsuario, String mensajeTecnico, Exception excepcionRaiz) {
        return new PizzeriaDominioExcepcion(mensajeUsuario, mensajeTecnico,
                excepcionRaiz);
    }

}
