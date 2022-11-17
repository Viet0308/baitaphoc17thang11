package service;

import model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductService {
    public List<Product> products = new ArrayList<>();

    public ProductService() {
        products.add(new Product(1, "Lộc", "https://file.qdnd.vn/data/images/0/2020/04/19/vuhuyen/342020huyen1698.jpg?dpi=150&quality=100&w=575", 50000.0));
        products.add(new Product(2, "Khánh", "https://cafefcdn.com/thumb_w/650/203337114487263232/2021/5/1/photo1619828199863-1619828200012212933298.jpg", 30000.0));
        products.add(new Product(3, "Mạnh", "https://nld.mediacdn.vn/2021/3/2/duymanh-1614653912250406924255.jpg", 40000.0));
        products.add(new Product(4, "Vân", "https://nld.mediacdn.vn/291774122806476800/2021/9/15/hoahaukhanhvandahoibanketmissuniverselanthu690-1631667790463465477775.png", 20000.0));
    }

    public void add(Product product){
        products.add(product);
    }

    public void edit(int id, Product product){
        int index = findIndexByID(id);
        if (index != -1){
            products.set(index,product);
        }

    }

    public int findIndexByID(int id){
        for (int i=0; i<products.size();i++){
            if ( products.get(i).getId() == id ){
                return i;
            }
        }
        return -1;
    }

    public void delete(int id){
        int index = findIndexByID(id);
        if (index !=-1){
            products.remove(index);
        }
    }


}
