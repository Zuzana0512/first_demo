package nl.novi.first_demo.dto;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

public class OrderRequestDto {

    @NotBlank
    private Date date;

    @NotBlank
    private long customerId;

    @NotBlank
    private List<OrderRowDto> orderRowDtos;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public List<OrderRowDto> getOrderRowDtos() {
        return orderRowDtos;
    }

    public void setOrderRowDtos(List<OrderRowDto> orderRowDtos) {
        this.orderRowDtos = orderRowDtos;
    }
}

