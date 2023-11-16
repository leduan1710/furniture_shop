package hcmute.it.furnitureshop.Common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Type;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseModel<T> {
    private T object;
    private String status;
    private String message;
}
