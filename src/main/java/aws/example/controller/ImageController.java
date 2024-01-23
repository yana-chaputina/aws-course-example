package aws.example.controller;

import aws.example.dto.ImageUploadDto;
import aws.example.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/images")
public class ImageController {

    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping(path = "/random")
    public ResponseEntity<?> getRandomMetadata() {
        return new ResponseEntity<>(imageService.getRandomMetadata(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> uploadImage(@ModelAttribute ImageUploadDto imageUploadDto) {
        return new ResponseEntity<>(imageService.upload(imageUploadDto), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(imageService.findAll(), HttpStatus.OK);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<?> delete(@PathVariable String name) {
        imageService.delete(name);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping(value = "/{name}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] download(@PathVariable String name) {
        return imageService.getImage(name);
    }
}
