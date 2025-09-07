import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.io.IOException;

@RestController
public class ImageEncryptionController {

    @PostMapping("/process")
    public ResponseEntity<byte[]> processImage(@RequestParam("file") MultipartFile file,
                                               @RequestParam("key") int key) throws IOException {
        byte[] data = file.getBytes();

        // XOR logic for both encryption and decryption
        for (int i = 0; i < data.length; i++) {
            data[i] = (byte) (data[i] ^ key);
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=result.png")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(data);
    }
}
