import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import java.util.*;

public class VectorHospitalTest {

    private VectorHospital vh;
    private Paciente p1;
    private Paciente p2;
    private Paciente p3;
    private Paciente p4;
    private Paciente p5;

    @Before
    public void setUp() {
        vh = new VectorHospital();
        p1 = new Paciente("Vernel Hernández", "fiebre", 'E');
        p2 = new Paciente("Marta Cáceres", "chikunguya", 'C');
        p3 = new Paciente("Ernesto López", "fractura de mano", 'D');
        p4 = new Paciente("María Pascual", "tuberculosis", 'A');
        p5 = new Paciente("Noe Luna", "varicela", 'B');
    }

    @Test
    public void testAgregarYPrioridad() {
        vh.agregar(p1);
        vh.agregar(p2);
        vh.agregar(p3);
        vh.agregar(p4);
        vh.agregar(p5);

        Paciente esperado = vh.remove();
        assertEquals("Paciente con mayor prioridad debe ser María (A)", p4, esperado);
    }

    @Test
    public void testOrdenCorrecto() {
        vh.agregar(p1);
        vh.agregar(p2);
        vh.agregar(p3);
        vh.agregar(p4);
        vh.agregar(p5);

        assertEquals(p4, vh.remove()); // A
        assertEquals(p5, vh.remove()); // B
        assertEquals(p2, vh.remove()); // C
        assertEquals(p3, vh.remove()); // D
        assertEquals(p1, vh.remove()); // E
    }

    @Test
    public void testIsEmptyInicial() {
        assertTrue(vh.isEmpty());
    }

    @Test
    public void testIsEmptyDespuésDeAgregar() {
        vh.agregar(p1);
        assertFalse(vh.isEmpty());
    }

    @Test
    public void testRemoveVacío() {
        assertNull(vh.remove());
    }

    @Test
    public void testCantidad() {
        vh.agregar(p1);
        vh.agregar(p2);
        assertEquals(2, vh.cantidad());
    }

    @Test
    public void testVerPacientesOrdenados() {
        vh.agregar(p1);
        vh.agregar(p2);
        vh.agregar(p3);
        vh.agregar(p4);
        vh.agregar(p5);

        String resultado = vh.verPacientes();
        assertTrue(resultado.contains("tuberculosis")); // A
        assertTrue(resultado.contains("varicela")); // B
        assertTrue(resultado.contains("chikunguya")); // C
        assertTrue(resultado.contains("fractura de mano")); // D
        assertTrue(resultado.contains("fiebre")); // E

        int indexA = resultado.indexOf("tuberculosis");
        int indexB = resultado.indexOf("varicela");
        int indexC = resultado.indexOf("chikunguya");
        int indexD = resultado.indexOf("fractura de mano");
        int indexE = resultado.indexOf("fiebre");

        assertTrue(indexA < indexB);
        assertTrue(indexB < indexC);
        assertTrue(indexC < indexD);
        assertTrue(indexD < indexE);
    }
}
