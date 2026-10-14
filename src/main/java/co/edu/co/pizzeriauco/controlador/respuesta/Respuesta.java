package co.edu.co.pizzeriauco.controlador.respuesta;

import co.edu.co.pizzeriauco.transversal.utilitario.UtilFecha;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilId;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilObjeto;
import co.edu.co.pizzeriauco.transversal.utilitario.UtilTexto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

class Respuesta<D> {

    private String correlationId;
    private LocalDateTime timestamp;
    private List<D> datos;
    private List<String> mensajes;
    private EstadoRespuesta estado;

    protected Respuesta(List<D> datos, List<String> mensajes,EstadoRespuesta estado){
        setMensajes(mensajes);
        setDatos(datos);
        setEstado(estado);
        timestamp = UtilFecha.fechaActual();
        correlationId = UtilId.generarIdComoTexto();
    }

    public void setDatos(List<D> datos) {
        this.datos = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(datos, new ArrayList<D>());
    }

    public void setMensajes(List<String> mensajes) {
        this.mensajes = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(mensajes, new ArrayList<String>());

    }

    public void setEstado(EstadoRespuesta estado) {
        this.estado = UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(estado, EstadoRespuesta.ERROR);
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public List<D> getDatos() {
        return datos;
    }

    public List<String> getMensajes() {
        return mensajes;
    }

    public EstadoRespuesta getEstado() {
        return estado;
    }
}
