package hcmute.it.furnitureshop.Service;

import hcmute.it.furnitureshop.DTO.BannerDTO;
import hcmute.it.furnitureshop.Entity.Banner;

public interface BannerService {
    public Iterable<Banner> findTop5Banner();
}
