import com.tensquare.encrypt.EncryptApplication;
import com.tensquare.encrypt.rsa.RsaKeys;
import com.tensquare.encrypt.service.RsaService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = EncryptApplication.class)
public class EncryptTest {

    @Autowired
    private RsaService rsaService;

    @Before
    public void before() throws Exception{
    }

    @After
    public void after() throws Exception {
    }

    @Test
    public void genEncryptDataByPubKey() {
        String data = "{\"columnid\":\"11\"}";

        try {

            String encData = rsaService.RSAEncryptDataPEM(data, RsaKeys.getServerPubKey());

            System.out.println("data: " + data);
            System.out.println("encData: " + encData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void test() throws Exception {
        String requestData = "V8QaK2yX9Jj47ey1tnksbPKXbu5D6l5UjmvtYh6kOnuw7LYiBqDWwltR+xBEC0lL+ipYvAxwM9yHz77Dr0mLwSXBmqvWQuXiP7Dk3dhN5nYxycRl6STOHTWrsTWgGceKq5e3cr9CsVbJOpKSDaEgb3C8YaVKMaR8Tx0r7Qd23M/48wPi0Qv0bRr2TJ0BAg0IU8nSSZI/4sKR2XQP3/hkuffzahiY8PCnXznWiM38wuA+KCYD29SoQdZsPcJx31EEUGqGOLnlR4XVwBavvIFZnTpvpz25xieGLNKz5gI1GJwZvMSioW4qXBKocjvT9+9qe+EqMxj19syZwOP3/cZ3cA==";

        String decryptData = rsaService.RSADecryptDataPEM(requestData, RsaKeys.getServerPrvKeyPkcs8());

        System.out.println(decryptData);

    }
}
