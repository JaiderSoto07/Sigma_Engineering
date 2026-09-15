package co.edu.co.pizzeriauco.dao.datos;

public interface ActualizarDAO<E, Id> {

    void actualizar(Id id, E entidad);
}
