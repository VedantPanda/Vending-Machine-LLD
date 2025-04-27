package main.entities;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Inventory {

    private final Map<Integer, Queue<Product>> productInventoryMap;

    private Product currentProduct;

    public Inventory() {
        productInventoryMap = new HashMap<>();
    }

    public void addProduct(Product product) {
        if(productInventoryMap.containsKey(product.getProductId())) {
            productInventoryMap.get(product.getProductId()).add(product);
        }
        else {
            Queue<Product> productQueue = new LinkedList<>();
            productQueue.add(product);
            productInventoryMap.put(product.getProductId(), productQueue);
        }
    }

    public void selectProduct(int productId) throws Exception{
        this.currentProduct = getProduct(productId);
    }

    public int getProductPrice(int productId) throws Exception {
        Product product = getProduct(productId);
        return product == null ? 0 : product.getPrice();
    }

    public Product getProduct(int productId) {
        if(productInventoryMap.containsKey(productId)) {
            return productInventoryMap.get(productId).poll();
        }
        else{
            System.out.println("Product with id: "+productId+" does not exist");
            return null;
        }
    }

    public Product dispenseProduct() throws Exception {
        if(currentProduct == null) {
            throw new Exception("Product is not available...invalid product");
        }
        int productId = currentProduct.getProductId();
        productInventoryMap.get(productId).poll();
        if(productInventoryMap.get(productId).isEmpty()) {
            productInventoryMap.remove(productId);
        }
        System.out.println("Product with id: "+productId+" is dispensed successfully");
        return currentProduct;
    }

}
