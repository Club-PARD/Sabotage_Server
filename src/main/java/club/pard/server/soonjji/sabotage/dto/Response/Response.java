package club.pard.server.soonjji.sabotage.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "set")
public class Response<D> {
    private boolean isSuccessful;   // Basic boolean value for call result
    private String message;         // For client developers
    private String comment;         // For server developers
    private D data;                 // What client will receive after request

    public static <D> Response<D> setSuccess(String message, String comment, D data){ return Response.set(true,     message, comment, data); }
    public static <D> Response<D> setFailure(String message, String comment)        { return Response.set(false,    message, comment, null); }
}
