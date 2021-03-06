package net.revature.binarybuiltbyte.project2.repository;

import net.revature.binarybuiltbyte.project2.model.ByteUser;
import net.revature.binarybuiltbyte.project2.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200" , "http://bytebuildersbucket.s3-website.us-east-2.amazonaws.com"})
@RepositoryRestResource(collectionResourceRel = "products", path = "product")
public interface ProductRepository extends JpaRepository<Product, Integer> {

    Page<Product> findByCategoryId(@RequestParam("id") Integer id, Pageable pageable);
    Page<Product> findByDescriptionContainingIgnoreCase(@RequestParam("name") String name, Pageable pageable);

    /** this endpoint could be used for hot products to buy.
     *
     * @param past the time you choose to look back at
     * @param now the current time
     * @return returns the list of products which are "hot" for being bought between the two dates specified.
     */
    @Query(value="select p.id, p.description, p.is_active,  p.picture, p.price, p.product_created, p.product_terminated, p.rating, p.sku, p.stock, p.category_id from product p join product_order po on p.id=po.product_id join byte_order bo on bo.id=po.byte_order_id where bo.order_completed between :past and :now ;", nativeQuery = true)
    @RestResource(path = "hot", rel = "hot")
    List<Product> findProductsByDate(@Param("past") Date past, @Param("now") Date now);

    /** This endpoint could be from someone checking all the boxes for the parameters to choose from
     *
     * @param categoryName could be a checkbox for the category to pick, the bottom ones will just be variances of the fully checked boxes
     * @param rating could be a checkbox for a rating to pick
     * @param price could be an above price value check box
     * @return returns product list with specified parameters.
     */
    @Query(value="select p.id, p.description, p.is_active,  p.picture, p.price, p.product_created, p.product_terminated, p.rating, p.sku, p.stock, p.category_id from product p join category c on p.category_id=c.id where category_name = :categoryName and rating >= :rating and price < :price ;", nativeQuery = true)
    @RestResource(path = "checkBox_full", rel = "checkBox_full")
    List<Product> findByCategoryRatingPrice(@Param("categoryName") String categoryName, @Param("rating") int rating, @Param("price") double price);

    @Query(value="select p.id, p.description, p.is_active,  p.picture, p.price, p.product_created, p.product_terminated, p.rating, p.sku, p.stock, p.category_id from product p join category c on p.category_id=c.id where category_name = :categoryName and rating >= :rating;", nativeQuery = true)
    @RestResource(path = "checkBox_category_rating", rel = "checkBox_category_rating")
    List<Product> findByCategoryRating(@Param("categoryName") String categoryName, @Param("rating") int rating);

    @Query(value="select p.id, p.description, p.is_active,  p.picture, p.price, p.product_created, p.product_terminated, p.rating, p.sku, p.stock, p.category_id from product p join category c on p.category_id=c.id where rating >= :rating and price < :price ;", nativeQuery = true)
    @RestResource(path = "checkBox_rating_price", rel = "checkBox_rating_price")
    List<Product> findByRatingPrice(@Param("rating") int rating, @Param("price") double price);

    @Query(value="select p.id, p.description, p.is_active,  p.picture, p.price, p.product_created, p.product_terminated, p.rating, p.sku, p.stock, p.category_id from product p join category c on p.category_id=c.id where category_name = :categoryName and price < :price ;", nativeQuery = true)
    @RestResource(path = "checkBox_category_price", rel = "checkBox_category_price")
    List<Product> findByCategoryPrice(@Param("categoryName") String categoryName, @Param("price") double price);

    @Query(value="select p.id, p.description, p.is_active,  p.picture, p.price, p.product_created, p.product_terminated, p.rating, p.sku, p.stock, p.category_id from product p join category c on p.category_id=c.id where category_name = :categoryName", nativeQuery = true)
    @RestResource(path = "checkBox_category", rel = "checkBox_category")
    List<Product> findByCategory(@Param("categoryName") String categoryName);

    @Query(value="select * from product where rating >= :rating ;", nativeQuery = true)
    @RestResource(path = "checkBox_rating", rel = "checkBox_rating")
    List<Product> findByRating(@Param("rating") int rating);

    @Query(value="select * from product where price >= :price ;", nativeQuery = true)
    @RestResource(path = "checkBox_price", rel = "checkBox_price")
    List<Product> findByPrice(@Param("price") int price);

    //TODO: change data inside product description to all lower case or upper case for easier searches

    /** for search bar
     *
     * @param search parameter passed into the search bar
     * @return returns all products with matching search parameters.
     */
    @Query(value="select * from product where description LIKE CONCAT('%', :search, '%');", nativeQuery = true)
    @RestResource(path = "search_bar", rel = "search_bar")
    List<Product> searchBarForDescription(@Param("search") String search);

    /** User shopping history
     *
     * @param userId enter the userId for the person
     * @return returns list of products user has bought in the past.
     */
    @Query(value="select p.id, p.description, p.is_active,  p.picture, p.price, p.product_created, p.product_terminated, p.rating, p.sku, p.stock, p.category_id from product p join product_order po on p.id=po.product_id join byte_order bo on bo.id=po.byte_order_id join byte_user bu on bu.id = bo.id where bu.id = :userId ;", nativeQuery = true)
    @RestResource(path = "shopping_history", rel = "shopping_history")
    List<Product> findShoppingHistoryById(@Param("userId") int userId);

    @Transactional
    @Modifying
    @Query(value="update product set price = price-price *:percentage where category_id = :categoryId ;", nativeQuery = true)//works with url parameters inputed with a get, throws an error but updates db...
    @RestResource(path = "sale_by_category", rel = "sale_by_category")
    void decreaseProductValueByCategoryId(@Param("percentage") double percentage, @Param("categoryId") int categoryId);

    @Transactional
    @Modifying
    @Query(value="update product set rating = (select avg(r.rating) from review r join byte_order bo on r.byte_order_id = bo.id \n" +
            "    join product_order po on po.byte_order_id = bo.id\n" +
            "    join product on po.product_id =product.id\n" +
            "    where product.id = :productId)\n" +
            "    where product.id = :productId ;", nativeQuery = true)//works with url parameters inputed with a get, throws an error but updates db...
    @RestResource(path = "update_product_rating", rel = "update_product_rating")
    void updateProductAverageRating(@Param("productId") int productId);


    /** Find some the recently purchased items
     *
     *
     */
    @Query(value="select distinct id ,category_id , description ,  is_active , picture , product_created , product_terminated , rating , sku , stock , price from (\n" +
            "\tselect distinct on (po.order_id) p2.category_id  , p2.description  , p2.id  , p2.is_active  , p2.picture  ,p2.price ,p2.product_created ,p2.product_terminated  , p2.rating  , p2.sku  ,p2.stock\n" +
            "\tfrom byte_order bo , product_order po , product p2 \n" +
            "\twhere po.order_id  = bo.id and po.product_id = p2.id order by po.order_id desc nulls last\n" +
            ") t\n", nativeQuery = true)
    @RestResource(path = "hotItems", rel = "hotItems")
    List<Product> findRecentlyPurchasedItems();


    /** Find some the recently purchased items
     *
     *
     */
    @Query(value="select * from product p  where p.product_created  <= current_timestamp order by p.product_created desc ;", nativeQuery = true)
    @RestResource(path = "newItems", rel = "newItems")
    List<Product> findNewlyAddedITems();
}

