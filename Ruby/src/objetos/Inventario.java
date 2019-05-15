package objetos;

import java.util.ArrayList;
import objetos.plantas.Planta;
import objetos.pociones.Pocion;
import objetos.semillas.Semilla;

public class Inventario {

    private ArrayList<Planta> plantas;
    private ArrayList<Semilla> semillas;
    private ArrayList<Pocion> pociones;

    public Inventario() {
        plantas = new ArrayList<>();
        semillas = new ArrayList<>();
        pociones = new ArrayList<>();
    }

    public void anadirObjeto(Objeto objeto, int i) {
        boolean anadir = true;
        Objeto objeto_temp = null;
        int j = 0;

        if (i > 0) {    //Si se añade al menos un elemento
            if (objeto.tipoObjeto().equals("Planta")) {
                for (Planta objeto_inventario : plantas) {
                    if (objeto_inventario.equals(objeto)) {
                        anadir = false;
                        objeto_temp = objeto_inventario;
                        break;
                    }
                    j++;
                }
                if (!anadir) {
                    objeto_temp.aumentarCantidad(i);
                    plantas.set(j, (Planta) objeto_temp);
                } else {
                    objeto_temp = objeto;
                    objeto_temp.setCantidad(i);
                    plantas.add((Planta) objeto_temp);
                }
            } else if (objeto.tipoObjeto().endsWith("Semilla")) {
                for (Semilla objeto_inventario : semillas) {
                    if (objeto_inventario.equals(objeto)) {
                        anadir = false;
                        objeto_temp = objeto_inventario;
                        break;
                    }
                    j++;
                }
                if (!anadir) {
                    objeto_temp.aumentarCantidad(i);
                    semillas.set(j, (Semilla) objeto_temp);
                } else {
                    objeto_temp = objeto;
                    objeto_temp.setCantidad(i);
                    semillas.add((Semilla) objeto_temp);
                }
            } else if (objeto.tipoObjeto().equals("Pocion")) {
                for (Pocion objeto_inventario : pociones) {
                    if (objeto_inventario.equals(objeto)) {
                        anadir = false;
                        objeto_temp = objeto_inventario;
                        break;
                    }
                    j++;
                }
                if (!anadir) {
                    objeto_temp.aumentarCantidad(i);
                    pociones.set(j, (Pocion) objeto_temp);
                } else {
                    objeto_temp = objeto;
                    objeto_temp.setCantidad(i);
                    pociones.add((Pocion) objeto_temp);
                }
            } else {
                System.out.println("Error al añadir un objeto al inventario");
            }
        }
    }

    /**
     * Elimina uno de cantidad del objeto introducido
     *
     * @param objeto
     * @return Un boolean que indica si ese objeto se encontraba en el
     * inventario
     */
    public boolean eliminarObjeto(Objeto objeto) {
        boolean esta = false;
        Objeto objeto_temp = null;
        int j = 0;
        if (objeto.tipoObjeto().equals("Planta")) {
            for (Objeto objeto_inventario : plantas) {
                if (objeto_inventario.equals(objeto)) {
                    esta = true;
                    break;
                }
                j++;
            }
            if (esta) {
                objeto_temp = plantas.get(j);
                objeto_temp.reducirCantidad(1);
                plantas.set(j, (Planta) objeto_temp);
                if (objeto_temp.getCantidad() == 0) {
                    plantas.remove(j);
                }
            }
        } else if (objeto.tipoObjeto().equals("Semilla")) {
            for (Objeto objeto_inventario : semillas) {
                if (objeto_inventario.equals(objeto)) {
                    esta = true;
                    break;
                }
                j++;
            }
            if (esta) {
                objeto_temp = semillas.get(j);
                objeto_temp.reducirCantidad(1);
                semillas.set(j, (Semilla) objeto_temp);
                if (objeto_temp.getCantidad() == 0) {
                    semillas.remove(j);
                }
            }
        } else if (objeto.tipoObjeto().equals("Pocion")) {
            for (Objeto objeto_inventario : pociones) {
                if (objeto_inventario.equals(objeto)) {
                    esta = true;
                    break;
                }
                j++;
            }
            if (esta) {
                objeto_temp = pociones.get(j);
                objeto_temp.reducirCantidad(1);
                pociones.set(j, (Pocion) objeto_temp);
                if (objeto_temp.getCantidad() == 0) {
                    pociones.remove(j);
                }
            }
        } else {
            System.out.println("Error al eliminar un objeto del inventario");
        }
        return esta;
    }

    public ArrayList<Planta> getPlantas() {
        return plantas;
    }

    public ArrayList<Semilla> getSemillas() {
        return semillas;
    }

    public ArrayList<Pocion> getPociones() {
        return pociones;
    }

    public int getCantidad(Objeto objeto) {
        int cantidad = 0;
        if (objeto.tipoObjeto().equals("Planta")) {
            for (Planta planta : plantas) {
                if (objeto.equals(planta)) {
                    cantidad = planta.getCantidad();
                    break;
                }
            }
        } else if (objeto.tipoObjeto().equals("Semilla")) {
            for (Semilla semilla : semillas) {
                if (objeto.equals(semilla)) {
                    cantidad = semilla.getCantidad();
                    break;
                }
            }
        } else if (objeto.tipoObjeto().equals("Pocion")) {
            for (Pocion pocion : pociones) {
                if (objeto.equals(pocion)) {
                    cantidad = pocion.getCantidad();
                    break;
                }
            }
        } else {
            System.out.println("Error al obtener la cantidad de un objeto");
        }
        return cantidad;
    }
}
