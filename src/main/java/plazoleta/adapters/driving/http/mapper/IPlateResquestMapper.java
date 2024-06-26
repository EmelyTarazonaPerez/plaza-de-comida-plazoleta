package plazoleta.adapters.driving.http.mapper;

import org.mapstruct.*;
import plazoleta.adapters.driving.http.dto.request.plate.AddPlateRequest;
import plazoleta.adapters.driving.http.dto.response.PlateDto;
import plazoleta.domain.model.plate.Plate;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IPlateResquestMapper {
    @Mappings(value = {
            @Mapping(target = "id", ignore = true),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "categoryId", target = "categoryId"),
            @Mapping(source = "description", target = "description"),
            @Mapping(source = "price", target = "price"),
            @Mapping(source = "restaurantId", target = "restaurantId"),
            @Mapping(source = "imageUrl", target = "imageUrl"),
            @Mapping(source = "active", target = "active"),
    })
    Plate toPlate (AddPlateRequest plateRequest);

    @InheritInverseConfiguration
    PlateDto toPlateDto(Plate plate);

    List<PlateDto> toListPlateDto(List<Plate> listPlate);

}
