package hcmute.it.furnitureshop.ModelMapper;

import hcmute.it.furnitureshop.DTO.CategoryDTO;
import hcmute.it.furnitureshop.DTO.FavoriteDTO;
import hcmute.it.furnitureshop.DTO.ProductDTO;
import hcmute.it.furnitureshop.DTO.ReviewDTO;
import hcmute.it.furnitureshop.Entity.Category;
import hcmute.it.furnitureshop.Entity.Favorite;
import hcmute.it.furnitureshop.Entity.Product;
import hcmute.it.furnitureshop.Entity.Review;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.attribute.standard.Destination;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class ChangeToDTOService {
    @Autowired
    ModelMapper modelMapper;
    public ArrayList<CategoryDTO> changeListCategoryToDTO(Iterable<Category> categories){
        ArrayList<CategoryDTO> categoryDTOS=new ArrayList<>();
        categories.forEach(category->{
            categoryDTOS.add(modelMapper.map(category,CategoryDTO.class));
        });
        return categoryDTOS;
    }

    public ArrayList<ProductDTO> changeListProductToDTO(Iterable<Product> products){
        ArrayList<ProductDTO> productDTOS=new ArrayList<>();
        products.forEach(product -> {
            productDTOS.add(modelMapper.map(product,ProductDTO.class));
        });
        return productDTOS;
    }

    public ProductDTO changeProductToDTO(Product product){
        ProductDTO productDTO = modelMapper.map(product,ProductDTO.class);
        return productDTO;
    }

    public CategoryDTO changeCategoryToDTO(Category category){
        CategoryDTO categoryDTO = modelMapper.map(category,CategoryDTO.class);
        return categoryDTO;
    }

    public ArrayList<FavoriteDTO> changeListFavoriteToDTO(Iterable<Favorite> favorites){
        ArrayList<FavoriteDTO> favoriteDTOS=new ArrayList<>();
        favorites.forEach(favorite -> {
            FavoriteDTO favoriteDTO=new FavoriteDTO();
            favoriteDTO.setFavoriteId(favorite.getFavoriteId());
            favoriteDTO.setUserId(favorite.getUser().getUserId());
            favoriteDTOS.add(favoriteDTO);
        });
        return  favoriteDTOS;
    }

    public ArrayList<ReviewDTO> changeListReviewToDTO(Iterable<Review> reviews){
        ArrayList<ReviewDTO> reviewDTOS=new ArrayList<>();
        reviews.forEach(review -> {
            ReviewDTO reviewDTO=new ReviewDTO();
            reviewDTO.setReviewId(review.getReviewId());
            reviewDTO.setDate(review.getDate());
            reviewDTO.setContent(review.getContent());
            reviewDTO.setName(review.getUser().getName());
            reviewDTO.setUserId(review.getUser().getUserId());
            reviewDTO.setImage(review.getUser().getImage());
            reviewDTOS.add(reviewDTO);
        });
        return  reviewDTOS;
    }
}
