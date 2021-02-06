package pl.pjatk.pozyczto.util;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import pl.pjatk.pozyczto.model.Rent;
import pl.pjatk.pozyczto.model.RentDTO;

@Mapper(componentModel = "spring")
public interface RentMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateRentFromDTO(RentDTO rentDTO, @MappingTarget Rent rent);
}
