package site.metacoding.hospi.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@AllArgsConstructor
@ToString
@Getter
@Entity
public class Hospital {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;//auto)incresement

    private String addr; //주소
    private Integer adtEndDd; //요양종료일자
    private Integer adtFrDd; //요양개시일자
    private String sgguNm; //시군구명
    private String sidoNm; //시도명
    private String telno; //전화번호
    private String yadmNm; //요양기관명
    private String ykiho; //요양기관번호
}
