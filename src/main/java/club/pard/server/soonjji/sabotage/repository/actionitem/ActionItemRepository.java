package club.pard.server.soonjji.sabotage.repository.actionitem;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import club.pard.server.soonjji.sabotage.entity.actionitem.ActionItem;

public interface ActionItemRepository extends JpaRepository<ActionItem, Long> {
    public boolean existsByUserIdAndCategoryAndContent(Long userId, String targetCategory, String targetContent);
    public List<ActionItem> findAllByUserIdOrderByIdAsc(Long userId);
    public ActionItem findFirst1ByUserIdOrderByExposureCountAsc(Long userId);
}
