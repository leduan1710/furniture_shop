package hcmute.it.furnitureshop.Service.Impl;

import hcmute.it.furnitureshop.Entity.Favorite;
import hcmute.it.furnitureshop.Entity.Product;
import hcmute.it.furnitureshop.Entity.User;
import hcmute.it.furnitureshop.Repository.FavoriteRepository;
import hcmute.it.furnitureshop.Repository.ProductRepository;
import hcmute.it.furnitureshop.Service.FavoriteService;
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
public class FavoriteServiceImpl implements FavoriteService {
    @Autowired
    FavoriteRepository favoriteRepository;
    @Autowired
    ProductRepository productRepository;
    @Override
    public Iterable<Favorite> findByUser(User user) {
        return favoriteRepository.findByUser(user);
    }

    @Override
    public Iterable<Favorite> findByProduct(Integer productId) {
        Optional<Product> product= productRepository.findById(productId);
        return favoriteRepository.findByProduct(product.get());
    }

    @Override
    public <S extends Favorite> void saveFavorite(Favorite favorite) {
        favoriteRepository.save(favorite);
    }

    @Override
    public void deleteByUserAndProduct(User user, Product product) {
        favoriteRepository.deleteByUserAndProduct(user,product);
    }

    @Override
    public Optional<Favorite> findById(Integer favoriteId) {
        return favoriteRepository.findById(favoriteId);
    }


}
