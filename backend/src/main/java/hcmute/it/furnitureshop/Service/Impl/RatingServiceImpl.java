package hcmute.it.furnitureshop.Service.Impl;

import hcmute.it.furnitureshop.Entity.Product;
import hcmute.it.furnitureshop.Entity.Rating;
import hcmute.it.furnitureshop.Entity.User;
import hcmute.it.furnitureshop.Repository.RatingRepository;
import hcmute.it.furnitureshop.Service.RatingService;
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
public class RatingServiceImpl implements RatingService{
    @Autowired
    RatingRepository ratingRepository;

    @Override
    public <S extends Rating> void save(Rating rating) {
        ratingRepository.save(rating);
    }

    @Override
    public Optional<Rating> findByUserAndProduct(User user, Product product) {
        return ratingRepository.findRatingByUserAndProduct(user,product);
    }
}
