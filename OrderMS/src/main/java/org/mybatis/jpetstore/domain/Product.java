package org.mybatis.jpetstore.domain;

import java.io.Serializable;
import java.lang.Object;
import java.lang.String;
import java.lang.Override;

public class Product implements Serializable {
    private static final long serialVersionUID = -7492639752670189553L;
    private String productId;
    private String categoryId;
    private String name;
    private String description;


    static {
    
    }



    public String getProductId() {
    
    return productId;
  
    }

    public void setProductId(String productId) {
    
    this.productId = productId.trim();
  
    }

    public String getCategoryId() {
    
    return categoryId;
  
    }

    public void setCategoryId(String categoryId) {
    
    this.categoryId = categoryId;
  
    }

    public String getName() {
    
    return name;
  
    }

    public void setName(String name) {
    
    this.name = name;
  
    }

    public String getDescription() {
    
    return description;
  
    }

    public void setDescription(String description) {
    
    this.description = description;
  
    }

    @Override
    public String toString() {
    
    return getName();
  
    }

}