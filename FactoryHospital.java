/**
 * Factoría para seleccionar la implementación del sistema del hospital.
 */

public class FactoryHospital {

    /**
     * Retorna una implementación concreta del sistema.
     * @param tipo 1 = VectorHospital con el heap propio, 2 = PriorityHospital con Java.
     * @return
     */

    public static Object getSistema(int tipo) {
        if (tipo == 1) {
            return new VectorHospital(); 
        } else {
            return new PriorityHospital();
        }
    }
}
