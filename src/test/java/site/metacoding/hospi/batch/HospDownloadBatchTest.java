package site.metacoding.hospi.batch;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

// 하루에 한번씩 다운로드해서 DB에 변경해주기
// pcr 검사기관이 추가 될 수 있기 때문에!!
// 4개 병원이 있다면...4개가 DB에 insert된다.
// 다음날에는 5개 병원이 되었다면 한개 추가하는 로직이 복잡할 것 같아서
// 그냥 4개 데이터 삭제해버리고 새로 추가해서 넣자.
// 공공데이터를 바로 바로 서비스해주는 방식은 하루에 트래픽이 1000이라서 서비스하기 힘들것같다.


public class HospDownloadBatchTest {
    
    @Test
    public void start() throws URISyntaxException {

        // 1. 공공데이터 다운로드
        RestTemplate rt = new RestTemplate();

        //servicekey 에러나면 URI 사용하기
        String url = "https://apis.data.go.kr/B551182/cv19EtgMsupHospService/getCv19EtgMsupHospList?serviceKey=Q51wiYKC3vaY1YhZR%2BHAds2pRgx9%2B4lPEuNvIJRltzO4FE9Tkh8B0jYw%2FMyxI2Uo7WguDJ4flwnQny52C6kytA==&pageNo=1&numOfRows=3&pageNo=1&numOfRows=10&_type=json";

        URI uri = new URI(url);

        ResponseDto dto = rt.getForObject(uri, ResponseDto.class);
        //String dto = rt.getForObject(uri, String.class);
        //System.out.println(dto);
        List<Item> hospitals = dto.getResponse().getBody().getItems().getItem();
        for (Item item : hospitals) {
            System.out.println(item.getYadmNm());
        }
        
    }

}
