package aws.example.orika;

import aws.example.dto.ImageMetadataDto;
import aws.example.entity.ImageMetadata;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageMetadataMapper {

    private static MapperFacade mapperFacade;
    private MapperFactory mapperFactory;

    public ImageMetadataMapper() {
        this.mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(ImageMetadata.class, ImageMetadataDto.class)
                .byDefault()
                .register();
        mapperFactory.classMap(ImageMetadataDto.class, ImageMetadata.class)
                .byDefault()
                .register();
        mapperFacade = mapperFactory.getMapperFacade();
    }

    static public ImageMetadataDto getDtoFromEntity(ImageMetadata imageMetadata) {
        return mapperFacade.map(imageMetadata, ImageMetadataDto.class);
    }

    static public ImageMetadata getEntityFromDto(ImageMetadataDto imageMetadataDto) {
        return mapperFacade.map(imageMetadataDto, ImageMetadata.class);
    }

    static public List<ImageMetadataDto> getListDtoFromEntity(List<ImageMetadata> imageMetadataDtoList) {
        return mapperFacade.mapAsList(imageMetadataDtoList, ImageMetadataDto.class);
    }
}
