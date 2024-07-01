package team01.yaksutor.supplier.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import team01.yaksutor.dto.Medicine;

@Controller
@RequestMapping("/supplier")
@RequiredArgsConstructor
@Slf4j
public class SuMedicineController {
    @GetMapping("/myMedicineInsert")
    public String myMedicineInsert(Model model) {
        model.addAttribute("title", "내 의약품 등록");
        model.addAttribute("content", "내 의약품 등록");
        
        return "user/supplier/medicine/myMedicineInsert";
    }

    @GetMapping("/myMedicineSearchList")
    public String myMedicineSearchList(Model model) {
        model.addAttribute("title", "내 의약품 검색");
        model.addAttribute("content", "내 의약품 검색");

        return "user/supplier/medicine/myMedicineSearchList";
    }

    @GetMapping("/myMedicineModify")
    public String myMedicineModify(Model model) {
        model.addAttribute("title", "내 의약품 수정");
        model.addAttribute("content", "내 의약품 수정");

        return "user/supplier/medicine/myMedicineModify";
    }

    @GetMapping("/myMedicineDelete")
    public String myMedicineDelete(Model model) {
        model.addAttribute("title", "내 의약품 삭제");
        model.addAttribute("content", "내 의약품 삭제");

        return "user/supplier/medicine/myMedicineDelete";
    }

    @GetMapping("/myMedicineDetailView")
    public String myMedicineDetailView(Model model) {
        model.addAttribute("title", "내 의약품 상세");
        model.addAttribute("content", "내 의약품 상세");

        return "user/supplier/medicine/myMedicineDetailView";
    }

}
