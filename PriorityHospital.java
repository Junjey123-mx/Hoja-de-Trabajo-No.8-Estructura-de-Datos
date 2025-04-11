import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class PriorityHospital{

    private PriorityQueue<Paciente> cola;

    /**
     * Constructor:
     */

    public PriorityHospital(){
        cola = new PriorityQueue<>();
    }

    /**
     * Carga pacientes desde una lista inicial.
     * @param cargado
     */

    public void cargar(List<Paciente> cargado) {
        for (Paciente p : cargado){
            agregar(p);
        }
    }

    /**
     * Agrega un paciente a la cola.
     * @param agregar 
     */

    public void agregar(Paciente agregar) {
        cola.add(agregar);
    }

    /**
     * Atiende al paciente con mayor prioridad (menor código).
     * @return 
     */

    public Paciente remove() {
        if (!cola.isEmpty()) {
            return cola.poll(); // poll() = remove() pero retorna null si está vacía
        } else {
            return null;
        }
    }

    /**
     * Muestra todos los pacientes en la cola pero no ordenados visualmente.
     * @return
     */

    public String verPacientes(){
        List<Paciente> copia = new ArrayList<>(cola);
        copia.sort(Comparator.naturalOrder());

        StringBuilder sb = new StringBuilder();
        for (Paciente p : copia) {
            sb.append(p).append("\n");
        }
        return sb.toString();
    }


    /**
     * Verifica si hay pacientes en espera.
     * @return
     */

    public boolean isEmpty(){
        return cola.isEmpty();
    }

    public int cantidad() {
        return cola.size();
    }
    
    public List<Paciente> getPacientes() {
        return new ArrayList<>(cola);
    }
    
}

