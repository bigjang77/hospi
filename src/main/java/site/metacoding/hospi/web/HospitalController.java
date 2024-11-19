package site.metacoding.hospi.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import lombok.RequiredArgsConstructor;
import site.metacoding.hospi.domain.Hospital;
import site.metacoding.hospi.domain.HospitalRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;




@RequiredArgsConstructor
@Controller
public class HospitalController {
    
    
    private final HospitalRepository hRepository;

    @GetMapping("/")
    public String index(String sidoNm, String sgguNm, Model model) {

        model.addAttribute("sidoNms", hRepository.mFindSidoNm());//복수나까 s넣자
        model.addAttribute("sgguNms", hRepository.mFindSggunm("강원"));//복수니까 s넣자
        
        return "index";
    }
    
    @GetMapping("/api/hospital")
    public @ResponseBody List<Hospital> hospitals(String sidoNm, String sgguNm) {
        System.out.println("몇개노?"+hRepository.mFindSidoNm().size());

        return hRepository.mFindHospitals(sidoNm, sgguNm);
    }
    
    
    @GetMapping("/api/sgguNm")
    //응답 json으로 할예정
    public @ResponseBody List<String> sgguNm(String sidoNm) {
        return hRepository.mFindSggunm(sidoNm);
    }
    
    
}
