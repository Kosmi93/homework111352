package bip.online.homework111352.service;

import bip.online.homework111352.model.Avatar;
import bip.online.homework111352.model.Student;
import bip.online.homework111352.repo.AvatarRepo;
import bip.online.homework111352.repo.StudentRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class AvatarService {
    @Value("${path.to.avatars.folder}")
    private String avatarsDir;
    private final ImageResize imageResize;
    private final AvatarRepo avatarRepo;
    private final StudentRepo studentRepo;
    private Logger logger = LoggerFactory.getLogger(AvatarService.class);



    public AvatarService(ImageResize imageResize, AvatarRepo repo, StudentRepo studentRepo) {
        this.imageResize = imageResize;
        this.avatarRepo = repo;
        this.studentRepo = studentRepo;
    }

    public Optional<Avatar> findById(Long id) {
        logger.info("Поиск аватара по id");
        return avatarRepo.findById(id);
    }

    public Collection<Avatar> getAll() {
        logger.info("Получение всех аватаров");
        return avatarRepo.findAll();
    }

    public void delete(Long id) {
        logger.info("Удаление аватара по id");
        avatarRepo.deleteById(id);
    }


    public void uploadAvatar(Long studentId, MultipartFile avatarFile) throws IOException {
        logger.info("Обновление аватара");
        Student student = studentRepo.findById(studentId).orElseThrow();
        String avatarExtension = getExtensions(avatarFile.getOriginalFilename());
        Path filePath = Path.of(avatarsDir, student + "." + avatarExtension);
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        long fileSize = imageResize.resize(avatarFile, filePath, avatarExtension);
        Avatar avatar = findAvatar(studentId);
        avatar.setStudent(student);
        avatar.setFilePath(filePath.toString());
        avatar.setFileSize(fileSize);
        avatar.setMediaType(avatarFile.getContentType());
        avatar.setData(avatarFile.getBytes());
        avatarRepo.save(avatar);
    }

    public Avatar findAvatar(Long id) {
        logger.info("Поиск аватара по id студента");
        return avatarRepo.findByStudent_Id(id).orElse(new Avatar());
    }

    private String getExtensions(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    public List<Avatar> pagination(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return avatarRepo.findAll(pageRequest).getContent();
    }
}
