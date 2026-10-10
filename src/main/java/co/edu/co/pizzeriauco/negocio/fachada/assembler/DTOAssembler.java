package co.edu.co.pizzeriauco.negocio.fachada.assembler;

import java.util.List;

public interface DTOAssembler<D, T> {

    T convertirADTO(D dominio);

    D convertirADominio(T dto);

    List<T> convertirADTO(List<D> listaDominios);
}
