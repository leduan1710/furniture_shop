package hcmute.it.furnitureshop.Service.Impl;

import hcmute.it.furnitureshop.Entity.ResponseReview;
import hcmute.it.furnitureshop.Entity.Review;
import hcmute.it.furnitureshop.Repository.ResponseReviewRepository;
import hcmute.it.furnitureshop.Service.ResponseReviewService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ResponseReviewImpl implements ResponseReviewService {
    @Autowired
    ResponseReviewRepository responseReviewRepository;
    @Override
    public Iterable<ResponseReview> findByReview(Review review) {
        return responseReviewRepository.findResponseReviewsByReview(review);
    }

    @Override
    public void deleteById(Integer responseReviewId) {
        responseReviewRepository.deleteById(responseReviewId);
    }

    @Override
    public <S extends ResponseReview> void save(ResponseReview responseReview) {
        responseReviewRepository.save(responseReview);
    }
}
