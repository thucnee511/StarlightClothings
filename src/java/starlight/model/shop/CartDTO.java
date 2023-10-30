/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package starlight.model.shop;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class CartDTO {

    private List<ProductDTO> cart = new ArrayList<>();

    public CartDTO() {
    }

    public void add(ProductDTO p) {
        for (ProductDTO pro : cart) {
            if (p.getProductID().equals(pro.getProductID())) {
                pro.setQuantity(pro.getQuantity() + p.getQuantity());
                return;
            }
        }
        cart.add(p);
    }

    public List<ProductDTO> getCart() {
        return cart;
    }

}
