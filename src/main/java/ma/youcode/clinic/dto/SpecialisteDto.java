package ma.youcode.clinic.dto;

import ma.youcode.clinic.Models.Specialist;

public record SpecialisteDto(int id, String name, Specialist.SpecialistList specialisty, int tarif) {
}