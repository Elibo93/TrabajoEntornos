
public class CuentaBancaria {

    static int numCuenta = 0;

    // ATRIBUTOS
    private String nombre, apellidos;
    private int dni;
    private double saldo;

    // CONSTRUCTOR

    public CuentaBancaria(String nombre, String apellidos, int dni, double saldo, int numCuenta) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.saldo = saldo;
        this.numCuenta = numCuenta;
    }

    public CuentaBancaria(String nombre, String apellidos, int dni, int numCuenta) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.saldo = 0;
        this.numCuenta = numCuenta;
    }

    // MÉTODOS

    public double ingresarDinero(double monto) {
        this.saldo += monto;
        return saldo;
    }

    public double retirarDinero(double monto) {
        double montoDespuesIngresar = -1;
        if (monto >= this.saldo ) {
            this.saldo -= monto;
            montoDespuesIngresar = this.saldo;
        }
        return montoDespuesIngresar;
    }

    public double transferirDinero(CuentaBancaria c2, double monto) {
        c2.retirarDinero(monto);
        ingresarDinero(monto);
        return this.saldo;
    }

    public void consultarCuentas() {

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
    public int  getDni() {
        return dni;
    }
    public void setDni(int dni) {
        this.dni = dni;
    }
    public double getSaldo() {
        return saldo;
    }
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    public int getNumCuenta() {
        return numCuenta;
    }
    public void setNumCuenta(short numCuenta) {
        this.numCuenta = numCuenta;
    }

    @Override
    public String toString() {
        return "Cuenta con saldo: " + this.saldo;
    }
}
