package ma.youcode.clinic.dto;

public record DemandeDto(
        int id,
        String question,
        String priorite,
        String status,
        String avis,
        String recommendations,
        String dateCreation,
        int consultationId,
        int specialistId) {
}