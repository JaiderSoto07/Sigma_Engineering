package co.edu.co.pizzeriauco.dto;

import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilId;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilObjeto;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilTexto;

import java.util.UUID;

public class ProductoDto {

    private UUID id;
    private String nombre;
    private HistoricoPrecioDto historicoPrecio;

    public ProductoDto() {
        setId(id);
        setNombre(UtilTexto.vacia);
        setHistoricoPrecio(new HistoricoPrecioDto());
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = UtilId.valorDefecto(id);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = UtilTexto.getUtilTexto().quitarEspaciosEnBlanco(nombre);
    }

    public HistoricoPrecioDto getHistoricoPrecio() {
        return historicoPrecio;
    }

    public void setHistoricoPrecio(HistoricoPrecioDto historicoPrecio) {
        this.historicoPrecio = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(historicoPrecio, new HistoricoPrecioDto());
    }
}
