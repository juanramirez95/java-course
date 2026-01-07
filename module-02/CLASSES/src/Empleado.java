
public class Empleado {
    // Atributos
    private String nombreCompleto;
    private String idEmpleado;
    private Double salarioMensual;
    private String departamento;
    
    //Constructores
    public Empleado(String nombreCompleto, String idEmpleado, String departamento) {
        this.nombreCompleto = nombreCompleto.toUpperCase();
        this.idEmpleado = idEmpleado;
        salarioMensual = 0.0;
        this.departamento = departamento.toUpperCase();
    }
    public Empleado(String nombreCompleto, String idEmpleado, Double salarioMensual, String departamento) {
        this.nombreCompleto = nombreCompleto.toUpperCase();
        this.idEmpleado = idEmpleado;
        this.salarioMensual = salarioMensual;
        this.departamento = departamento.toUpperCase();
    }

    // getters
    public String getNombreCompleto() {
        return nombreCompleto;
    }
    public String getIdEmpleado() {
        return idEmpleado;
    }
    public Double getSalarioMensual() {
        return salarioMensual;
    }
    public String getDepartamento() {
        return departamento;
    }
    public String mostrarInformacion(){
        return String.format("Nombre: %s%n ID: %s%n Salario: %s%n Departamento: %s ",  getNombreCompleto(),
               getIdEmpleado(),
               getSalarioMensual().toString(),
               getDepartamento());
              
    }
    public Double calcularSalarioAnual( Double salarioMensual){
                var salarioAnual = salarioMensual*12;
                return salarioAnual;
    }

     //setter
    public void setSalarioMensual(Double salarioMensual) {
        
        if (salarioMensual<0) { //valida si el salario es positivo
            System.out.println("<Error, el valor del salario debe de ser positivo>");
            salarioMensual= 0.0;}
        else{
            this.salarioMensual = salarioMensual;}
        
    }
    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    

    

    


   

    
    

    

}
