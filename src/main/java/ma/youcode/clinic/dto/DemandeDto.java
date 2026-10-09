package ma.youcode.clinic.dto;

public record DemandeDto(
        int id,
        String question,
        String priorite,
        String status,
        String opinion,
        String recommendations,
        String dateCreation,
        int consultationId,
        int specialistId) {
}