package ma.youcode.clinic.services;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import jakarta.ws.rs.BadRequestException;
import ma.youcode.clinic.Models.Specialist;
import ma.youcode.clinic.dto.SpecialisteDto;
import ma.youcode.clinic.repository.SpecialistRepository;

public class SpecialistesService {

    private final SpecialistRepository specialistRepository = new SpecialistRepository();

    public List<SpecialisteDto> lister(String specialite) {

        // 1. Check the specialty, otherwise 400
        if (specialite == null) {
            throw new BadRequestException("Specialty is required");
        }

        Specialist.SpecialistList sp;
        try {
            sp = Specialist.SpecialistList.valueOf(specialite.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Unknown specialty: " + specialite);
        }

        // 2. Predicate, Comparator and Function
        Predicate<Specialist> hasSpecialty = s -> s.getRole() == sp;

        Comparator<Specialist> byRate = Comparator.comparingInt(Specialist::getRate);

        Function<Specialist, SpecialisteDto> toDto = s -> new SpecialisteDto(
                s.getId(),
                s.getUser().getName(),
                s.getRole(),
                s.getRate());

        // 3. Filter, sort by fee, convert to DTO
        return specialistRepository.findAll().stream()
                .filter(hasSpecialty)
                .sorted(byRate)
                .map(toDto)
                .toList();
    }
}