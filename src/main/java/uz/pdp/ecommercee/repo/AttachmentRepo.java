package uz.pdp.ecommercee.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.ecommercee.entity.Attachment;
import uz.pdp.ecommercee.entity.Category;

import java.util.UUID;

public interface AttachmentRepo extends JpaRepository<Attachment, UUID> {
}
