package site.metacoding.hospi.batch;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;


// 하루에 한번씩 다운로드해서 DB에 변경해주기
// pcr 검사기관이 추가 될 수 있기 때문에!!
// 4개 병원이 있다면...4개가 DB에 insert된다.
// 다음날에는 5개 병원이 되었다면 한개 추가하는 로직이 복잡할 것 같아서
// 그냥 4개 데이터 삭제해버리고 새로 추가해서 넣자.
// 공공데이터를 바로 바로 서비스해주는 방식은 하루에 트래픽이 1000이라서 서비스하기 힘들것같다.

@RequiredArgsConstructor
@Component
public class HospDownloadBatch {
    
    @Scheduled(cron="0 * * * * *", zone = "Asia/Seoul")
    public void startBagtch() {

        System.out.println("나 1분 마다 실행됨");

    }

}
