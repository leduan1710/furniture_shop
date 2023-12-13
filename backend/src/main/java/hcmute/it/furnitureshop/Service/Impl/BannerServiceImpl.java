package hcmute.it.furnitureshop.Service.Impl;

import hcmute.it.furnitureshop.DTO.BannerDTO;
import hcmute.it.furnitureshop.Entity.Banner;
import hcmute.it.furnitureshop.Repository.BannerRepository;
import hcmute.it.furnitureshop.Service.BannerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class BannerServiceImpl implements BannerService {
    @Autowired
    BannerRepository bannerRepository;
    @Override
    public Iterable<Banner> findTop5Banner() {
        return bannerRepository.findTop5Banner();
    }
}
