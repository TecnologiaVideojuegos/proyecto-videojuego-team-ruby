package objetos;

import java.util.ArrayList;

public class Inventario {
    private ArrayList<Objeto> plantas;
    private ArrayList<Objeto> semillas;
    private ArrayList<Objeto> pociones;

    public Inventario() {
        plantas = new ArrayList<>();
        semillas = new ArrayList<>();
        pociones = new ArrayList<>();
    }

    public void anadirObjeto(Objeto objeto, int i) {
        boolean anadir = true;
        Objeto objeto_temp = null;
        int j = 0;

        if (objeto.tipoObjeto().equals("Planta")) {
            for (Objeto objeto_inventario : plantas) {
                if (objeto_inventario.equals(objeto)) {
                    anadir = false;
                    objeto_temp = objeto_inventario;
                    break;
                }
                j++;
            }
            if (!anadir) {
                objeto_temp.aumentarCantidad(i);
                plantas.set(j, objeto_temp);
            } else {
                objeto_temp = objeto;
                objeto_temp.setCantidad(i);
                plantas.add(objeto_temp);
            }
        } else if (objeto.tipoObjeto().endsWith("Semilla")) {
            for (Objeto objeto_inventario : semillas) {
                if (objeto_inventario.equals(objeto)) {
                    anadir = false;
                    objeto_temp = objeto_inventario;
                    break;
                }
                j++;
            }
            if (!anadir) {
                objeto_temp.aumentarCantidad(i);
                semillas.set(j, objeto_temp);
            } else {
                objeto_temp = objeto;
                objeto_temp.setCantidad(i);
                semillas.add(objeto_temp);
            }
        } else if (objeto.tipoObjeto().equals("Pocion")) {
            for (Objeto objeto_inventario : pociones) {
                if (objeto_inventario.equals(objeto)) {
                    anadir = false;
                    objeto_temp = objeto_inventario;
                    break;
                }
                j++;
            }
            if (!anadir) {
                objeto_temp.aumentarCantidad(i);
                pociones.set(j, objeto_temp);
            } else {
                objeto_temp = objeto;
                objeto_temp.setCantidad(i);
                pociones.add(objeto_temp);
            }
        } else {
            System.out.println("Error al añadir un objeto al inventario");
        }
    }

    /*
    public void anadirObjeto(Objeto objeto, int i){
        boolean anadir = true;
        Objeto objeto_temp = null;
        int j = 0;
        for(Objeto objeto_inventario: inventario){
            if(objeto_inventario.equals(objeto)){
                anadir=false;
                objeto_temp=objeto_inventario;
                break;
            }
            j++;
        }
        if(anadir){
            objeto_temp.aumentarCantidad(i);
            inventario.set(j, objeto_temp);
        }else{
            objeto_temp = objeto;
            objeto_temp.setCantidad(i);
            inventario.add(objeto_temp);
        }
    }
     */
    
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
                plantas.set(j, objeto_temp);
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
                semillas.set(j, objeto_temp);
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
                pociones.set(j, objeto_temp);
            }
        } else {
            System.out.println("Error al eliminar un objeto del inventario");
        }
        return esta;
    }

    /*
    public boolean eliminarObjeto(Objeto objeto) {
        boolean esta = false;
        Objeto objeto_temp = null;
        int j = 0;
        for (Objeto objeto_ineventario : inventario) {
            if (objeto_ineventario.equals(objeto)) {
                esta = true;
                break;
            }
            j++;
        }
        if (esta) {
            objeto_temp = inventario.get(j);
            objeto_temp.reducirCantidad(1);
            inventario.set(j, objeto_temp);
        }
        return esta;
    }
     */
    
    public ArrayList<Objeto> getPlantas(){
        return plantas;
    }
    
    public ArrayList<Objeto> getSemillas(){
        return semillas;
    }
    
    public ArrayList<Objeto> getPociones(){
        return pociones;
    }
}