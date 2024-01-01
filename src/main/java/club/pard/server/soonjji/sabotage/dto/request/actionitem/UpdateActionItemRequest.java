package club.pard.server.soonjji.sabotage.dto.request.actionitem;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UpdateActionItemRequest {
    @NotBlank(message = "Category should not be null or empty")
    private String category;
    @NotBlank(message = "Content should not be null or empty")
    private String content;
}