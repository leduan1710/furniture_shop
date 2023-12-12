package hcmute.it.furnitureshop.Service;

import hcmute.it.furnitureshop.DTO.DiscountDTO;
import hcmute.it.furnitureshop.Entity.Discount;

import java.util.List;

public interface DiscountService {
    public List<DiscountDTO> getListDiscount();
    public Iterable<Discount> getAll();
}
