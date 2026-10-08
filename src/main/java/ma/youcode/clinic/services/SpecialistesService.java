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

        if (specialite == null) {
            throw new BadRequestException("Specialty is required");
        }

        Specialist.SpecialistList sp;
        try {
            sp = Specialist.SpecialistList.valueOf(specialite.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Unknown specialty: " + specialite);
        }

        Predicate<Specialist> hasSpecialty = s -> s.getRole() == sp;

        Comparator<Specialist> byRate = Comparator.comparingInt(Specialist::getRate);

        Function<Specialist, SpecialisteDto> toDto = s -> new SpecialisteDto(
                s.getId(),
                s.getUser().getName(),
                s.getRole(),
                s.getRate());

        return specialistRepository.findAll().stream()
                .filter(hasSpecialty)
                .sorted(byRate)
                .map(toDto)
                .toList();
    }
}