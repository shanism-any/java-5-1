package practice3;

import java.math.BigDecimal;
import java.util.List;
public class PriceCaculator {
    public  BigDecimal calculateOrderLineTotal(List<OrderLineItem> orderLineItemList){
        return orderLineItemList.stream().map(m -> m.getPrice()).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public  BigDecimal calculateDiscountTotal(List<BigDecimal> discounts){
        return discounts.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal calculateGrandTotal(BigDecimal subTotal, BigDecimal tax){
        return   subTotal.multiply(tax.add(new BigDecimal(1)));
    }
}
