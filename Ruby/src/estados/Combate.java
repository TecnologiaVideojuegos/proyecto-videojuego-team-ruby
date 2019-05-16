package estados;

import java.awt.Font;
import objetos.Objeto;
import objetos.plantas.Planta_agua;
import objetos.plantas.Planta_fuego;
import objetos.plantas.Planta_rayo;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import personajes.Boss;
import personajes.Enemigo;
import personajes.Jugador;
import personajes.Personaje;

public class Combate extends BasicGameState {

    private Jugador ruby;
    private Personaje combatiente;
    private Image imgRuby, imgEnemy, imgBoss, iconoFAgua, iconoPFuego, iconoPRayo, iconoPocionVida, iconoPocionDanio;
    private int p_Widht, p_Height;
    private Circle cursor_hitbox;
    //Menu opciones
    private Rectangle btnAtaques, btnPociones, btnHuir;
    private boolean sobreAtaques = false, sobrePociones = false, sobreHuir = false;
    private boolean pulsadoAtaques = true, pulsadoPociones = false, pulsadoHuir = false;
    //Submenu ataques
    private Rectangle btnAgua, btnFuego, btnRayo;
    private boolean sobreAgua = false, sobreFuego = false, sobreRayo = false;
    //Submenu pociones
    private Rectangle btnPocionVida, btnPocionDanio;
    private boolean sobrePocionVida = false, sobrePocionDanio = false;

    //Acciones del combate
    private boolean turnoRuby = false, ataque = false, acierto = false, tomaPocion = false, huir = false;

    private StateBasedGame game;
    private int estadoAnterior = 0;
    private Font font;

    private boolean actualizar = true;
    private int numFAgua = 0, numFFuego = 0, numFRayo = 0, numPVida = 0, numPDanio = 0;
    private boolean sobreContinuar = false;
    private Rectangle btnContinuar;

    public Combate(Jugador ruby) throws SlickException {
        this.ruby = ruby;
        this.imgRuby = new Image("./resources/img/RubyAvatar.png");
        this.imgEnemy = new Image("./resources/img/Planta.png");
        this.imgBoss = new Image("./resources/img/Boss.png");
        this.iconoFAgua = new Image("./resources/img/florAzul.png");
        this.iconoPFuego = new Image("./resources/img/florRoja.png");
        this.iconoPRayo = new Image("./resources/img/florAmarilla.png");
        this.iconoPocionVida = new Image("./resources/img/pocionVida.png");
        this.iconoPocionDanio = new Image("./resources/img/pocionDanio.png");
    }

    @Override
    public void init(GameContainer gc, StateBasedGame game) throws SlickException {
        this.game = game;
        p_Widht = gc.getWidth();
        p_Height = gc.getHeight();

        //Elementos de colisión/interacción
        cursor_hitbox = new Circle(gc.getInput().getMouseX(), gc.getInput().getMouseY(), 2);
        btnAtaques = new Rectangle(954, 478, 270, 50);
        btnPociones = new Rectangle(954, 560, 270, 50);
        btnHuir = new Rectangle(954, 642, 270, 50);
        btnAgua = new Rectangle(50, 560, 230, 50);
        btnFuego = new Rectangle(350, 560, 230, 50);
        btnRayo = new Rectangle(626, 560, 230, 50);
        btnPocionVida = new Rectangle(150, 560, 230, 50);
        btnPocionDanio = new Rectangle(525, 560, 230, 50);
        btnPocionDanio = new Rectangle(525, 560, 230, 50);
        btnContinuar = new Rectangle(964, 560, 270, 50);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame game, Graphics grphcs) throws SlickException {
        if (combatiente != null) {
            // --------- FONDO --------- //
            grphcs.setColor(new Color(0xFFFFFF));
            grphcs.fillRect(0, 0, p_Widht, p_Height);

            // --------- ZONA COMBATE --------- //
            grphcs.setColor(Color.black);
            font = new Font("Verdana", Font.PLAIN, 30);
            grphcs.setFont(new TrueTypeFont(font, true));
            //Enemigo Texto
            grphcs.drawString("HP", 750, 15);
            if (combatiente.getNombre().equals("Boss")) { //Comprovamos si es Boss o enemigo simple
                grphcs.drawString("Boss", 980, 120);
            } else {
                grphcs.drawString("Enemigo", 980, 120);
            }
            //Ruby Texto
            grphcs.drawString("HP", 40, 140);
            grphcs.drawString("Ruby", 230, 250);

            //Enemigo cuadro Vida
            grphcs.setColor(new Color(0x61BE6D));
            grphcs.fillRect(750, 60, combatiente.getVida() * 5, 30);
            grphcs.setColor(new Color(0xFF7272));
            grphcs.fillRect(750 + (combatiente.getVida() * 5), 60, (100 - combatiente.getVida()) * 5, 30);
            //Ruby cuadro Vida
            grphcs.setColor(new Color(0x61BE6D));
            grphcs.fillRect(40, 190, ruby.getVida() * 5, 30);
            grphcs.setColor(new Color(0xFF7272));
            grphcs.fillRect(40 + (ruby.getVida() * 5), 190, (100 - ruby.getVida()) * 5, 30);

            //Enemigo imagen
            if (combatiente.getNombre().equals("Boss")) { //Comprovamos si es Boss o enemigo simple
                imgBoss.draw(1080, 120, 170, 170);
            } else {
                imgEnemy.draw(1080, 120, 170, 170);
            }
            //Ruby imagen
            imgRuby.draw(40, 250, 170, 170);

            // --------- ZONA MENU--------- //
            //Marcos
            grphcs.setColor(new Color(0xFFAEAE));
            grphcs.fillRect(0, 450, p_Widht, 5);
            grphcs.fillRect(900, 450, 5, 270);

            if (pulsadoAtaques || pulsadoPociones || pulsadoHuir) {
                //MENU OPCIONES
                grphcs.setColor(new Color(0xFFAEAE));   //Bordes
                if (pulsadoAtaques) {
                    grphcs.fillRect(952, 476, 274, 54);
                } else if (pulsadoPociones) {
                    grphcs.fillRect(952, 558, 274, 54);
                } else if (pulsadoHuir) {
                    grphcs.fillRect(952, 640, 274, 54);
                }

                if (sobreAtaques) {       //btn ataque
                    grphcs.setColor(new Color(0xFFF1F1)); //Color sobre boton
                } else {
                    grphcs.setColor(new Color(0xF0F0F0));
                }
                grphcs.fillRect(954, 478, 270, 50);

                if (sobrePociones) {       //btn pociones
                    grphcs.setColor(new Color(0xFFF1F1)); //Color sobre boton
                } else {
                    grphcs.setColor(new Color(0xF0F0F0));
                }
                grphcs.fillRect(954, 560, 270, 50);

                if (sobreHuir) {       //btn huir
                    grphcs.setColor(new Color(0xFFF1F1)); //Color sobre boton
                } else {
                    grphcs.setColor(new Color(0xF0F0F0));
                }
                grphcs.fillRect(954, 642, 270, 50);

                grphcs.setColor(Color.black);               //Texto
                font = new Font("Verdana", Font.PLAIN, 35);
                grphcs.setFont(new TrueTypeFont(font, true));
                grphcs.drawString("ATAQUES", 1007, 482);
                grphcs.drawString("POCIONES", 1003, 566);
                grphcs.drawString("HUIDA", 1031, 646);

                //SUBMENUS
                if (pulsadoAtaques) {//MENU ATAQUES

                    if (sobreAgua) {       //btn ataque
                        grphcs.setColor(new Color(0xFFF1F1)); //Color sobre boton
                    } else {
                        grphcs.setColor(new Color(0xF0F0F0));
                    }
                    grphcs.fillRect(50, 560, 230, 50);

                    if (sobreFuego) {       //btn pociones
                        grphcs.setColor(new Color(0xFFF1F1)); //Color sobre boton
                    } else {
                        grphcs.setColor(new Color(0xF0F0F0));
                    }
                    grphcs.fillRect(350, 560, 230, 50);

                    if (sobreRayo) {       //btn huir
                        grphcs.setColor(new Color(0xFFF1F1)); //Color sobre boton
                    } else {
                        grphcs.setColor(new Color(0xF0F0F0));
                    }
                    grphcs.fillRect(626, 560, 230, 50);

                    iconoFAgua.draw(60, 565, 40, 40);
                    iconoPFuego.draw(359, 565, 40, 40);
                    iconoPRayo.draw(636, 565, 40, 40);

                    grphcs.setColor(Color.black);               //Texto
                    font = new Font("Verdana", Font.PLAIN, 30);
                    grphcs.setFont(new TrueTypeFont(font, true));
                    grphcs.drawString("Selecciona ataque:", 15, 470);
                    grphcs.drawString("Agua", 125, 568);
                    grphcs.drawString("x" + numFAgua, 218, 568);
                    grphcs.drawString("Fuego", 423, 568);
                    grphcs.drawString("x" + numFFuego, 517, 568);
                    grphcs.drawString("Rayo", 700, 568);
                    grphcs.drawString("x" + numFRayo, 794, 568);

                } else if (pulsadoPociones) {

                    if (sobrePocionVida) {       //btn ataque
                        grphcs.setColor(new Color(0xFFF1F1)); //Color sobre boton
                    } else {
                        grphcs.setColor(new Color(0xF0F0F0));
                    }
                    grphcs.fillRect(150, 560, 230, 50);

                    if (sobrePocionDanio) {       //btn pociones
                        grphcs.setColor(new Color(0xFFF1F1)); //Color sobre boton
                    } else {
                        grphcs.setColor(new Color(0xF0F0F0));
                    }
                    grphcs.fillRect(525, 560, 230, 50);

                    iconoPocionVida.draw(160, 565, 40, 40);
                    iconoPocionDanio.draw(535, 565, 40, 40);

                    grphcs.setColor(Color.black);               //Texto
                    font = new Font("Verdana", Font.PLAIN, 30);
                    grphcs.setFont(new TrueTypeFont(font, true));
                    grphcs.drawString("Selecciona poción:", 15, 470);
                    grphcs.drawString("Vida", 224, 568);
                    grphcs.drawString("x" + numPVida, 318, 568);
                    grphcs.drawString("Ataque", 583, 568);
                    grphcs.drawString("x" + numPDanio, 693, 568);;

                } else if (pulsadoHuir) {

                }
            } else {
                if (sobreContinuar) {       //btn continuar
                    grphcs.setColor(new Color(0xFFF1F1)); //Color sobre boton
                } else {
                    grphcs.setColor(new Color(0xF0F0F0));
                }
                grphcs.fillRect(964, 560, 270, 50);

                grphcs.setColor(Color.black);               //Texto
                font = new Font("Verdana", Font.PLAIN, 35);
                grphcs.setFont(new TrueTypeFont(font, true));
                grphcs.drawString("CONTINUAR", 992, 564);

                font = new Font("Verdana", Font.PLAIN, 30);
                grphcs.setFont(new TrueTypeFont(font, true));

                if (turnoRuby) {    //Turno ruby
                    if (ataque) {   //Accion ataque
                        if (acierto) {
                            grphcs.drawString("Tu ataque es eficaz y daña al enemigo", 128, 564);
                        } else {
                            grphcs.drawString("El enemigo esquiva tu ataque", 207, 564);
                        }
                    } else if (tomaPocion) {    //Accion toma de pocion
                        grphcs.drawString("La poción surge efecto", 257, 564);
                    } else {
                        if (huir) {   //Accion huir
                            grphcs.drawString("¡Huyes con éxito!", 332, 564);
                        } else {
                            grphcs.drawString("Tu enemigo no te deja escapar", 201, 564);
                        }
                    }
                } else {  //Tunrno enemigo
                    if (ataque) {   //Accion ataque
                        if (acierto) {
                            grphcs.drawString("El enemigo te ataca, sufres daños", 171, 564);
                        } else {
                            grphcs.drawString("El enemigo te ataca, pero falla", 198, 564);
                        }
                    } else if (tomaPocion) {    //Accion toma de pocion
                        grphcs.drawString("El enemigo realiza un conjuro para recupera vida", 22, 564);
                    }
                }
            }
        }
    }

    @Override
    public void update(GameContainer gc, StateBasedGame game, int i) throws SlickException {
        cursor_hitbox.setX(gc.getInput().getMouseX() - (cursor_hitbox.getHeight() / 2));
        cursor_hitbox.setY(gc.getInput().getMouseY() - (cursor_hitbox.getWidth() / 2));
        if (combatiente != null) {

            //Comprobamos si se necesita actualización de valores
            if (actualizar) {
                cargarValoresInventario();
                actualizar = false;
            }

            //Comprovamos finalización de combate
            if (combatiente.getVida() <= 0) {
                String nombreClaseAnterior = game.getState(estadoAnterior).getClass().getName();
                if (nombreClaseAnterior.equals("estados.Mazmorra")) {
                    ((Mazmorra) game.getState(estadoAnterior)).combateGanado(true);
                    game.getCurrentState().leave(gc, game);
                    resetAllValues();
                    game.enterState(2);
                } else if (nombreClaseAnterior.equals("estados.Prueba")) {
                    ((Prueba) game.getState(estadoAnterior)).combateGanado(true);
                    game.getCurrentState().leave(gc, game);
                    resetAllValues();
                    game.enterState(4);
                } else if (nombreClaseAnterior.equals("estados.Prueba_Mazmorra")) {
                    ((Prueba_Mazmorra) game.getState(estadoAnterior)).combateGanado(true);
                    game.getCurrentState().leave(gc, game);
                    resetAllValues();
                    game.enterState(5);
                }
                game.enterState(estadoAnterior);
            } else if (ruby.getVida() <= 0) {
                String nombreClaseAnterior = game.getState(estadoAnterior).getClass().getName();
                if (nombreClaseAnterior.equals("estados.Mazmorra")) {
                    ((Mazmorra) game.getState(estadoAnterior)).combateGanado(false);
                    resetAllValues();
                    game.enterState(2);
                } else if (nombreClaseAnterior.equals("estados.Prueba")) {
                    ((Prueba) game.getState(estadoAnterior)).combateGanado(false);
                    resetAllValues();
                    game.enterState(4);
                } else if (nombreClaseAnterior.equals("estados.Prueba_Mazmorra")) {
                    ((Prueba_Mazmorra) game.getState(estadoAnterior)).combateGanado(false);
                    resetAllValues();
                    game.enterState(5);
                }
            }

            if (pulsadoAtaques || pulsadoPociones || pulsadoHuir) {     //ENTRE COMBATES

                //MENU OPCIONES
                if (cursor_hitbox.intersects(btnAtaques)) {
                    sobreAtaques = true;
                    if (gc.getInput().isMousePressed(0)) {
                        pulsadoAtaques = true;
                        pulsadoPociones = false;
                        pulsadoHuir = false;
                    }
                } else {
                    sobreAtaques = false;
                }
                if (cursor_hitbox.intersects(btnPociones)) {
                    sobrePociones = true;
                    if (gc.getInput().isMousePressed(0)) {
                        pulsadoAtaques = false;
                        pulsadoPociones = true;
                        pulsadoHuir = false;
                    }
                } else {
                    sobrePociones = false;
                }

                if (cursor_hitbox.intersects(btnHuir)) {
                    sobreHuir = true;
                    if (gc.getInput().isMousePressed(0)) {
                        turnoRuby = true;
                        entrarTurnoCombate();
                        if (((int) (Math.random() * 3)) == 0) {
                            huir = true;
                        } else {
                            huir = false;
                        }

                    }
                } else {
                    sobreHuir = false;
                }

                //MENU ATAQUES
                if (pulsadoAtaques) {
                    if (numFAgua > 0 && cursor_hitbox.intersects(btnAgua)) {
                        sobreAgua = true;
                        if (gc.getInput().isMousePressed(0)) {
                            acierto = ruby.ataque(combatiente, new Planta_agua());
                            turnoRuby = true;
                            ataque = true;
                            entrarTurnoCombate();
                            actualizar = true;
                        }
                    } else {
                        sobreAgua = false;
                    }

                    if (numFFuego > 0 && cursor_hitbox.intersects(btnFuego)) {
                        sobreFuego = true;
                        if (gc.getInput().isMousePressed(0)) {
                            acierto = ruby.ataque(combatiente, new Planta_fuego());
                            turnoRuby = true;
                            ataque = true;
                            entrarTurnoCombate();
                            actualizar = true;
                        }
                    } else {
                        sobreFuego = false;
                    }

                    if (numFRayo > 0 && cursor_hitbox.intersects(btnRayo)) {
                        sobreRayo = true;
                        if (gc.getInput().isMousePressed(0)) {
                            acierto = ruby.ataque(combatiente, new Planta_rayo());
                            turnoRuby = true;
                            ataque = true;
                            entrarTurnoCombate();
                            actualizar = true;
                        }
                    } else {
                        sobreRayo = false;
                    }
                }

                //MENU POCIONES
                if (pulsadoPociones) {
                    if (numPVida > 0 && cursor_hitbox.intersects(btnPocionVida)) {
                        sobrePocionVida = true;
                        if (gc.getInput().isMousePressed(0)) {
                            ruby.tomarPocionVida();
                            turnoRuby = true;
                            tomaPocion = true;
                            entrarTurnoCombate();
                            actualizar = true;
                        }
                    } else {
                        sobrePocionVida = false;
                    }

                    if (numPDanio > 0 && cursor_hitbox.intersects(btnPocionDanio)) {
                        sobrePocionDanio = true;
                        if (gc.getInput().isMousePressed(0)) {
                            ruby.tomarPocionDanio();
                            turnoRuby = true;
                            tomaPocion = true;
                            entrarTurnoCombate();
                            actualizar = true;
                        }
                    } else {
                        sobrePocionDanio = false;
                    }
                }
            } else {                                                          //EN TURNO COMBATE
                if (cursor_hitbox.intersects(btnContinuar)) {
                    sobreContinuar = true;
                    if (gc.getInput().isMousePressed(0)) {
                        if (turnoRuby) {    //En el turno de ruby
                            if (huir) {
                                String nombreClaseAnterior = game.getState(estadoAnterior).getClass().getName();
                                if (nombreClaseAnterior.equals("estados.Mazmorra")) {
                                    ((Mazmorra) game.getState(estadoAnterior)).huidoDeCombate();
                                }else if (nombreClaseAnterior.equals("estados.Prueba")) {
                                    ((Prueba) game.getState(estadoAnterior)).huidoDeCombate();
                                } else if (nombreClaseAnterior.equals("estados.Prueba_Mazmorra")) {
                                    ((Prueba_Mazmorra) game.getState(estadoAnterior)).huidoDeCombate();
                                }
                                resetAllValues();
                                game.enterState(estadoAnterior);
                            } else {
                                //Para el resto de casos el siguiente en realizar acción es el enemigo
                                turnoRuby = false;
                                ataque = false;
                                acierto = false;
                                tomaPocion = false;

                                if (combatiente.getNombre().equals("Boss")) {
                                    Boss b = (Boss) combatiente;
                                    if ((int) (Math.random() * 10) == 0) {
                                        tomaPocion = true;
                                        b.tomarPocionVida();
                                    } else {
                                        ataque = true;
                                        acierto = b.ataque(ruby);
                                    }

                                } else {
                                    ataque = true;
                                    Enemigo e = (Enemigo) combatiente;
                                    acierto = e.ataque(ruby);
                                }

                            }
                        } else {              //En el turno del enemigo
                            turnoRuby = false;
                            ataque = false;
                            acierto = false;
                            tomaPocion = false;
                            huir = false;
                            salirTurnoCombate();
                        }

                    }
                } else {
                    sobreContinuar = false;
                }
            }
        }
    }

    @Override
    public int getID() {
        return 3;
    }

    public void setEstadoAnterior(int i) {
        this.estadoAnterior = i;
    }

    public void setCombatiente(Personaje combatiente) {
        this.combatiente = combatiente;
        this.actualizar = true;
        pulsadoAtaques = true;
        pulsadoPociones = false;
        pulsadoHuir = false;
    }

    private void cargarValoresInventario() {
        numFAgua = 0;
        numFFuego = 0;
        numFRayo = 0;
        numPVida = 0;
        numPDanio = 0;

        for (Objeto obj : ruby.getInventario().getPlantas()) {
            if (obj.getNombre().equals("Planta de agua")) {
                numFAgua = obj.getCantidad();
            } else if (obj.getNombre().equals("Planta de fuego")) {
                numFFuego = obj.getCantidad();
            } else if (obj.getNombre().equals("Planta de rayo")) {
                numFRayo = obj.getCantidad();
            }
        }

        for (Objeto obj : ruby.getInventario().getPociones()) {
            if (obj.getNombre().equals("Pocion de vida")) {
                numPVida = obj.getCantidad();
            } else if (obj.getNombre().equals("Pocion de ataque")) {
                numPDanio = obj.getCantidad();
            }
        }
    }

    private void entrarTurnoCombate() {
        pulsadoAtaques = false;
        pulsadoPociones = false;
        pulsadoHuir = false;
    }

    private void salirTurnoCombate() {
        pulsadoAtaques = true;
        pulsadoPociones = false;
        pulsadoHuir = false;
    }

    public void resetAllValues() {
        combatiente = null;
        sobreAtaques = false;
        sobrePociones = false;
        sobreHuir = false;
        pulsadoAtaques = true;
        pulsadoPociones = false;
        pulsadoHuir = false;
        sobreAgua = false;
        sobreFuego = false;
        sobreRayo = false;
        sobrePocionVida = false;
        sobrePocionDanio = false;
        turnoRuby = false;
        ataque = false;
        acierto = false;
        tomaPocion = false;
        huir = false;
        actualizar = true;
        numFAgua = 0;
        numFFuego = 0;
        numFRayo = 0;
        numPVida = 0;
        numPDanio = 0;
        sobreContinuar = false;
    }
}
