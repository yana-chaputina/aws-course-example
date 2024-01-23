package aws.example.service;

import aws.example.exception.S3ObjectNotFoundException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class S3Service {

    public static final String S3_BUCKET_NAME = "ianapriazhkinatestbucket";
    private final AmazonS3 amazonS3;

    private final FileService fileService;

    @Autowired
    public S3Service(AmazonS3 amazonS3, FileService fileService) {
        this.amazonS3 = amazonS3;
        this.fileService = fileService;
    }

    public byte[] downloadObject(String objectName) {
        checkBucketExists();
        checkObjectExits(objectName);

        S3Object o = amazonS3.getObject(S3_BUCKET_NAME, objectName);
        return fileService.readBitmap(o);
    }

    public void uploadObject(InputStream file, String filename, String customName) {
        checkBucketExists();
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.addUserMetadata("Name", filename);
        metadata.setContentType("image/jpg");
        PutObjectRequest request = new PutObjectRequest(S3_BUCKET_NAME, customName, file, metadata);
        request.setMetadata(metadata);
        amazonS3.putObject(request);
    }

    public void deleteObject(String objectName) {
        checkBucketExists();
        checkObjectExits(objectName);
        amazonS3.deleteObject(S3_BUCKET_NAME, objectName);
    }

    private void checkBucketExists() {
        if (!amazonS3.doesBucketExistV2(S3_BUCKET_NAME)) {
            amazonS3.createBucket(S3_BUCKET_NAME);
        }
    }

    private void checkObjectExits(String objectName) {
        if (!amazonS3.doesObjectExist(S3_BUCKET_NAME,objectName)) {
            throw new S3ObjectNotFoundException("Object not found in S3 bucket");
        }
    }
}
