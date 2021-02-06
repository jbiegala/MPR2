package pl.pjatk.pozyczto.util;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import pl.pjatk.pozyczto.model.Product;
import pl.pjatk.pozyczto.model.ProductDTO;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProductFromDTO(ProductDTO productDTO, @MappingTarget Product product);
}
