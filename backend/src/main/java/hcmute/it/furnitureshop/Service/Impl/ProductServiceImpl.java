package hcmute.it.furnitureshop.Service.Impl;

import hcmute.it.furnitureshop.DTO.ProductDTO;
import hcmute.it.furnitureshop.Entity.Category;
import hcmute.it.furnitureshop.Entity.Product;
import hcmute.it.furnitureshop.Repository.CategoryRepository;
import hcmute.it.furnitureshop.Repository.ProductRepository;
import hcmute.it.furnitureshop.Service.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Iterable<Product> getTop8Product() {
        return productRepository.findTop8ByProductSold();
    }

    @Override
    public Optional<Product> getProductById(Integer productId) {
        return productRepository.findById(productId);
    }

    @Override
    public Iterable<Product> getProductByNameContaining(String name) {
        return productRepository.findProductsByNameContaining(name);
    }

    public  Iterable<Product> getProductsByCategory(Category category){
        return productRepository.findProductsByCategory(category);
    }

    @Override
    public Iterable<Product> getProductByCategoryAndPriceDesc(Category category) {
        return productRepository.findProductsByCategoryOrderByPriceDesc(category);
    }

    @Override
    public Iterable<Product> getProductByCategoryAndPriceAsc(Category category) {
        return productRepository.findProductsByCategoryOrderByPriceAsc(category);
    }

    @Override
    public Iterable<Product> findProductByRoomDesc(Integer roomId) {
        return productRepository.findProductsByCategory_Room_RoomIdOrderByPriceDesc(roomId);
    }

    @Override
    public Iterable<Product> findProductByRoomAsc(Integer roomId) {
        return productRepository.findProductsByCategory_Room_RoomIdOrderByPriceAsc(roomId);
    }

    @Override
    public Iterable<Product> findProductByRoomSale(Integer roomId) {
        return productRepository.findProductsByCategory_Room_RoomId(roomId);
    }

    @Override
    public Optional<Product> findById(Integer productId) {
        return productRepository.findById(productId);
    }

    @Override
    public Iterable<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findProductByOrderId(Integer orderId) {
        return productRepository.findProductByOrder_OrderId(orderId);
    }

    @Override
    public <S extends Product> void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public List<ProductDTO> getAllProductsWithCategoryName() {
        List<ProductDTO> detailDTOList = new ArrayList<>();
        productRepository.findAllProductsWithCategoryName().forEach(productJoinCate -> {
                    ProductDTO productDetail = ProductDTO.builder()
                            .productId(productJoinCate.getProductId())
                            .name(productJoinCate.getName())
                            .image(productJoinCate.getImage())
                            .price(productJoinCate.getPrice())
                            .size(productJoinCate.getSize())
                            .categoryName(productJoinCate.getCategory().getName())
                            .numberProductSold(productJoinCate.getNumberProductSold())
                            .description(productJoinCate.getDescription())
                            .material(productJoinCate.getMaterial())
                            .quantity(productJoinCate.getQuantity())
                            .status(productJoinCate.getStatus())
                            .build();
                    detailDTOList.add(productDetail);
                });
        return detailDTOList;
    }

    @Override
    public String updateProduct(Integer productId) {
        return null;
    }

    @Override
    public String deleteProduct(Integer productId) {
        return null;
    }

    @Override
    public Product createProduct(ProductDTO createProductDTO) {
        if(productRepository.findById(createProductDTO.getProductId()).isEmpty())
        {
            var product = Product.builder()
                    .productId(createProductDTO.getProductId())
                    .name(createProductDTO.getName())
                    .Image(createProductDTO.getImage())
                    .price(createProductDTO.getPrice())
                    .size(createProductDTO.getSize())
                    .category(categoryRepository.findByName(createProductDTO.getCategoryName()))
                    .numberProductSold(createProductDTO.getNumberProductSold())
                    .description(createProductDTO.getDescription())
                    .material(createProductDTO.getMaterial())
                    .quantity(createProductDTO.getQuantity())
                    .status(createProductDTO.getStatus())
                    .build();
            return product;
        }
        else return null;
    }
}
