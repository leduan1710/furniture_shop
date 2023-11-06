package hcmute.it.furnitureshop.Service.Impl;

import hcmute.it.furnitureshop.Entity.Category;
import hcmute.it.furnitureshop.Entity.Product;
import hcmute.it.furnitureshop.Repository.ProductRepository;
import hcmute.it.furnitureshop.Service.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

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


}
