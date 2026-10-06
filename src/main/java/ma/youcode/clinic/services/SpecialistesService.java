package ma.youcode.clinic.services;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import ma.youcode.clinic.DAO.SpecialistJDBC;
import ma.youcode.clinic.DAO.UserJDBC;
import ma.youcode.clinic.Models.Specialist;
import ma.youcode.clinic.Models.User;
import ma.youcode.clinic.dto.SpecialisteDto;

public class SpecialistesService {
    private final SpecialistJDBC Specialistjdbc = new SpecialistJDBC();
    private final UserJDBC Userjdbc = new UserJDBC();

    public List<SpecialisteDto> lister(String specialite) {
    Specialist.SpecialistList sp;
    try{
        sp = Specialist.SpecialistList.valueOf(specialite.toUpperCase());
    }catch(IllegalArgumentException e) {
        throw new IllegalArgumentException("Invalid specialite: " + specialite);
    }

    Predicate<Specialist> filter = new Predicate<Specialist>() {
        public boolean test(Specialist s) {
            return s.getRole() == sp;
        };
    };
    Comparator<Specialist> sort = new Comparator<Specialist>() {
        @Override
        public int compare(Specialist o1, Specialist o2) {
            // TODO Auto-generated method stub
            return Integer.compare(o1.getRate(), o2.getRate());
        }
    };

    Function<Specialist, SpecialisteDto> map = new Function<Specialist, SpecialisteDto>() {
        public SpecialisteDto apply(Specialist s) {
            return new SpecialisteDto(
            s.getId(),
            getName(s),
            s.getRole(),
            s.getRate()
        );
        };
    };


    return Specialistjdbc.findAll().stream().filter(filter).sorted(sort).map(map).toList();
}

    private String getName(Specialist s) {
        return Userjdbc.findById(s.getUserId())
                .map(User::getName)
                .orElse("Unknown");
    }
}
