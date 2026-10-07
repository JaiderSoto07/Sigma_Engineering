package co.edu.co.pizzeriauco.negocio.negocio.reglas;

public interface Rule<O> {

    void ejecutar(O... datos);
}
