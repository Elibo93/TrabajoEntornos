package cuenta;


public class CuentaBancaria {

    // static int numCuenta = 0;
    // static int numUsuario = 0;

    // ATRIBUTOS
    private String nombre, apellidos, contrasenia, dni;
    private int idCuenta, idUsuario;
    private double saldo;

    // CONSTRUCTORES

    // Constructor para crear un nuevo usuario
    public CuentaBancaria(String nombre, String apellidos, String dni, int idUsuario, String contrasenia) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.contrasenia = contrasenia;
        this.idUsuario = idUsuario;
    }

    // Constructor para crear una nueva cuenta con un monto inicial
    public CuentaBancaria(String nombre, String apellidos, String dni, double saldo, int idCuenta) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.saldo = saldo;
        this.idCuenta = idCuenta;
    }

    // Constructor para crear una nueva cuenta sin un importe inicial
    public CuentaBancaria(String nombre, String apellidos, String dni, int idCuenta) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.saldo = 0;
        this.idCuenta = idCuenta;
    }

    // MÉTODOS

    public double ingresarDinero(double monto) {
        this.saldo += monto;
        return this.saldo;
    }

    public double retirarDinero(double monto) {
        double montoDespuesIngresar = -1;
        if (monto <= this.saldo ) {
            this.saldo -= monto;
            montoDespuesIngresar = this.saldo;
        }
        return montoDespuesIngresar;
    }

    public double transferirDinero(CuentaBancaria c2, double monto) {
        double montoDespuesTransferir = -1;
        if (retirarDinero(monto) != -1) {
            montoDespuesTransferir = c2.ingresarDinero(monto);
        }
        return montoDespuesTransferir;
    }
    
    public boolean verificarAcceso(String dni, String contrasenia) {
        boolean verificacion = false;
        if (this.contrasenia.equals(contrasenia) && this.dni.equals(dni)){
            verificacion = true;
        } 
        return verificacion;
    }
    // GETTERS & SETTERS
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellidos() {
        return apellidos;
    }
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    public String getDni() {
        return dni;
    }
    public void setDni(String dni) {
        this.dni = dni;
    }
    public double getSaldo() {
        return saldo;
    }
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    public int getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(int idUsuario) {
        this.idCuenta = idUsuario;
    }
    public int getIdCuenta() {
        return idCuenta;
    }
    public void setIdCuenta(int idCuenta) {
        this.idCuenta = idCuenta;
    }
    public String getContrasenia() {
        return contrasenia;
    }
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    // public static int getNumCuenta() {
    //     return numCuenta;
    // }

    // public static void setNumCuenta(int numCuenta) {
    //     CuentaBancaria.numCuenta = numCuenta;
    // }

    // public static int getNumUsuario() {
    //     return numUsuario;
    // }

    // public static void setNumUsuario(int numUsuario) {
    //     CuentaBancaria.numUsuario = numUsuario;
    // }


    // Presentación a String de esta clase(CuentaBancaria)
    @Override
    public String toString() {
        return "Cuenta con saldo: " + this.saldo + " EUROS";
    }
}
