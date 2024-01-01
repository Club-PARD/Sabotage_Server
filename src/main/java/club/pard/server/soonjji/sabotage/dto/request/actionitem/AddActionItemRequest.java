package club.pard.server.soonjji.sabotage.dto.request.actionitem;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddActionItemRequest {
    @NotNull
    private String category;
    private String content;
}
