package productcontrollertest;

import org.example.nobs.NobsApplication;
import org.example.nobs.dto.ProductDto;
import org.example.nobs.entity.Product;
import org.example.nobs.query.queryhandler.GetProductQueryHandler;
import org.example.nobs.repo.ProductRepo;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = NobsApplication.class)
public class GetProductQueryHandlerTest {

    @InjectMocks
    private GetProductQueryHandler getProductQueryHandler;
    @Mock
    ProductRepo productRepo;


    @Test
    public void  setGetProductQueryHandler_validId_ReturnProductDTO (){
        Product product = new Product();
        product.setId(1);
        product.setName("M1");
        product.setPrice(1200.0);
        ProductDto exceptedDTO = new ProductDto(product);
//        Thiết lập giả định với Mockito:
//        Sử dụng when(productRepo.findById(product.getId())) để thiết lập một giả định rằng khi gọi phương thức findById() của productRepo với ID của sản phẩm, sẽ trả về một Optional chứa đối tượng sản phẩm. Điều này giả định rằng productRepo sẽ trả về đối tượng sản phẩm tương ứng với ID đã cung cấp.
//        Trong trường hợp này, thenReturn(Optional.of(product)) được sử dụng để chỉ định rằng kết quả trả về của findById() sẽ là một Optional chứa đối tượng sản phẩm đã được tạo trước đó.
        when(productRepo.findById(product.getId())).thenReturn(Optional.of(product));
        ResponseEntity<ProductDto> actualResponse = getProductQueryHandler.execute(product.getId());
        assertEquals(exceptedDTO,actualResponse.getBody());
        assertEquals(HttpStatus.OK,actualResponse.getStatusCode());
    }

}
