package aws.example.repository;

import aws.example.entity.ImageMetadata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<ImageMetadata, Long> {

    List<ImageMetadata> findByName(String name);

    @Query(nativeQuery = true, value = "SELECT * FROM image ORDER BY RAND() limit 1;")
    Optional<ImageMetadata> findRandomEntity();
}
