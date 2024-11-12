package site.metacoding.hospi.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import lombok.RequiredArgsConstructor;
import site.metacoding.hospi.domain.HospitalRepository;
import org.springframework.web.bind.annotation.GetMapping;


@RequiredArgsConstructor
@Controller
public class HospitalController {
    
    private final HospitalRepository hRepository;

    @GetMapping("/")
    public String home(String sidoNm, String sgguNm, Model model) {

        model.addAttribute("hospitals", hRepository.mFindHospitals(sidoNm, sgguNm));
        
        return "index"; 
    }
    
}
