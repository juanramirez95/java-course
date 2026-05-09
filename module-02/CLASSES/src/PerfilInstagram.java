public class PerfilInstagram {
    
    
    // atributos
    private String username;
    private Integer seguidores;
    private String biografia;

    // contructores

    public PerfilInstagram(String username, String biografia) {
        this.username = username;
        this.seguidores = 0;
        this.biografia = biografia;
    }

    public PerfilInstagram(String username) {
        this.username = username;
        this.seguidores = 100;
        this.biografia = "Hola, bienvenidos a mi mundo de Instagram";
    }

    // metodos getter

public String getUsername() {
    return username;
}

public Integer getSeguidores() {
    return seguidores;
}

public String getBiografia() {
    return biografia;
}
// metodos setter
public void setBiografia(String biografia) {
    this.biografia = biografia;
}



public void setUsername(String newUsername) {
    this.username = newUsername;
}

public void  addFollow(){
this.seguidores++;
}




    

    
    

}
