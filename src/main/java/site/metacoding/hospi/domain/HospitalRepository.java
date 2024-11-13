package site.metacoding.hospi.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HospitalRepository extends JpaRepository<Hospital,Integer>{

    @Query(value = "SELECT * FROM hospital WHERE sidoNm = :sidoNm And sgguNm = :sgguNm", nativeQuery = true)
    public List<Hospital> mFindHospitals(String sidoNm, String sgguNm);

    @Query(value = "SELECT distinct sidoNm FROM hospital ORDER BY sidoNm", nativeQuery = true)
    public List<String> mFindSidoNm();

    @Query(value = "SELECT distinct sgguNm FROM HOSPITAL WHERE sidoNm = :sidoNm order by sgguNm", nativeQuery = true)
    public List<String> mFindSggunm(@Param("sidoNm") String sidoNm);
}
