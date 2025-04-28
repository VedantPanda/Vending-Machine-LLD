package main.entities;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Inventory {

    private final Map<Integer, Queue<Product>> productInventoryMap;

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

    public int getProductPrice(int productId) throws Exception {
        if(isProductAvailable(productId) && !productInventoryMap.get(productId).isEmpty()) {
            return productInventoryMap.get(productId).peek().getPrice();
        }
        else {
            throw new Exception("Product: "+productId+" does not exist");
        }
    }

    public Product getProduct(int productId) throws Exception{
        if(isProductAvailable(productId)) {
            return productInventoryMap.get(productId).peek();
        }
        else {
            throw new Exception("Product with id: "+productId+" is not available");
        }
    }

    public boolean isProductAvailable(int productId) {
        return productInventoryMap.containsKey(productId);
    }

    public Product dispenseProduct(Product currentProduct) throws Exception {
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
