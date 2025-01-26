import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        // Declaración/Inicialización de variables
        final String BIENVENIDA = """
            _________________________________
            |.         ¡BIENVENIDO!        .|
            |_______________________________|
            |>>>>>>>>>>>>>>>x<<<<<<<<<<<<<<<|
            |   <-Esto es el TIC-TAC-TOE->  |
            |>>>>>>>>>>>>>>>x<<<<<<<<<<<<<<<|
            |                               |
            |         +---+---+---+         |
            |       1 | X | X | O |         |
            |         |---+---+---|         |
            | filas 2 | O | X | O |         |
            |         |---+---+---|         |
            |       3 | X | O | O |         |
            |         +---+---+---+         |
            | columnas  1   2   3           |
            |.                             .|
            |_______________________________|
                """;
        final String REGLAS = """
            |>>>>>>>>>>>>>>>x<<<<<<<<<<<<<<<|
            |.   <-REGLAS/INSTRUCCIONES->  .|
            |>>>>>>>>>>>>>>>x<<<<<<<<<<<<<<<|
            | 1. Se registrarán el nombre y |
            |    el símbolo elegido por     |
            |    cada jugador.              |
            | 2. Se elige aleatoriamente    |
            |    el jugador que empieza.    |
            | 3. Al final de la partida se  |
            |    puede jugar de nuevo,      |
            |    quedando registradas las   |
            |    victorias de cada jugador, |
            |    o salir del juego.         |
            | 4. Ganará el jugador que      |
            |    cuente con más victorias.  |
            |>>>>>>>>>>>>>>x<<<<<<<<<<<<<<<<|
            |.     ¡COMIENZA EL JUEGO!     .|
            |>>>>>>>>>>>>>>x<<<<<<<<<<<<<<<<|
                """;
        final String SEPARADOR = "--------------------------------------------------\n";
        final String PEDIRFILA = "Introduzca una fila (1-3): ";
        final String PEDIRCOLUMNA = "Introduzca una columna (1-3): ";
        final String PEDIRSIMBOLO = " escoja su símbolo para el juego: ";
        char[][] tablero = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
        };
        String jugador1, jugador2, caracter1, caracter2, primerJugador, segundoJugador, respuestaJugarDeNuevo, marcadorFinal = "";
        char simbolo1, simbolo2, turno1, turno2;
        int fila, columna, partida = 1, victoriasJugador1 = 0, victoriasJugador2 = 0;
        boolean partidaTerminada = false, movimientoInvalido = false, jugarDeNuevo = false;

        // EJECUCIÓN DEL JUEGO
        System.out.print(BIENVENIDA + REGLAS);
        
        // Registro Jugador 1
        System.out.print(SEPARADOR + "Ingrese un nombre para el jugador 1: ");
        jugador1 = sc.nextLine();
        System.out.print(jugador1 + PEDIRSIMBOLO);
        caracter1 = sc.nextLine();
        simbolo1 = caracter1.charAt(0);

        // Registro Jugador 2
        System.out.print(SEPARADOR + "Ingrese un nombre para el jugador 2: ");
        jugador2 = sc.nextLine();
        System.out.print(jugador2 + PEDIRSIMBOLO);
        caracter2 = sc.nextLine();
        simbolo2 = caracter2.charAt(0);

        // Comprobar que los caracteres elegidos por ambos jugadores no son iguales (Podría dar lugar a confusión en el juego)
        while (simbolo1 == simbolo2) {
            System.out.print(SEPARADOR + "Los simbolos de ambos jugadores deben ser diferentes, por favor introduzca otro simbolo: ");
            caracter2 = sc.nextLine();
            simbolo2 = caracter2.charAt(0);
        }

        // Bucle que permite jugar otra partida
        do {
            System.out.println(SEPARADOR + "ESTA ES LA PARTIDA Nº " + partida);

            // Condición que elige el jugador que empieza la partida
            if (Math.random() < 0.5){
                primerJugador = jugador1;
                turno1 = simbolo1;
                segundoJugador = jugador2;
                turno2 = simbolo2;
            } else {
                primerJugador = jugador2;
                turno1 = simbolo2;
                segundoJugador = jugador1;
                turno2 = simbolo1;
            }

            // Bucle que permite jugar una partida hasta que haya un ganador o empate
            while (!partidaTerminada) {

                // TURNO DEL PRIMER JUGADOR
                do { // Bucle que permite jugar de nuevo al jugador en caso de que haya introducido una posición no válida
                    mostrarTablero(tablero); // Muestra el estado del tablero
                    System.out.println(SEPARADOR + "Es el turno de " + primerJugador);

                    System.out.print(PEDIRFILA);
                    fila = sc.nextInt();
                    fila --;

                    System.out.print(PEDIRCOLUMNA);
                    columna = sc.nextInt();
                    columna --;

                    if (hacerJugada(tablero,fila,columna,turno1)) { // En caso de que la jugada sea permitida se comprueba si hay ganador o empate
                        movimientoInvalido = false;
                        if (comprobarGanador(tablero, turno1)) { // Si hay un ganador se muestra el tablero y el nombre del ganador
                            mostrarTablero(tablero);
                            System.out.println(SEPARADOR + "¡" + primerJugador + " gana esta partida!");
                            // Se comprueba que jugador ha ganado y se suma la victoria a su marcador
                            if (primerJugador.equals(jugador1)) {
                                victoriasJugador1 ++;
                            } else if (primerJugador.equals(jugador2)) {
                                victoriasJugador2 ++;
                            }
                            partidaTerminada = true;
                        } else if (comprobarEmpate(tablero)) { // Si hay empate se muestra el tablero y el mensaje correspondiente
                            mostrarTablero(tablero);
                            System.out.println(SEPARADOR + "¡Esto es un empate!");
                            partidaTerminada = true;
                        }                     
                    } else { // Jugada no permitida
                        movimientoInvalido = true;
                        System.out.println("Casilla no permitida o ya ocupada, pruebe otra posición.");
                    }
                } while (movimientoInvalido); // Mientras la jugada no sea válida se repite el turno (DO-WHILE)

                // TURNO DEL SEGUNDO JUGADOR
                if (!partidaTerminada) { // Si la partida no ha terminado se ejecutara el turno del segundo jugador
                    do { // Bucle que permite jugar de nuevo al jugador en caso de que haya introducido una posición no válida
                        mostrarTablero(tablero); // Muestra el estado del tablero
                        System.out.println(SEPARADOR + "Es el turno de " + segundoJugador);
    
                        System.out.print(PEDIRFILA);
                        fila = sc.nextInt();
                        fila --;
    
                        System.out.print(PEDIRCOLUMNA);
                        columna = sc.nextInt();
                        columna --;
    
                        if (hacerJugada(tablero,fila,columna,turno2)) { // En caso de que la jugada sea permitida se comprueba si hay ganador o empate
                            movimientoInvalido = false;
                            if (comprobarGanador(tablero, turno2)) { // Si hay un ganador se muestra el tablero y el nombre del ganador
                                mostrarTablero(tablero);
                                System.out.println(SEPARADOR + "¡" + segundoJugador + " gana esta partida!");
                                // Se comprueba que jugador ha ganado y se suma la victoria a su marcador
                                if (segundoJugador.equals(jugador1)) {
                                    victoriasJugador1 ++;
                                } else if (segundoJugador.equals(jugador2)) {
                                    victoriasJugador2 ++;
                                }
                                partidaTerminada = true;
                            } else if (comprobarEmpate(tablero)) { // Si hay empate se muestra el tablero y el mensaje correspondiente
                                mostrarTablero(tablero);
                                System.out.println(SEPARADOR + "¡Ha habido un empate!");
                                partidaTerminada = true;
                            }                     
                        } else { // Jugada no permitida
                            movimientoInvalido = true;
                            System.out.println("Casilla no permitida o ya ocupada, pruebe otra posición.");
                        }
                    } while (movimientoInvalido); // Mientras la jugada no sea válida se repite el turno (DO-WHILE)
                }
            } // FIN PARTIDA

            System.out.print(SEPARADOR + "¿Los jugadores desean jugar otra partida? (SI/NO): ");
            sc.nextLine();
            respuestaJugarDeNuevo = sc.nextLine();

            while (!respuestaJugarDeNuevo.equalsIgnoreCase("SI") && !respuestaJugarDeNuevo.equalsIgnoreCase("NO")) { // MIENTRA la respuesta es diferente de (SI/NO) se pedirá que responda de nuevo
                System.out.print("Respuesta no válida, por favor responda SI o NO: ");
                respuestaJugarDeNuevo = sc.nextLine();
            }

            if (respuestaJugarDeNuevo.equalsIgnoreCase("SI")) { // Si la respuesta es afirmativa se ejecutara otra partida 
                jugarDeNuevo = true;
                partidaTerminada = false;
                limpiarTablero(tablero); // Reinicia el tablero a su estado inicial
                partida ++;
            } else if (respuestaJugarDeNuevo.equalsIgnoreCase("NO")) { // Caso contrario se presentara el marcador final
                jugarDeNuevo = false;
                if (victoriasJugador1 > victoriasJugador2) {
                    marcadorFinal = "Gana el jugador " + jugador1 + " con " + victoriasJugador1 + " victorias frente a " + victoriasJugador2 + " victorias del jugador " + jugador2;
                } else if (victoriasJugador1 < victoriasJugador2) {
                    marcadorFinal = "Gana el jugador " + jugador2 + " con " + victoriasJugador2 + " victorias frente a " + victoriasJugador1 + " victorias del jugador " + jugador1;
                } else if (victoriasJugador1 == victoriasJugador2) {
                    marcadorFinal = "¡EMPATE! Ambos jugadores cuentan con " + victoriasJugador1 + " victorias";
                }
                System.out.println(SEPARADOR + marcadorFinal);
            }

        } while (jugarDeNuevo); // Se ejecutará otra partida mientras se quiera jugar de nuevo (DO/WHILE)

        System.out.print(SEPARADOR + "FIN DEL JUEGO, GRACIAS POR JUGAR.");

        sc.close();

    } // ¡FIN MAIN!

    // Método que nos muestra el tablero por pantalla
    public static void mostrarTablero (char[][]tablero){
        int numeroFila = 1;
        String salidaTablero = "";
        for (int i = 0; i < tablero.length; i++) {
            if (i == 0) { 
                salidaTablero += "  +---+---+---+\n"; // Borde superior
            }
            salidaTablero += numeroFila + " ";
            numeroFila ++;
            for (int j = 0; j < tablero[i].length; j++) {
                salidaTablero += "| " + tablero[i][j] + " "; // Inicio de la fila y contenido de las casillas
                if (j == 2) {
                    salidaTablero += "|\n"; // Cierre final de la fila
                }
            }
            if (i < 2) {
                salidaTablero += "  |---+---+---|\n"; // Separaador entre filas
            }
            if (i == 2) {
                salidaTablero += ("  +---+---+---+\n    1   2   3"); // Borde inferior
            }
        }
        System.out.println(salidaTablero);
    }

    // Método que comprueba si la jugada a realizar por el jugador es válida
    public static boolean hacerJugada(char[][] tablero, int fila, int columna, char jugador){
        if (tablero [fila][columna] != ' ' || fila < 0 || fila > 2 || columna < 0 || columna > 2) {
            return false; // Jugada no válida
        }
        tablero[fila][columna] = jugador; // Se sustituye la posición introducida por el simbolo del jugador
        return true; // Jugada válida
    }

    // Método que comprueba si hay un ganador
    public static boolean comprobarGanador(char[][] tablero, char jugador){
        for (int i = 0; i < tablero.length; i++) {
            // Comprobar filas y columnas
            if (tablero[i][0] == jugador && tablero[i][1] == jugador && tablero[i][2] == jugador ||
                tablero[0][i] == jugador && tablero[1][i] == jugador && tablero[2][i] == jugador) {
                return true;
            }
        }
        // Comprobar diagonales
        if (tablero[0][0] == jugador && tablero[1][1] == jugador && tablero[2][2] == jugador ||
            tablero[2][0] == jugador && tablero[1][1] == jugador && tablero[0][2] == jugador) {
            return true;
        }
        return false;
    }

    // Método que comprueba si hay un empate
    public static boolean comprobarEmpate(char[][]tablero) {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                if (tablero[i][j] == ' '){
                    return false; // Todavía hay movimientos disponibles
                }
            }
        }
        return true; // Todas las casillas estan ocupadas sin haber un ganador (EMPATE)
    }

    // Método que limpia el tablero para jugar una nueva partida
    public static void limpiarTablero(char[][] tablero) {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                tablero[i][j] = ' ';
            }
        }
    }
}