package club.pard.server.soonjji.sabotage.repository.actionitem;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import club.pard.server.soonjji.sabotage.entity.actionitem.ActionItem;

public interface ActionItemRepository extends JpaRepository<ActionItem, Long> {
    public boolean existsByCategoryAndContent(String targetCategory, String targetContent);
    public List<ActionItem> findAllByUserId(Long userId);

    @Query("select i from ActionItem i where i.user = ?1 order by i.exposureCount fetch first ?2 rows only")
    public List<ActionItem> findNLeastExposedActionItems(Long targetUserId, int length);
}
