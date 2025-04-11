import java.io.*;
import java.util.*;

/**
 * Clase principal del sistema hospitalario de emergencia.
 */
public class Hospital {

    private static final int LIMITE = 5;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Paciente> pacientesBase = leerArchivo("pacientes.txt");

        if (pacientesBase.isEmpty()) {
            System.out.println("No se pudo cargar ningún paciente desde el archivo.");
            return;
        }

        boolean seguirSistema = true;

        while (seguirSistema) {
            System.out.println("¡Bienvenido!");
            System.out.println("");
            System.out.println("Seleccione la implementación que desea:");
            System.out.println("1. VectorHospital, (Heap personalizado)");
            System.out.println("2. PriorityHospital, (Java Collections)");
            System.out.println("3. Salir");
            System.out.println("");
            System.out.print("Opción: ");
            System.out.println("");
            int op = sc.nextInt(); sc.nextLine();

            if (op == 3) {
                System.out.println("");
                System.out.println("Cerrando el sistema.");
                System.out.println("¡Buen dia! :D.");
                break;
            }

            if (op != 1 && op != 2) {
                System.out.println("¡UPS! Tu opción es inválida.");
                System.out.println("");
                System.out.println("¡Intentalo de nuevo!");
                continue;
            }

            Object sistema = FactoryHospital.getSistema(op);

            if (op == 1) {
                ((VectorHospital) sistema).cargar(new ArrayList<>(pacientesBase));
            } else {
                ((PriorityHospital) sistema).cargar(new ArrayList<>(pacientesBase));
            }

            int eleccion = -1;
            while (eleccion != 4) {
                System.out.println("------------------------------------------ MENÚ HOSPITAL---------------------------------------------");
                System.out.println("1. Ver pacientes");
                System.out.println("2. Agregar paciente");
                System.out.println("3. Atender siguiente paciente");
                System.out.println("4. Volver al selector de implementación");
                System.out.print("Opción: ");
                System.out.print("");
                try {
                    eleccion = Integer.parseInt(sc.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("¡UPS! Tu opción es inválida.");
                    System.out.println("Ingresa un número del 1 al 4.");
                    System.out.println("");
                    continue;
                }

                switch (eleccion) {
                    case 1:
                        boolean vacio = (op == 1)
                                ? ((VectorHospital) sistema).isEmpty()
                                : ((PriorityHospital) sistema).isEmpty();
                        if (vacio) {
                            System.out.println("");
                            System.out.println("No hay pacientesen espera.");
                            System.out.println("La cola está vacía.");
                            System.out.println("");
                        } else {
                            System.out.println("");
                            System.out.print((op == 1)
                                    ? ((VectorHospital) sistema).verPacientes()
                                    : ((PriorityHospital) sistema).verPacientes());
                                    System.out.println("");
                        }
                        break;

                    case 2:
                        int actual = (op == 1)
                                ? ((VectorHospital) sistema).cantidad()
                                : ((PriorityHospital) sistema).cantidad();

                        if (actual >= LIMITE) {
                            System.out.println("");
                            System.out.println("¡UY! Cupo lleno.");
                            System.out.println("¡Pérdon! Pero solo se permiten 5 pacientes en espera.");
                            System.out.println("");
                        } else {
                            System.out.println("");
                            System.out.print("Nombre: ");
                            String nom = sc.nextLine();
                            System.out.print("Síntoma: ");
                            String snt = sc.nextLine();

                            char cod = pedirPrioridadManual(sc);

                            boolean repetido = false;
                            List<Paciente> actuales = (op == 1)
                                    ? ((VectorHospital) sistema).getPacientes()
                                    : ((PriorityHospital) sistema).getPacientes();

                            for (Paciente p : actuales) {
                                if (p.getCod() == cod) {
                                    repetido = true;
                                    break;
                                }
                            }

                            if (repetido) {
                                System.out.println("");
                                System.out.println("Ya hay un paciente con prioridad " + cod + ". No se puede repetir.");
                                System.out.println("¡Intentalo de nuevo!");
                                System.out.println("");
                            } else {
                                Paciente nuevo = new Paciente(nom, snt, cod);
                                if (op == 1)
                                    ((VectorHospital) sistema).agregar(nuevo);
                                else
                                    ((PriorityHospital) sistema).agregar(nuevo);
                            }
                        }
                        break;

                    case 3:
                        boolean estaVacio = (op == 1)
                                ? ((VectorHospital) sistema).isEmpty()
                                : ((PriorityHospital) sistema).isEmpty();
                        if (estaVacio) {
                            System.out.println("");
                            System.out.println("No hay pacientes en espera. La cola está vacía.");
                            System.out.println("");
                        } else {
                            Paciente atendido = (op == 1)
                                    ? ((VectorHospital) sistema).remove()
                                    : ((PriorityHospital) sistema).remove();
                            System.out.println("");
                            System.out.println("Atendido: " + atendido);
                            System.out.println("");
                        }
                        break;

                    case 4:
                    System.out.println("");
                        System.out.println("Volviendo al menú de implementación.");
                        System.out.println("");
                        break;

                    default:
                        System.out.println("¡UPS! Tu opción es inválida.");
                        System.out.println("Ingresa un número del 1 al 4.");
                        System.out.println("");
                }
            }
        }

        System.out.println("Programa finalizado.");
        System.out.println(" ¡Buen trabajo!");
    }

    public static char pedirPrioridadManual(Scanner sc) {
        while (true) {
            System.out.print("Asigna un código de prioridad (A a E): ");
            String entrada = sc.nextLine().toUpperCase().trim();
            if (entrada.length() == 1 && entrada.charAt(0) >= 'A' && entrada.charAt(0) <= 'E') {
                return entrada.charAt(0);
            } else {
                System.out.println("");
                System.out.println("¡UPS! Tu pioridad es inválida.");
                System.out.println("Ingresa una letra entre A y E.");
                System.out.println("");
            }
        }
    }

    public static List<Paciente> leerArchivo(String ruta) {
        List<Paciente> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 3) {
                    String nom = partes[0].trim();
                    String snt = partes[1].trim();
                    char cod = partes[2].trim().toUpperCase().charAt(0);
                    lista.add(new Paciente(nom, snt, cod));
                }
            }
        } catch (IOException e) {
            System.out.println("¡Ha ocurrido un error al leer el archivo: " + e.getMessage());
            System.out.println("Intentalo de nuevo");
            System.out.println("");
        }
        return lista;
    }
}
