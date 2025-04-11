/**
 * Es un paciente en el hospital.
 * Implementa "Comparable" para ordenar de A a E, esto según la gravedad de su enermedad.
 */

public class Paciente implements Comparable<Paciente> {
    private String nom;    // Nombre del paciente
    private String snt;    // Síntoma del paciente
    private char cod;      // Prioridad de códgio A-E.

    /**
     * Constructor para crear un nuevo paciente.
     * @param nom 
     * @param snt 
     * @param cod 
     */


    public Paciente(String nom, String snt, char cod){
        this.nom = nom;
        this.snt = snt;
        this.cod = cod;
    }

    public String getNom(){
        return nom;
    }

    public String getSnt(){
        return snt;
    }

    public char getCod(){
        return cod;
    }

    @Override
    public int compareTo(Paciente o){
        return Character.compare(this.cod, o.cod);
    }

    @Override
    public String toString() {
        return nom + " - " + snt + " - " + cod;
    }
}

