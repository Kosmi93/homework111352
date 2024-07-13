package bip.online.homework111352.service;

import org.imgscalr.Scalr;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

@Service
public class ImageResize {

    public long resize(MultipartFile imageFile, Path pathFile,String avatarExtensions) throws IOException {
        BufferedImage image = ImageIO.read(imageFile.getInputStream());
        BufferedImage newImage = Scalr.resize(image, 1920, 1080);
        File newImageFile = new File(String.valueOf(pathFile));
        ImageIO.write(newImage, avatarExtensions, newImageFile);
        return newImageFile.length();
    }

}
