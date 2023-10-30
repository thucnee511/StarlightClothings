/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package starlight.model.shop;

/**
 *
 * @author Admin
 */
public class CategoryDTO {

    private String cateID;
    private String name;

    public CategoryDTO() {
    }

    public CategoryDTO(String cateID, String name) {
        this.cateID = cateID;
        this.name = name;
    }

    public String getCateID() {
        return cateID;
    }

    public void setCateID(String cateID) {
        this.cateID = cateID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CategoryDTO{" + "cateID=" + cateID + ", name=" + name + '}';
    }

}
