

import java.util.Scanner;

public class CuentasMain {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        CuentaBancaria cuentaUsuario = new CuentaBancaria(null, null, 0, 0);

        CuentaBancaria[] arrayCuentasUsuario = new CuentaBancaria[4];
    
        final String BIENVENIDA = """

                |>>>>>>>>>>>>>>>>>>>x<<<<<<<<<<<<<<<<<<|
                |       ¡Bienvenido a QUIEBRANCO!      |
                |--------------------------------------|
                |  El banco donde nuestro presupuesto  |
                |               eres TU :/             |
                |>>>>>>>>>>>>>>>>>>>x<<<<<<<<<<<<<<<<<<|
                """;
        final String INICIO = """
                Registrese o inicie sesion para realizar una operación o consulta.
    
                    1. Iniciar sesion.
                    2. Registrarse.
                    0. Salir

                ¿Qué desea hacer?: """;
        final String MENU = """               
                ¿Que operación desea realizar?

                    1. Crear cuenta.
                    2. Ingresar dinero.
                    3. Retirar dinero.
                    4. Realizar transferencia.
                    5. Consultar saldo.
                
                Por favor introduzca su elección: """;
        final String USUARIO_NO_REGISTRADO = "No se ha detectado ninguna usuario registrado.";
        final String PEDIR_DATOS_REGISTRO = "\nPor favor introduzca los datos que se solicitan a continuación:";
        final String PEDIR_INGRESAR = "Introduzca la cantidad a ingresar: ";
        final String PEDIR_RETIRAR = "Introduzca la cantidad a retirar: ";
        final String PEDIR_TRANSFERIR = "Introduzca la cantidad a transferir: ";
        final String SEPARADOR_CORTO = "----------------------------------------\n"; // Su uso es para fines esteticos (TAMAÑO CORTO)
        final String SEPARADOR_LARGO = "-----------------------------------------------------------\n"; // Su uso es para fines esteticos (TAMAÑO LARGO)
        final String OPCION_INVALIDA = "Opción no válida, vuelva a introducir su elección: ";
        final String ACCESO_DENEGADO = "Acceso denegado. Usuario o contraseña incorrectos.";
        final String OPERACION_CORRECTA = "Operación realizada correctamente.";
        final String DESEA_OPERAR = "¿Desea realizar otra operación? (SI/NO): ";
        final String MENSAJE_SALIDA = """
                Gracias por no confiar en nosotros.
                -------¡TENGA UN FELIZ DIA ;D!-------

                """;
        String respuestaSiNo, mensajeSwitch = "";
        short opcionInicio, opcionElegida, opcionOrigen, opcionDestino;
        String nombre = "", apellidos = "", clave = "", contraseniaAcceso;
        int dni = -1, usuario, contador = 0;
        double saldo, monto = -1;
        boolean salir = false;


        // INICIO DEL PROGRAMA

        System.out.print(BIENVENIDA);
        System.out.println(SEPARADOR_CORTO + USUARIO_NO_REGISTRADO);

        do { // 

            contador = 0;
            // Condición que registra un nuevo usuario

            if (cuentaUsuario.getDni() == 0){

                System.out.print(SEPARADOR_CORTO + PEDIR_DATOS_REGISTRO + "\n");
                
                System.out.print("\t-> NOMBRE: ");
                nombre = sc.nextLine();

                System.out.print("\t-> APELLIDOS: ");
                apellidos = sc.nextLine();

                System.out.print("\t-> DNI: ");
                dni = sc.nextInt();
                sc.nextLine();

                System.out.print("\t-> CONTRASEÑA: ");
                clave = sc.nextLine();

                arrayCuentasUsuario[contador] = new CuentaBancaria(nombre, apellidos, dni, contador);
                contador ++;
                System.out.println("\nUsuario registrado con éxito.");
            }

            // INICIO DE SESION PARA ACCEDER A TUS CUENTAS

            System.out.print(SEPARADOR_CORTO + "Inicie sesion para continuar:\n\t-> USUARIO(DNI): ");
            usuario = sc.nextInt();
            sc.nextLine();

            System.out.print("\t-> CONTRASEÑA: ");
            contraseniaAcceso = sc.nextLine();

            // Mientras el usario es correcto se pueden ejecutar operaciones/consultas
            if (verificarUsuario(dni, clave, usuario, contraseniaAcceso)) {

                System.out.println(SEPARADOR_CORTO + "ACCESO CONCEDIDO. Bienvenido Sr/Sra " +  arrayCuentasUsuario[0].getNombre());

                do {

                    System.out.print(SEPARADOR_CORTO + MENU + " ");

                    opcionElegida = sc.nextShort();

                    while (!opcionValida(opcionElegida)) {
                        System.out.print(SEPARADOR_LARGO + OPCION_INVALIDA);
                        opcionElegida = sc.nextShort();
                    }

                    sc.nextLine();

                    switch (opcionElegida) {

                        case 1: // Crear Cuenta
                            System.out.println(SEPARADOR_CORTO + "Ingrese los siguientes datos:");
                            System.out.print("\t-> NOMBRE: ");
                            nombre = sc.nextLine();
            
                            System.out.print("\t-> APELLIDOS: ");
                            apellidos = sc.nextLine();
            
                            System.out.print("\t-> DNI: ");
                            dni = sc.nextInt();
                            sc.nextLine();

                            System.out.print("\n¿Desea crear la cuenta con un importe inicial? (SI/NO): ");
                            respuestaSiNo = sc.nextLine();

                            while (!respuestaValidaSiNo(respuestaSiNo)) {
                                System.out.print(OPCION_INVALIDA);
                                respuestaSiNo = sc.nextLine();
                            }

                            if (!finalizar(respuestaSiNo)) {
                                System.out.print("\tIntroduzca un importe->  ");
                                saldo = sc.nextDouble();
                                sc.nextLine();
                                arrayCuentasUsuario[contador] = new CuentaBancaria(nombre, apellidos, dni, saldo, contador);
                            } else {
                                arrayCuentasUsuario[contador] = new CuentaBancaria(nombre, apellidos, dni, contador);
                            }
                            contador ++;
                            mensajeSwitch = "\nCuenta creada con éxito.";
                            break;
                        case 2: // Ingresar dinero 
                            System.out.println(SEPARADOR_CORTO + "Seleccione la cuenta en la que desea ingresar el dinero: ");
                            for (int i = 0; i < contador; i++) {
                                System.out.println(arrayCuentasUsuario[i].getNumCuenta() + ". Cuenta de " + arrayCuentasUsuario[i].getNombre());
                            }
                            System.out.print("Seleccione una opcion: ");
                            opcionElegida = sc.nextShort();
                            sc.nextLine();

                            System.out.print(SEPARADOR_CORTO + PEDIR_INGRESAR);
                            monto = sc.nextDouble();
                            sc.nextLine();
                            arrayCuentasUsuario[opcionElegida].ingresarDinero(monto);
                            mensajeSwitch = OPERACION_CORRECTA;
                            break;
                        case 3: // Retirar dinero
                            System.out.println(SEPARADOR_CORTO + "Seleccione la cuenta de la que desea retirar el dinero: ");
                            for (int i = 0; i < contador; i++) {
                                System.out.println(arrayCuentasUsuario[i].getNumCuenta() + ". Cuenta de " + arrayCuentasUsuario[i].getNombre());
                            }
                            System.out.print("Seleccione una opcion: ");
                            opcionElegida = sc.nextShort();
                            sc.nextLine();

                            System.out.print(SEPARADOR_CORTO + PEDIR_RETIRAR);
                            monto = sc.nextDouble();
                            sc.nextLine();
                            arrayCuentasUsuario[opcionElegida].retirarDinero(monto);
                            mensajeSwitch = OPERACION_CORRECTA;
                            break;
                        case 4: // Realizar transferencia
                            System.out.println(SEPARADOR_CORTO + "Seleccione la cuenta de origen:  ");
                            for (int i = 0; i < contador; i++) {
                                System.out.println(arrayCuentasUsuario[i].getNumCuenta() + ". Cuenta de " + arrayCuentasUsuario[i].getNombre());
                            }
                            System.out.print("Seleccione una opcion: ");
                            opcionOrigen = sc.nextShort();
                            sc.nextLine();

                            System.out.println(SEPARADOR_CORTO + "Seleccione la cuenta de destino: ");
                            for (int i = 0; i < contador; i++) {
                                System.out.println(arrayCuentasUsuario[i].getNumCuenta() + ". Cuenta de " + arrayCuentasUsuario[i].getNombre());
                            }
                            System.out.print("Seleccione una opcion: ");
                            opcionDestino = sc.nextShort();
                            sc.nextLine();

                            System.out.print(SEPARADOR_CORTO + PEDIR_TRANSFERIR);
                            monto = sc.nextDouble();
                            sc.nextLine();

                            arrayCuentasUsuario[opcionElegida].transferirDinero(arrayCuentasUsuario[opcionDestino], monto);

                            mensajeSwitch = OPERACION_CORRECTA;
                            break;
                        case 5: // Consultar saldo
                            System.out.println(SEPARADOR_CORTO + "Seleccione la cuenta de la que desea consultar el saldo: ");
                            for (int i = 0; i < contador; i++) {
                                System.out.println(arrayCuentasUsuario[i].getNumCuenta() + ". Cuenta de " + arrayCuentasUsuario[i].getNombre());
                            }
                            System.out.print("Seleccione una opcion: ");
                            opcionElegida = sc.nextShort();
                            sc.nextLine();

                            mensajeSwitch = "" + arrayCuentasUsuario[opcionElegida];
                            break;
                    }
                    System.out.println(mensajeSwitch);

                    System.out.print(SEPARADOR_CORTO + DESEA_OPERAR);
                    respuestaSiNo = sc.nextLine();
        
                    while (!respuestaValidaSiNo(respuestaSiNo)) {     
                        System.out.print(OPCION_INVALIDA);
                        respuestaSiNo = sc.nextLine();
                    }
        
                    salir = finalizar(respuestaSiNo);

                } while(!salir);

            } else {
                System.out.println(SEPARADOR_CORTO + ACCESO_DENEGADO);
            }   
             
            System.out.print(SEPARADOR_LARGO + INICIO + " ");
            opcionInicio = sc.nextShort();
            sc.nextLine();

            switch (opcionInicio) {
                case 1:
                    break;
                case 2:
                    cuentaUsuario.setDni(0);
                    break;
                case 3:
                    salir = true;
                default:
                    break;
            }

        } while (!salir);

        System.out.print(SEPARADOR_CORTO + MENSAJE_SALIDA);

        sc.close();

    } //FIN MAIN

    //-----------------| MÉTODOS & FUNCIONES |--------------------

    // Verifica si el usuario y la contraseña son correctos
    public static boolean  verificarUsuario(int dni, String clave, int usuario, String contrasenia) {
        if (dni == usuario && clave.equalsIgnoreCase(contrasenia)) {
            return true;
        }
        return false;
    }

    // Verifica si la elección del menú (switch) es válida
    public static boolean opcionValida(short opcion) {
        
        if (opcion < 1 || opcion > 5) {
            return false;
        } else {
            return true;
        }
    }

    // Verifica si se quiere realizar otra operación/consulta
    public static boolean finalizar(String respuestaUsuario) {
        if (respuestaUsuario.equalsIgnoreCase("NO")) {
            return true;
        }
        return false;
    } 

    // Verifica si la respuesta es (SI/NO) 
    public static boolean respuestaValidaSiNo(String respuesta) {
        if (respuesta.equalsIgnoreCase("SI") || respuesta.equalsIgnoreCase("NO")) {
            return true;
        } else {
            return false;
        }
    }


}
