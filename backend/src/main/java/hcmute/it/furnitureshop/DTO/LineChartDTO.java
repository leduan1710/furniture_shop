package hcmute.it.furnitureshop.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LineChartDTO implements Comparable<LineChartDTO>{
    private Integer productId;
    private Long revenue;

    @Override
    public int compareTo(LineChartDTO other) {
        return other.getRevenue().compareTo(this.revenue);
    }
}
