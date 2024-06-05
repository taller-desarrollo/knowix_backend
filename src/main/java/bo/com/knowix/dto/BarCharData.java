package bo.com.knowix.dto;

import java.math.BigDecimal;

public class BarCharData {
    private String label;
    private Long y;

    private BigDecimal earnings;

    public BarCharData() {
    }

    public BarCharData(String label, Long y, BigDecimal standardPrice) {
        this.label = label;
        this.y = y;
        this.earnings = standardPrice;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Long getY() {
        return y;
    }

    public void setY(Long y) {
        this.y = y;
    }

    public BigDecimal getEarnings() {
        return earnings;
    }

    public void setEarnings(BigDecimal earnings) {
        this.earnings = earnings;
    }
}
