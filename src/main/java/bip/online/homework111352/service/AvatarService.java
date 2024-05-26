package bip.online.homework111352.service;

import bip.online.homework111352.model.Avatar;
import bip.online.homework111352.model.Student;
import bip.online.homework111352.repo.AvatarRepo;
import bip.online.homework111352.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Optional;

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
        return avatarRepo.findByStudent_Id(id).orElse(new Avatar());
    }

    private String getExtensions(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    /*public Collection<Avatar> pagination(int page, int size){
        return avatarRepo.findAllPagination(page,size);
    }*/
    public Page<Avatar> paginationV2(int page, int size){
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return avatarRepo.findAll(pageRequest);
    }
}
