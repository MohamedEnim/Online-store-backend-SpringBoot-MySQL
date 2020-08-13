package com.appStore.appStore.services;

import com.appStore.appStore.DO.ClientOrders;
import com.appStore.appStore.DO.ClientShoppingCart;
import com.appStore.appStore.VO.DBShipping;
import com.appStore.appStore.VO.ShoppingProducts;
import com.appStore.appStore.authentication.authDomain.AuthenticationUser;
import com.appStore.appStore.authentication.authenticationRepository.AuhenticationUserIterf;
import com.appStore.appStore.domain.*;
import com.appStore.appStore.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.*;
import java.util.stream.Collectors;


@Service
public class AppService implements AppServiceInterf {

    @Autowired
    public RepositoryCategoryInterf repositoryCategoryInterf;
    @Autowired
    public RepositoryProductInterf repositoryProductInterf;
    @Autowired
    public RepositoryShoppingCartInterf repositoryShoppingCartInterf;
    @Autowired
    public AuhenticationUserIterf authenticationUserIterf;
    @Autowired
    public RepositoryShoppingCartProductsInterf repositoryShoppingCartProductsInterf;

    @Override
    public Category addCategory(Category category) {
        Category categ = repositoryCategoryInterf.save(category);
        return categ;
    }

    @Override
    public Category selectCategory(String nameCategory) {
        return this.repositoryCategoryInterf.findBynameCateg(nameCategory);
    }

    @Override
    public Optional<Product> getProduct(long indexProd) {
       return this.repositoryProductInterf.findById(indexProd);
    }



   @Override
    public Iterable<Category> updateProduct(Long idCateg, Long idProd, Product product) {
       Optional<Category>  category = this.repositoryCategoryInterf.findById(idCateg) ;
       Optional<Product> prod = this.repositoryProductInterf.findById(idProd) ;
       List<Product> products = category.get().getProducts();

       int indexProd = 0;
       for (Product p: products) {
           if(p.getId() == idProd){
               break;
           }
           indexProd += 1;
       }
        product.setId(prod.get().getId());
      products.set(indexProd, product);
       category.get().setProducts(products);
       this.repositoryCategoryInterf.save(category.get());
          return this.returnCategories();
    }

    @Override
    public Iterable<Category> deleteProduct(Long idCateg, Long idProd) {
        Optional<Category>  category = this.repositoryCategoryInterf.findById(idCateg) ;
        Optional<Product> prod = this.repositoryProductInterf.findById(idProd) ;
        List<Product> products = category.get().getProducts();
        products.remove(prod.get());
        this.repositoryCategoryInterf.save(category.get());
        return this.returnCategories();
    }

    @Override
    public ClientShoppingCart addShoppingCart(Long userId, ShoppingProducts shoppingProducts) {

          ClientShoppingCart clientShoppingCart = new  ClientShoppingCart();

         Optional<AuthenticationUser> user = this.authenticationUserIterf.findById(userId);

         List<ShoppingCart> shoppingCarts = new ArrayList<>();
         ShoppingCart shoppingCart = new ShoppingCart();

         shoppingCart.setQuantityProduct((long) 1);
         Date date = new Date();
         shoppingCart.setDate(date);
         shoppingCarts = user.get().getShoppingCarts();
         shoppingCarts.add(shoppingCart);

         user.get().setShoppingCarts(shoppingCarts);
         AuthenticationUser userSp  = this.authenticationUserIterf.save(user.get());

         List<ShoppingCartProducts> shoppingCartProducts = new ArrayList<>();
         ShoppingCartProducts ShoppingCP = new ShoppingCartProducts();

         ShoppingCP.setNameProd(shoppingProducts.getProducts().getNameProd());
         ShoppingCP.setPrice(shoppingProducts.getProducts().getPrice());
         ShoppingCP.setQuantity(shoppingProducts.getProducts().getQuantity());
         ShoppingCP.setImgUrl(shoppingProducts.getProducts().getImgUrl());
         ShoppingCP.setQuantityProduct(shoppingProducts.getQuantityProduct());

        shoppingCarts = userSp.getShoppingCarts();


        Long idLastSC = Long.valueOf(0) ;

            for (ShoppingCart element : shoppingCarts) {
                if(element.getId() > idLastSC){
                    idLastSC = element.getId();
                }
            }

         Long shopCartid = idLastSC;
        clientShoppingCart.setShoppingCart(userSp.getShoppingCarts().stream()
                .filter(shoppingC -> shoppingC.getId().equals(shopCartid))
                .findFirst().get());

        ShoppingCP.setShop_Cart_Id(shopCartid);

        shoppingCartProducts = userSp.getShoppingCartProducts();
        shoppingCartProducts.add(ShoppingCP);

        user.get().setShoppingCartProducts(shoppingCartProducts);
        userSp = this.authenticationUserIterf.save(userSp);

        shoppingCartProducts = userSp.getShoppingCartProducts();
        List<ShoppingCartProducts> shoCartProds = new ArrayList<>();

       for(ShoppingCartProducts shoppingCartProd : shoppingCartProducts){
           if(shoppingCartProd.getShop_Cart_Id().equals(shopCartid)){
               shoCartProds.add(shoppingCartProd);
           }
       }

        clientShoppingCart.setShoppingCartProducts(shoCartProds);
        return clientShoppingCart;
    }

    @Override
    public ClientShoppingCart addProductToShoppingCart(Long userId, Long cartId, ShoppingProducts shoppingProducts) {

        ClientShoppingCart clientShoppingCart = new  ClientShoppingCart();
        List<ShoppingCart> shoppingCarts = new ArrayList<>();

        Optional<AuthenticationUser> user = this.authenticationUserIterf.findById(userId);

        shoppingCarts =  user.get().getShoppingCarts();
       Optional<ShoppingCart> shoppingCart =  shoppingCarts.stream()
                .filter(shoppingC -> shoppingC.getId().equals(cartId))
                .findFirst();
        shoppingCart.get().setQuantityProduct(shoppingCart.get().getQuantityProduct() + 1);


          int indexSC = 0;
          for (ShoppingCart ShopSC: shoppingCarts) {
              if (ShopSC.getId().equals(cartId)) {
                 break;
              }
              indexSC += 1;
          }

        shoppingCarts.set(indexSC , shoppingCart.get());

        AuthenticationUser userSp  = this.authenticationUserIterf.save(user.get());

        clientShoppingCart.setShoppingCart(userSp.getShoppingCarts().stream()
                .filter(shoppingC -> shoppingC.getId().equals(cartId))
                .findFirst().get());

          List<ShoppingCartProducts> shoppingCartProducts = new ArrayList<>();
          ShoppingCartProducts ShoppingCP = new ShoppingCartProducts();

        ShoppingCP.setNameProd(shoppingProducts.getProducts().getNameProd());
        ShoppingCP.setPrice(shoppingProducts.getProducts().getPrice());
        ShoppingCP.setQuantity(shoppingProducts.getProducts().getQuantity());
        ShoppingCP.setImgUrl(shoppingProducts.getProducts().getImgUrl());
        ShoppingCP.setQuantityProduct(shoppingProducts.getQuantityProduct());
        ShoppingCP.setShop_Cart_Id(cartId);

        shoppingCartProducts = userSp.getShoppingCartProducts();


        List<ShoppingCartProducts> shoppingCPsByCID = new ArrayList<>();

        for(ShoppingCartProducts shoppingCartProd : shoppingCartProducts){
            if(shoppingCartProd.getShop_Cart_Id().equals(cartId)){
                shoppingCPsByCID.add(shoppingCartProd);
            }
        }


        Optional<ShoppingCartProducts> ShoppingExCP = shoppingCPsByCID.stream()
                .filter(ShopExCP -> ShopExCP.getNameProd().contentEquals(ShoppingCP.getNameProd()))
                .findFirst();

        if(ShoppingExCP.isPresent()){
        ShoppingExCP.get().setQuantityProduct(ShoppingExCP.get().getQuantityProduct() + 1);
              int indexProd = 0;
              for (ShoppingCartProducts ShopCP: shoppingCartProducts) {
                 if (ShopCP.getShop_Cart_Id().equals(cartId) && ShopCP.getNameProd().contentEquals(ShoppingCP.getNameProd())) {
                     break;
                }
                indexProd += 1;
            }

            shoppingCartProducts.set(indexProd, ShoppingExCP.get());
            userSp.setShoppingCartProducts(shoppingCartProducts);
            userSp  = this.authenticationUserIterf.save(userSp);


            List<ShoppingCartProducts> shoCartProds = new ArrayList<>();

            for(ShoppingCartProducts shoppingCartProd : userSp.getShoppingCartProducts()){
                if(shoppingCartProd.getShop_Cart_Id().equals(cartId)){
                    shoCartProds.add(shoppingCartProd);
                }
            }

            clientShoppingCart.setShoppingCartProducts(shoCartProds);
            return clientShoppingCart;

    }

        shoppingCartProducts.add(ShoppingCP);
        userSp.setShoppingCartProducts(shoppingCartProducts);
        userSp = this.authenticationUserIterf.save(userSp);


        List<ShoppingCartProducts> shoCartProds = new ArrayList<>();

        for(ShoppingCartProducts shoppingCartProd : userSp.getShoppingCartProducts()){
            if(shoppingCartProd.getShop_Cart_Id().equals(cartId)){
                shoCartProds.add(shoppingCartProd);
            }
        }

        clientShoppingCart.setShoppingCartProducts(shoCartProds);
        return clientShoppingCart;

    }

    @Override
    public ClientShoppingCart getShoppingCart(Long userId, Long cartId) {

        ClientShoppingCart clientShoppingCart = new  ClientShoppingCart();
        List<ShoppingCart> shoppingCarts = new ArrayList<>();
        List<ShoppingCartProducts> shoppingCartProducts = new ArrayList<ShoppingCartProducts>();

        Optional<AuthenticationUser> user = this.authenticationUserIterf.findById(userId);

        shoppingCarts =  user.get().getShoppingCarts();
        shoppingCartProducts = user.get().getShoppingCartProducts();

        Optional<ShoppingCart> shoppingCart =  shoppingCarts.stream()
                .filter(shoppingC -> shoppingC.getId().equals(cartId))
                .findFirst();

        clientShoppingCart.setShoppingCart(shoppingCart.get());


        List<ShoppingCartProducts> shoCartProds = new ArrayList<>();

        for(ShoppingCartProducts shoppingCartProd : shoppingCartProducts){
            if(shoppingCartProd.getShop_Cart_Id().equals(cartId)){
                shoCartProds.add(shoppingCartProd);
            }
        }

        clientShoppingCart.setShoppingCartProducts(shoCartProds);
        return clientShoppingCart;
    }

    @Override
    public  ClientShoppingCart removeProdShoppingCart(Long userId, Long cartId, Long prodId) {

        ClientShoppingCart clientShoppingCart = new  ClientShoppingCart();
        List<ShoppingCart> shoppingCarts = new ArrayList<>();
        List<ShoppingCartProducts> shoppingCartProducts = new ArrayList<ShoppingCartProducts>();

        Optional<AuthenticationUser> user = this.authenticationUserIterf.findById(userId);

        shoppingCarts =  user.get().getShoppingCarts();
        shoppingCartProducts = user.get().getShoppingCartProducts();

        Optional<ShoppingCart> shoppingCart =  shoppingCarts.stream()
                .filter(shoppingC -> shoppingC.getId().equals(cartId))
                .findFirst();

        Optional<ShoppingCartProducts> ShoppingExCP = shoppingCartProducts.stream()
                .filter(ShopExCP -> ShopExCP.getId().equals(prodId) )
                .findFirst();

        if(shoppingCart.get().getQuantityProduct() > 1){
            shoppingCart.get().setQuantityProduct(shoppingCart.get().getQuantityProduct() - 1);
            int indexSc = 0;
            for (ShoppingCart ShopC : shoppingCarts) {
                if (ShopC.getId().equals(cartId)) {
                    break;
                }
                indexSc += 1;
            }

            shoppingCarts.set(indexSc, shoppingCart.get());
            user.get().setShoppingCarts(shoppingCarts);
            AuthenticationUser userSp  = this.authenticationUserIterf.save(user.get());
            clientShoppingCart.setShoppingCart(userSp.getShoppingCarts().stream()
                    .filter(shoppingC -> shoppingC.getId().equals(cartId))
                    .findFirst().get());

            if (ShoppingExCP.get().getQuantityProduct() > 1) {
                ShoppingExCP.get().setQuantityProduct(ShoppingExCP.get().getQuantityProduct() - 1);
                int indexProd = 0;
                for (ShoppingCartProducts ShopCP : shoppingCartProducts) {
                    if (ShopCP.getId().equals(prodId)) {
                        break;
                    }
                    indexProd += 1;
                }
                shoppingCartProducts.set(indexProd, ShoppingExCP.get());
                userSp.setShoppingCartProducts(shoppingCartProducts);
                userSp  = this.authenticationUserIterf.save(userSp);
                List<ShoppingCartProducts> shoCartProds = new ArrayList<>();

                for(ShoppingCartProducts shoppingCartProd : userSp.getShoppingCartProducts()){
                    if(shoppingCartProd.getShop_Cart_Id().equals(cartId)){
                        shoCartProds.add(shoppingCartProd);
                    }
                }

                clientShoppingCart.setShoppingCartProducts(shoCartProds);
                return clientShoppingCart;
            }

            shoppingCartProducts.remove(ShoppingExCP.get());
            userSp.setShoppingCartProducts(shoppingCartProducts);
            userSp  = this.authenticationUserIterf.save(userSp);

            List<ShoppingCartProducts> shoCartProds = new ArrayList<>();

            for(ShoppingCartProducts shoppingCartProd : userSp.getShoppingCartProducts()){
                if(shoppingCartProd.getShop_Cart_Id().equals(cartId)){
                    shoCartProds.add(shoppingCartProd);
                }
            }

            clientShoppingCart.setShoppingCartProducts(shoCartProds);
            return clientShoppingCart;


        }

       return this.removeShoppingCart(userId, cartId);

    }

    @Override
    public  ClientShoppingCart removeShoppingCart(Long userId, Long cartId) {

        ClientShoppingCart clientShoppingCart = new ClientShoppingCart();
        List<ShoppingCart> shoppingCarts = new ArrayList<>();

        Optional<AuthenticationUser> user = this.authenticationUserIterf.findById(userId);

        shoppingCarts = user.get().getShoppingCarts();


        Optional<ShoppingCart> shoppingCart = shoppingCarts.stream()
                .filter(shoppingC -> shoppingC.getId().equals(cartId))
                .findFirst();
        user.get().setShoppingCarts(shoppingCarts);


        shoppingCarts.remove(shoppingCart.get());
        AuthenticationUser userSp = this.authenticationUserIterf.save(user.get());

        List<ShoppingCartProducts> shoCartProds = new ArrayList<>();

        for (ShoppingCartProducts shoppingCartProd : userSp.getShoppingCartProducts()) {
            if (!shoppingCartProd.getShop_Cart_Id().equals(cartId)) {
                shoCartProds.add(shoppingCartProd);
            }
        }
            userSp.setShoppingCartProducts(shoCartProds);
            this.authenticationUserIterf.save(userSp);

            return clientShoppingCart;

    }

    @Override
    public Category addNewProdToCategory(String nameCateg, List<Product> newProducts) {
        Category category = new Category();
        List<Product> products = new ArrayList<Product>();
        category = this.repositoryCategoryInterf.findBynameCateg(nameCateg);
        products = category.getProducts();
        for(Product newProduct: newProducts){
            products.add(newProduct);
        }
        category.setProducts(products);
        return  this.repositoryCategoryInterf.save(category);

    }


    @Override
    public Iterable<Category> returnCategories() {
        Iterable<Category> categories = this.repositoryCategoryInterf.findAll();
        return categories;
    }

    @Override
    public List<ShoppingCart> getMyOrders(Long userId) {

        List<ShoppingCart> shoppingCarts = new ArrayList<>();
        Optional<AuthenticationUser> user = this.authenticationUserIterf.findById(userId);
        shoppingCarts = user.get().getShoppingCarts();
        return shoppingCarts;
    }

    @Override
    public List<ShoppingCartProducts> getMyOrderDetail(Long userId, Long cartId) {

        ClientShoppingCart clientShoppingCart = new ClientShoppingCart();
        List<ShoppingCartProducts> shoppingCartProducts = new ArrayList<>();

        Optional<AuthenticationUser> user = this.authenticationUserIterf.findById(userId);

        shoppingCartProducts = user.get().getShoppingCartProducts();

        List<ShoppingCartProducts> shoCartProds = new ArrayList<>();

        for(ShoppingCartProducts shoppingCartProd : shoppingCartProducts){
            if(shoppingCartProd.getShop_Cart_Id().equals(cartId)){
                shoCartProds.add(shoppingCartProd);
            }
        }

        return shoCartProds;
    }

    @Override
    public List<ClientOrders> getOrders() {


        List<ClientOrders> clientOrders = new ArrayList<>();
        List<AuthenticationUser> users = this.authenticationUserIterf.findAll();

        for (AuthenticationUser user : users) {
            List<ShoppingCart> shoppingCarts = new ArrayList<>();
            shoppingCarts = user.getShoppingCarts();
            for(ShoppingCart shoppingCart: shoppingCarts){
                ClientOrders clientOrder = new ClientOrders();
                clientOrder.setUserId(user.getUserId());
                clientOrder.setUsername(user.getUsername());
                clientOrder.setId(shoppingCart.getId());
                clientOrder.setQuantityProduct(shoppingCart.getQuantityProduct());
                clientOrder.setDate(shoppingCart.getDate());
                clientOrders.add(clientOrder);
            }

        }
        return clientOrders;
    }

    @Override
    public Shipping addshipping(Long userId, Long cartId, DBShipping shipping) {

        List<Shipping> shippings = new ArrayList<>();
        Shipping shipp = new Shipping();

        shipp.setName(shipping.getName());
        shipp.setAddress_1(shipping.getAddress_1());
        shipp.setAddress_2(shipping.getAddress_2());
        shipp.setShop_Cart_Id(cartId);
        shipp.setCity(shipping.getCity());


        Optional<AuthenticationUser> user = this.authenticationUserIterf.findById(userId);

        shippings = user.get().getShippings();

        shippings.add(shipp);
        user.get().setShippings(shippings);

        this.authenticationUserIterf.save(user.get()) ;


        return shipp;
    }
}
