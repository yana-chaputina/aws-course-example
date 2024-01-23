package aws.example.service;

import aws.example.exception.FileException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@Service
public class FileService {

    public InputStream getInputStream(MultipartFile multipartFile) {
        InputStream inputStream;
        try {
            inputStream = multipartFile.getInputStream();
        } catch (IOException e) {
            throw new FileException("Can not open InputStream from MultipartFile");
        }
        return inputStream;
    }

    public String getFileExtension(String fileName) {
        Optional<String> fileExtensionOpt = Optional.of(fileName)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(fileName.lastIndexOf(".") + 1));
        return fileExtensionOpt.orElseThrow(() -> new FileException("Can not get file extension"));
    }

    public byte[] readBitmap(S3Object s3Object) {
        byte[] objectBytes = new byte[1024];
        try {
            S3ObjectInputStream s3is = s3Object.getObjectContent();
            ByteArrayOutputStream os = new ByteArrayOutputStream();

            byte[] read_buf = new byte[1024];
            int read_len;
            while ((read_len = s3is.read(read_buf)) > 0) {
                os.write(read_buf, 0, read_len);
            }
            s3is.close();
            os.close();
            objectBytes = os.toByteArray();
        } catch (AmazonServiceException e) {
            throw new FileException("Can not read bitmap from S3 object");
        } catch (IOException e) {
            throw new FileException("Can not read ByteArrayOutputStream from S3 object");
        }
        return objectBytes;
    }
}
