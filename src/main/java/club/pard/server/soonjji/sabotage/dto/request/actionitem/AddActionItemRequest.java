package club.pard.server.soonjji.sabotage.dto.request.actionitem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddActionItemRequest {
    private String category;
    private String content;
}
