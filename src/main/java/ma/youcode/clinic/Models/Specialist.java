package ma.youcode.clinic.Models;


public class Specialist {
    private int id;
    private int userId;
    private int rate;
    private SpecialistList role;

    
    public enum SpecialistList { CARDIOLOGIE, PNEUMOLOGIE, DERMATOLOGIE, NEUROLOGIE, ENDOCRINOLOGIE }

    
    public Specialist(int id, int userId, int rate, SpecialistList role) {
        this.id = id;
        this.userId = userId;
        this.rate = rate;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public SpecialistList getRole() {
        return role;
    }

    public void setRole(SpecialistList role) {
        this.role = role;
    }
    
}
