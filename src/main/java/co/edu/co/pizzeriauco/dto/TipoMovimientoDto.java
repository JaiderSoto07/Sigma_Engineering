package co.edu.co.pizzeriauco.dto;

import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilId;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilObjeto;

import java.util.UUID;

public class TipoMovimientoDto {

    private UUID id;
    private CategoriaOrigenDto categoriaOrigen;

    public TipoMovimientoDto() {
        setId(id);
        setCategoriaOrigen(new CategoriaOrigenDto());
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = UtilId.valorDefecto(id);
    }

    public CategoriaOrigenDto getCategoriaOrigen() {
        return categoriaOrigen;
    }

    public void setCategoriaOrigen(CategoriaOrigenDto categoriaOrigen) {
        this.categoriaOrigen = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(categoriaOrigen, new CategoriaOrigenDto());
    }
}
