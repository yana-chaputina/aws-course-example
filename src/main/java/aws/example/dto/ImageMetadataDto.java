package aws.example.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ImageMetadataDto {

    public String name;

    public Long sizeInBytes;

    public String fileExtension;

    public LocalDateTime lastUpdate;

}
