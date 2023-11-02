package hcmute.it.furnitureshop.Service.Impl;

import hcmute.it.furnitureshop.Entity.Product;
import hcmute.it.furnitureshop.Entity.Review;
import hcmute.it.furnitureshop.Repository.ReviewRepository;
import hcmute.it.furnitureshop.Service.ReviewService;
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
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    ReviewRepository reviewRepository;
    @Override
    public <S extends Review> void saveReview(Review review) {
        reviewRepository.save(review);
    }

    @Override
    public Iterable<Review> findByProduct(Product product) {
        return reviewRepository.findReviewsByProductOrderByDateDesc(product);
    }

    @Override
    public void deleteById(Integer reviewId) {
        reviewRepository.deleteById(reviewId);
    }

    @Override
    public Optional<Review> findById(Integer reviewId) {
        return reviewRepository.findById(reviewId);
    }
}
