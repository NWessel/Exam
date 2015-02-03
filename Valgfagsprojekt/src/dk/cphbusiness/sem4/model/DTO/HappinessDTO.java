package dk.cphbusiness.sem4.model.DTO;

public class HappinessDTO implements Comparable<Object> {
    
    private String email;
    private int score;

    public HappinessDTO(String email, int score) {
        this.email = email;
        this.score = score;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public int compareTo(Object o) {
        HappinessDTO temp = (HappinessDTO)o;
        
        if (temp.getScore() == this.score){
            return 0;
        }
        
        if (temp.getScore() > this.score){
            return -1;
        }
        
        if (temp.getScore() < this.score){
            return 1;
        }
        return -2;
    }
}
