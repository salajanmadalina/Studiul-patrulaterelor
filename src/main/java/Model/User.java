package Model;

public class User{
    private String nume;
    private int id;
    private String password;
    private String rol;

    public User(String nume, String password, String rol, int id) {
        this.nume = nume;
        this.password = password;
        this.rol = rol;
        this.id = id;
    }

    public User(){}

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "User{" +
                "nume='" + nume + '\'' +
                ", id=" + id +
                ", password=" + password +
                ", rol=" + rol +
                '}' + "\n";
    }
}
