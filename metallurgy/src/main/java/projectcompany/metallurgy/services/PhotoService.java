package projectcompany.metallurgy.services;

import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import projectcompany.metallurgy.entities.Photo;
import projectcompany.metallurgy.repositories.PhotoRepository;

@Service
public class PhotoService {
    
    private final PhotoRepository photoRepository;

    @Autowired
    public PhotoService(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    @Transactional
    public Photo save(MultipartFile image) throws Exception {
        if (image != null && !image.isEmpty()) {
            try {
                Photo photo = new Photo();
                photo.setMime(image.getContentType());
                photo.setName(image.getName());
                photo.setContents(image.getBytes());
                return photoRepository.save(photo);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return null;
    }

    @Transactional
    public Photo modify(String idPhoto, MultipartFile image) throws Exception {
        if (image != null) {
            Photo photo = new Photo();
            if (idPhoto != null) {
                Optional<Photo> answer = photoRepository.findById(idPhoto);
                if (answer.isPresent()) {
                    photo = answer.get();
                }
            }
            photo.setMime(image.getContentType());
            photo.setName(image.getName());
            photo.setContents(image.getBytes());
            return photoRepository.save(photo);
        } else {
            return null;
        }
    }
}
