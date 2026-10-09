package ma.youcode.clinic.dto;

public class ReponseDto {

    private String opinion;
    private String recommendations;

    public ReponseDto() {
    }

    public ReponseDto(String opinion, String recommendations) {
        this.opinion = opinion;
        this.recommendations = recommendations;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public String getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(String recommendations) {
        this.recommendations = recommendations;
    }
}