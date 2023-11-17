package hcmute.it.furnitureshop.Common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseModel<T> {
    private T object =  null;
    private String status;
    private String message;
}
