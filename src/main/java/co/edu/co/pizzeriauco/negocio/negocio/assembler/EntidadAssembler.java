package co.edu.co.pizzeriauco.negocio.negocio.assembler;

public interface EntidadAssembler<D, E> {

    E convertirAEntidad(D dominio);

    D convertirADominio(E entidad);
}
