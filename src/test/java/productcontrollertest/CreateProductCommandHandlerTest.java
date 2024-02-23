package productcontrollertest;

import org.example.nobs.NobsApplication;
import org.example.nobs.command.commandhandler.CreateProductCommand;
import org.example.nobs.entity.Product;
import org.example.nobs.repo.ProductRepo;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = NobsApplication.class)
//Annotation để chỉ định rằng lớp kiểm thử này là một kiểm thử Spring Boot
// và chỉ định class chính của ứng dụng (NobsApplication) để Spring Boot
// có thể khởi chạy ứng dụng trong quá trình kiểm thử
public class CreateProductCommandHandlerTest {
    //Annotation để yêu cầu Mockito inject (tiêm) các mock objects vào trường createProductCommand
    @InjectMocks
    private CreateProductCommand createProductCommand;
    //Annotation để đánh dấu trường productRepo là một mock object.
    @Mock
    ProductRepo productRepo;
    @Test
    public void CreateProductCommand_validProduct_returnSuccess (){

        Product product = new Product();
        product.setId(1);
        product.setName("M1");
        product.setPrice(1200.0);
        ResponseEntity response = createProductCommand.execute(product);
        assertEquals(HttpStatus.OK,response.getStatusCode());

    }

}
