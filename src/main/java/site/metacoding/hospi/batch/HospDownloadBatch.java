package site.metacoding.hospi.batch;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;
import site.metacoding.hospi.domain.Hospital;
import site.metacoding.hospi.domain.HospitalRepository;


// 하루에 한번씩 다운로드해서 DB에 변경해주기
// pcr 검사기관이 추가 될 수 있기 때문에!!
// 4개 병원이 있다면...4개가 DB에 insert된다.
// 다음날에는 5개 병원이 되었다면 한개 추가하는 로직이 복잡할 것 같아서
// 그냥 4개 데이터 삭제해버리고 새로 추가해서 넣자.
// 공공데이터를 바로 바로 서비스해주는 방식은 하루에 트래픽이 1000이라서 서비스하기 힘들것같다.

@RequiredArgsConstructor        
@Component
public class HospDownloadBatch {

    //DI
    private final HospitalRepository hospitalRepository;

    @Scheduled(cron = "0 49 * * * *", zone = "Asia/Seoul")
    public void startBagtch() throws URISyntaxException {
        
        System.out.println("나 1분 마다 실행됨");

        //1.담을그릇준비
        List<Hospital> hospitals = new ArrayList<>();

        //2.api 한번 호출해서 totalcount확인
        RestTemplate rt = new RestTemplate();

        int totalCount = 2;

        String totalCounturl = "https://apis.data.go.kr/B551182/cv19EtgMsupHospService/getCv19EtgMsupHospList?serviceKey=Q51wiYKC3vaY1YhZR%2BHAds2pRgx9%2B4lPEuNvIJRltzO4FE9Tkh8B0jYw%2FMyxI2Uo7WguDJ4flwnQny52C6kytA==&pageNo=1&numOfRows="
                + totalCount + "&_type=json";

        URI totaluri = new URI(totalCounturl);

        ResponseDto totalCountDto = rt.getForObject(totaluri, ResponseDto.class);
        totalCount = totalCountDto.getResponse().getBody().getTotalCount();
        System.out.println(totalCount);

        //3.totalcount 가져오기
        String url = "https://apis.data.go.kr/B551182/cv19EtgMsupHospService/getCv19EtgMsupHospList?serviceKey=Q51wiYKC3vaY1YhZR%2BHAds2pRgx9%2B4lPEuNvIJRltzO4FE9Tkh8B0jYw%2FMyxI2Uo7WguDJ4flwnQny52C6kytA==&pageNo=1&numOfRows="
                + totalCount + "&_type=json";

        URI uri = new URI(url);
        System.out.println(uri);

        ResponseDto responseDto = rt.getForObject(uri, ResponseDto.class);

        List<Item> items = responseDto.getResponse().getBody().getItems().getItem();
        System.out.println("가져온 데이터 사이즈 : " + items.size());

        hospitals = items.stream().map(
                (e) -> {
                    return Hospital.builder()
                            .addr(e.getAddr())
                            .adtEndDd(e.getAdtEndDd())
                            .adtFrDd(e.getAdtFrDd())
                            .sgguNm(e.getSgguNm())
                            .sidoNm(e.getSidoNm())
                            .telno(e.getTelno())
                            .yadmNm(e.getYadmNm())
                            .ykiho(e.getYkiho())
                            .build();
                }).collect(Collectors.toList());

                // 삭제 테스트
                // 기존 데이터 다 삭제하기 (삭제 잘되는지 먼저 테스트 하기위해 yml - ddl auto update 로 변경)
                hospitalRepository.deleteAll();
                // 우선 다시 데이터 추가하고
                // 배치시간에 db에 insert 하기 (하루에 한번 할 예정 우선 테스트 지금 48분 47분으로 세팅하고 서버시작)
                hospitalRepository.saveAll(hospitals);
    }
}
