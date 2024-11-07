package site.metacoding.hospi.batch;

import java.util.List;

import lombok.Data;

// 1. 임포트 하기 (alt+shift+o) - 인텔리J 키맵 사용
// 2. private -> private 으로 변경하기 (ctrl+r)
// 3. lombok 사용하기

@Data
class Body {
    private Items items;
    private Integer numOfRows;
    private Integer pageNo;
    private Integer totalCount;
}
@Data
class Header {
    private String resultCode;
    private String resultMsg;
}
@Data
class Item {
    private String addr;
    private Integer adtEndDd;
    private Integer adtFrDd;
    private String sgguNm;
    private String sidoNm;
    private String telno;
    private String yadmNm;
    private String ykiho;
}

@Data
class Items {
    public List<Item> item;
}

@Data
class Response {
    private Header header;
    private Body body;
}

@Data
public class ResponseDto {
    
    private Response response;
}
