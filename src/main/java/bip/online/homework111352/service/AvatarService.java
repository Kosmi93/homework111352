package bip.online.homework111352.service;

import bip.online.homework111352.model.Avatar;
import bip.online.homework111352.model.Student;
import bip.online.homework111352.repo.AvatarRepo;
import bip.online.homework111352.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Optional;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
public class AvatarService {
    @Value("${path.to.avatars.folder}")
    private String avatarsDir;
    private final ImageResize imageResize;

    private final AvatarRepo avatarRepo;
    private final StudentRepo studentRepo;

    public AvatarService(ImageResize imageResize, AvatarRepo repo, StudentRepo studentRepo) {
        this.imageResize = imageResize;
        this.avatarRepo = repo;
        this.studentRepo = studentRepo;
    }

    public Optional<Avatar> findById(Long id) {
        return avatarRepo.findById(id);
    }

    public Collection<Avatar> getAll() {
        return avatarRepo.findAll();
    }

    public void delete(Long id) {
        avatarRepo.deleteById(id);
    }


    public void uploadAvatar(Long studentId, MultipartFile avatarFile) throws IOException {
        Student student = studentRepo.findById(studentId).orElseThrow();
        String avatarExtension = getExtensions(avatarFile.getOriginalFilename());
        Path filePath = Path.of(   avatarsDir, student + "." + avatarExtension);
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        long fileSize = imageResize.resize(avatarFile,filePath,avatarExtension);
        /*try (
                InputStream is = avatarFile.getInputStream();
                OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                BufferedOutputStream bos = new BufferedOutputStream(os, 1024)
        ) {
            bis.transferTo(bos);
        }*/
        Avatar avatar = findAvatar(studentId);
        avatar.setStudent(student);
        avatar.setFilePath(filePath.toString());
        avatar.setFileSize(fileSize);
        avatar.setMediaType(avatarFile.getContentType());
        avatar.setData(avatarFile.getBytes());
        avatarRepo.save(avatar);

    }

    public Avatar findAvatar(Long id) {
        return avatarRepo.findByStudent_Id(id).orElse(new Avatar());
    }

    private String getExtensions(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
}
