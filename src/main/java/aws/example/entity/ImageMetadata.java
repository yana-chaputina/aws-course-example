package aws.example.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;


@Entity
@Table(name = "image")
@Data
public class ImageMetadata {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "name")
    public String name;

    @Column(name = "size")
    public Long sizeInBytes;

    @Column(name = "file_extension")
    public String fileExtension;

    @Column(name = "last_update",
            columnDefinition = "TIMESTAMP",
            insertable = true,
            updatable = true)
    public LocalDateTime lastUpdate;

}
