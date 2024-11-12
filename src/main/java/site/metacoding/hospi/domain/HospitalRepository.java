package site.metacoding.hospi.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface HospitalRepository extends JpaRepository<Hospital,Integer>{

    @Query(value = "SELECT * FROM hospital WHERE sidoNm = :sidoNm And sgguNm = :sgguNm", nativeQuery = true)
    public List<Hospital> mFindHospitals(String sidoNm, String sgguNm);
}
