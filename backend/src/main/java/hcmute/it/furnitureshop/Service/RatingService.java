package hcmute.it.furnitureshop.Service;

import hcmute.it.furnitureshop.Entity.Product;
import hcmute.it.furnitureshop.Entity.Rating;
import hcmute.it.furnitureshop.Entity.User;

import java.util.Optional;

public interface RatingService {
    public <S extends Rating> void save(Rating rating);

    public Optional<Rating> findByUserAndProduct (User user, Product product);
}
