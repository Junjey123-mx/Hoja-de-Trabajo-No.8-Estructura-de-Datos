import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

/**
 * Implementación propia de heap binario mínimo con Vector.
 */
public class VectorHospital {

    private Vector<Paciente> data;

    /**
     * Constructor:
     */
    public VectorHospital() {
        data = new Vector<>();
    }

    /**
     * Carga pacientes desde una lista inicial.
     * @param cargado
     */
    public void cargar(List<Paciente> cargado) {
        for (Paciente p : cargado) {
            agregar(p);
        }
    }

    /**
     * Agrega un paciente y lo acomoda en el heap.
     * @param agregar
     */
    public void agregar(Paciente agregar) {
        data.add(agregar);
        colaUp(data.size() - 1);
    }

    /**
     * Atiende al paciente con mayor prioridad.
     * @return
     */
    public Paciente remove() {
        if (isEmpty()) return null;

        Paciente min = data.get(0);
        Paciente last = data.remove(data.size() - 1);
        if (!isEmpty()) {
            data.set(0, last);
            pushDownRoot(0);
        }
        return min;
    }

    /**
     * Mostrar todos los pacientes actuales.
     * @return
     */
    public String verPacientes() {
        List<Paciente> copia = new ArrayList<>(data);
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
    public boolean isEmpty() {
        return data.isEmpty();
    }

    /**
     * Retorna la cantidad actual de pacientes en espera.
     * @return número de pacientes en la cola
     */
    public int cantidad() {
        return data.size();
    }

    /**
     * Empuja hacia "arriba" el nodo recién insertado.
     */
    private void colaUp(int leaf) {
        int parent = (leaf - 1) / 2;
        Paciente value = data.get(leaf);
        while (leaf > 0 && value.compareTo(data.get(parent)) < 0) {
            data.set(leaf, data.get(parent));
            leaf = parent;
            parent = (leaf - 1) / 2;
        }
        data.set(leaf, value);
    }

    /**
     * Reorganización desde la raíz hacia abajo.
     */
    private void pushDownRoot(int root) {
        int heapSize = data.size();
        Paciente value = data.get(root);
        while (root < heapSize) {
            int child = 2 * root + 1;
            if (child >= heapSize) break;
            int rightChild = child + 1;
            if (rightChild < heapSize &&
                data.get(rightChild).compareTo(data.get(child)) < 0) {
                child = rightChild;
            }
            if (data.get(child).compareTo(value) < 0) {
                data.set(root, data.get(child));
                root = child;
            } else {
                break;
            }
        }
        data.set(root, value);
    }

    public List<Paciente> getPacientes() {
        return new ArrayList<>(data);
    }
    
}
