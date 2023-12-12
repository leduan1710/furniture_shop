package hcmute.it.furnitureshop.Service.Impl;

import hcmute.it.furnitureshop.DTO.DiscountDTO;
import hcmute.it.furnitureshop.Entity.Discount;
import hcmute.it.furnitureshop.Repository.DiscountRepository;
import hcmute.it.furnitureshop.Service.DiscountService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class DiscountServiceImpl implements DiscountService {
    @Autowired
    private DiscountRepository discountRepository;
    @Override
    public List<DiscountDTO> getListDiscount() {
        List<DiscountDTO> discountDTOS= new ArrayList<>();
        discountRepository.findAll().forEach(discount -> {
            discountDTOS.add(DiscountDTO.builder().discountId(discount.getDiscountId())
                    .discountName(discount.getDiscountName())
                    .percentDiscount(discount.getPercentDiscount())
                    .build());

        });
        return discountDTOS;
    }
    @Override
    public Iterable<Discount> getAll(){
        return discountRepository.findAll();
    }
}
