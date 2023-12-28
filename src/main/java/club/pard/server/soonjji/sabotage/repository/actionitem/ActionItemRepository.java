package club.pard.server.soonjji.sabotage.repository.actionitem;

import org.springframework.data.jpa.repository.JpaRepository;

import club.pard.server.soonjji.sabotage.entity.actionitem.ActionItem;

public interface ActionItemRepository extends JpaRepository<ActionItem, Long> {
    
}
