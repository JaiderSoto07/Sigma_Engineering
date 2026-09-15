package co.edu.co.pizzeriauco.dto;

import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilId;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilObjeto;
import co.edu.co.pizzeriauco.crosscuting.utilitario.UtilTexto;

import java.util.UUID;

public class CiudadDto {

    private UUID id;
    private String nombre;
    private DepartamentoDto departamento;

    public CiudadDto() {
        setId(id);
        setNombre(UtilTexto.vacia);
        setDepartamento(new DepartamentoDto());
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

    public DepartamentoDto getDepartamento() {
        return departamento;
    }

    public void setDepartamento(DepartamentoDto departamento) {
        this.departamento = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(departamento, new DepartamentoDto());
    }
}
