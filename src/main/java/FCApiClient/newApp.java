package FCApiClient;

import com.sun.org.apache.xerces.internal.util.PropertyState;
import org.springframework.http.*;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.sun.org.apache.xerces.internal.util.PropertyState.is;

public class newApp {


    public static void main(String args[]) throws IOException {
        String taskId = "1";
        byte[] data = fileToBytes("Einstein.ttl");

        RestTemplate rest = new RestTemplate();
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
        map.add("taskId",taskId);
        map.add("data", data);


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<MultiValueMap<String, Object>>(map, headers);

        ResponseEntity<FCApi> response =
                rest.exchange("http://localhost:8080/api/execTask/1",
                        HttpMethod.POST, request, FCApi.class);

        FCApi result =  response.getBody();

        System.out.println("Truth value:  " +result.getDefactoScore());



    }



    public static byte[] fileToBytes(String filePath) throws IOException {
        Path fileLocation = Paths.get(filePath);
        return Files.readAllBytes(fileLocation);
    }


}


