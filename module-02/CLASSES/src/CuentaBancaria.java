public class CuentaBancaria {
   private String numeroCUenta;
   private  String nombreTitular;
   private  Double saldo;
   double nuevoSaldo;

// constuctores
   public CuentaBancaria(String nombreTitular,String numeroCUenta) {
        this.nombreTitular = nombreTitular;
        this.numeroCUenta = numeroCUenta;
        saldo = 0.0;
    }


   public CuentaBancaria(String nombreTitular,String numeroCUenta, Double saldo) {
    this.nombreTitular = nombreTitular;
    this.numeroCUenta = numeroCUenta;
    this.saldo = saldo;
   }

   public void mostrarInformacion(){
    System.out.printf("""
            Nombre del titular:%s
            Numero de Cuenta: %s
            Saldo: %.2f%n
            """,getNombreTitular(),getNumeroCUenta(),getSaldo());
   }

   
  
   
// getters
   public String getNumeroCUenta() {
    return numeroCUenta;
   }


   public String getNombreTitular() {
    return nombreTitular;
   }


   public Double getSaldo() {
    return saldo;
   }

   
//setter


   public void setSaldo(Double saldo) {
    this.saldo = saldo;
}


  
   public void setNumeroCUenta(String numeroCUenta) {
    this.numeroCUenta = numeroCUenta;
}


   public void setNombreTitular(String nombreTitular) {
    this.nombreTitular = nombreTitular;
   }

// metodos de retirar y depositar dinero 

   public void depositar(double monto){
    if (monto<0) {
        System.out.println("Error: el monto debe de ser positivo");
    } else {
        nuevoSaldo= saldo +monto;
        setSaldo(nuevoSaldo);
        System.out.println(" -:Deposito exitoso:-");
        System.out.printf("Nuevo saldo: %.2f%n",nuevoSaldo);}
    }

    public void retirar(double monto){
    if ((monto<0)&&(saldo<0)) { // valida que el monto y el saldo sean valores positivos
        System.out.println("Error: el monto debe de ser positivo");
    } else { 
        nuevoSaldo= saldo -monto;
        if (nuevoSaldo>0) { // valida que haya fondos para relaizar el retiro
            setSaldo(nuevoSaldo);
            System.out.println(" -:Retiro exitoso:-");
            System.out.printf("Nuevo saldo: %.2f%n",nuevoSaldo);
        } else {
            System.out.println("Error: No posee los fondos para realizar el retiro");    
        }
        } 
    }
}



