package practice3;

import java.math.BigDecimal;
import java.util.List;
public class Order extends PriceCaculator {

    private List<OrderLineItem> orderLineItemList;
    private List<BigDecimal> discounts;
    private BigDecimal tax;

    public Order(List<OrderLineItem> orderLineItemList, List<BigDecimal> discounts) {
        this.orderLineItemList = orderLineItemList;
        this.discounts = discounts;
        this.tax = new BigDecimal(0.1);
    }

    public BigDecimal calculate() {
        BigDecimal orderLineTotal = calculateOrderLineTotal(orderLineItemList);
        BigDecimal discountTotal = calculateDiscountTotal(discounts);
        BigDecimal subTatal = orderLineTotal.subtract(discountTotal);
        BigDecimal grandTotal = calculateGrandTotal(subTatal,tax);


        return grandTotal;
    }
}
