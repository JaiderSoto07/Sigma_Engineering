package co.edu.co.pizzeriauco.crosscuting.utilitario;

import java.util.UUID;
public class UtilId {
       public static final UUID VALOR_DEFECTO = UUID.fromString("00000000-0000-0000-0000-000000000000");

        //que no se pueda crear el objeto id directaente desde otra clase , si no que
        // solamente se puede aqui, para poder hacerle el control y que no sea nula nunca
        private UtilId(){
        }
        //metodo para generar el id
        public static UUID generarId(){
            //aqui on UUID por que quiero que me retorne un objeto de ese tipo
            return UUID.randomUUID();
        }

        //Aqui ya asegure de que el id no sea nulo si no siempre un valor
        public static UUID valorDefecto(UUID id){
            //aqui puedo hacer el retorno con el operador Ternario
            return UtilObjeto.obtenerValorDefectoSiValorOriginalEsNulo(id, VALOR_DEFECTO);
        }

    }

//asegurar que el modelo de dominio quedo finalizado

