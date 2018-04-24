package practice2;

import java.math.BigDecimal;
import java.util.List;

public class Receipt {

    public Receipt() {
        tax = new BigDecimal(0.1);
        tax = tax.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    private BigDecimal tax;

    public double CalculateGrandTotal(List<Product> products, List<OrderItem> items) {
        BigDecimal subTotal = calculateSubtotal(products, items);
        BigDecimal taxTotal = subTotal.multiply(tax);
        BigDecimal grandTotal = subTotal.add(taxTotal);
        return grandTotal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    private OrderItem findOrderItemByProduct(List<OrderItem> items, Product product) {
        return items.stream().filter(m-> m.getCode() == product.getCode()).findFirst().get();
    }

    private BigDecimal calculateTotal(List<Product>products,List<OrderItem> items){
        return products.stream().map(m -> m.getPrice().multiply(new BigDecimal(findOrderItemByProduct(items,m).getCount()))).reduce(BigDecimal.ZERO,(a,b)->a.add(b));
    }

    private BigDecimal calculateReduceTatal(List<Product>products,List<OrderItem>items){
        return products.stream().map(m ->m.getPrice().multiply(m.getDiscountRate()).multiply(new BigDecimal(findOrderItemByProduct(items,m).getCount()))).reduce(BigDecimal.ZERO,(a,b)->a.add(b));
    }

    private BigDecimal calculateSubtotal(List<Product> products, List<OrderItem> items) {
        BigDecimal ratio = new BigDecimal(1);
       return products.stream().map(m ->m.getPrice().multiply(ratio.subtract(m.getDiscountRate())).multiply(new BigDecimal(findOrderItemByProduct(items,m).getCount()))).reduce(BigDecimal.ZERO,(a,b)->a.add(b));
    }
}
