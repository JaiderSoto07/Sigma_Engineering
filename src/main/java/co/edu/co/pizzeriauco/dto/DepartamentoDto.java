package co.edu.co.pizzeriauco.dto;

import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilId;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilObjeto;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilTexto;

import java.util.UUID;

public class DepartamentoDto {

    private UUID id;
    private String nombre;
    private PaisDto pais;

    public DepartamentoDto() {
        setId(id);
        setNombre(UtilTexto.vacia);
        setPais(new PaisDto());
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

    public PaisDto getPais() {
        return pais;
    }

    public void setPais(PaisDto pais) {
        this.pais = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(pais, new PaisDto());
    }
}
