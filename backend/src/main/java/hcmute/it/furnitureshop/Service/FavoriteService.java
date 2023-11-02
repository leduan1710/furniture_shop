package hcmute.it.furnitureshop.Service;

import hcmute.it.furnitureshop.Entity.Favorite;
import hcmute.it.furnitureshop.Entity.Product;
import hcmute.it.furnitureshop.Entity.User;

import java.util.Optional;

public interface FavoriteService {
    public Iterable<Favorite> findByUser(User user);
    public Iterable<Favorite> findByProduct(Product product);

    public <S extends Favorite> void saveFavorite(Favorite favorite);
    void deleteByUserAndProduct(User user,Product product);

    public Optional<Favorite> findById(Integer favoriteId);
}
