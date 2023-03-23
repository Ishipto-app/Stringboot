package user.example.usermanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import user.example.usermanager.model.Image;

public interface ImageRepository extends JpaRepository<Image, Integer> {

}