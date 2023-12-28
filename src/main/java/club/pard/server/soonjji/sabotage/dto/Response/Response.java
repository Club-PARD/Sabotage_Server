package club.pard.server.soonjji.sabotage.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "set")
public class Response<D> {
    private boolean result;
    private String message;
    private D data;

    public static <D> Response<D> setSuccess(String message, D data){ return Response.set(true, message, data); }
    public static <D> Response<D> setFailure(String message){ return Response.set(false, message, null); }
}
