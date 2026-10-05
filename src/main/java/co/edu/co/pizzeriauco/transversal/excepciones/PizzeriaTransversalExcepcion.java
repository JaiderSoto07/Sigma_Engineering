package co.edu.co.pizzeriauco.transversal.excepciones;

import co.edu.co.pizzeriauco.transversal.excepciones.enums.Capa;

public class PizzeriaTransversalExcepcion extends PizzeriaExcepcion {

    private static final long serialVersionUID = -7823416590374126845L;

    private PizzeriaTransversalExcepcion(String mensajeUsuario, String mensajeTecnico, Exception excepcionRaiz) {
        super(Capa.TRANSVERSAL, mensajeUsuario, mensajeTecnico, excepcionRaiz);
    }

    public static PizzeriaExcepcion crear(String mensajeUsuario) {
        return new PizzeriaTransversalExcepcion(mensajeUsuario, mensajeUsuario,
                new Exception(mensajeUsuario));
    }

    public static PizzeriaExcepcion crear(String mensajeUsuario, String mensajeTecnico) {
        return new PizzeriaTransversalExcepcion(mensajeUsuario, mensajeTecnico,
                new Exception(mensajeTecnico));
    }

    public static PizzeriaExcepcion crear(String mensajeUsuario, String mensajeTecnico, Exception excepcionRaiz) {
        return new PizzeriaTransversalExcepcion(mensajeUsuario, mensajeTecnico,
                excepcionRaiz);
    }

}
