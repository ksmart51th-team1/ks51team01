package team01.yaksutor.admin.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import team01.yaksutor.dto.Efficacy;
import team01.yaksutor.dto.Ingredient;
import team01.yaksutor.dto.Medicine;
import team01.yaksutor.dto.SellMedicine;

@Controller
@RequestMapping("/admin/medicine")
@RequiredArgsConstructor
@Slf4j
public class AdMedicineController {

    @GetMapping("/medicineInsert")
    public String medicineInsert(Model model) {
        model.addAttribute("title", "의약품 등록");
        model.addAttribute("content", "의약품 등록");
        return "admin/medicine/medicineInsert";
    }

    @PostMapping("/medicineInsert")
    public String medicineInsert(Medicine medicine,
                                 SellMedicine sellMedicine,
                                 Ingredient ingredient,
                                 Efficacy efficacy) {
        log.info("의약품 등록 Medicine: {}", medicine);
        log.info("의약품 등록 SellMedicine: {}", sellMedicine);
        log.info("의약품 등록 Ingredient: {}", ingredient);
        log.info("의약품 등록 Efficacy: {}", efficacy);

        return "redirect:/admin/adminMain";
    }

    @GetMapping("/medicineSearchList")
    public String medicineSearchList(Model model) {
        model.addAttribute("title", "의약품 리스트");
        model.addAttribute("content", "의약품 리스트");

        return "admin/medicine/medicineSearchList";
    }

    @GetMapping("/medicineModify")
    public String medicineModify(Model model) {

        model.addAttribute("title", "의약품 수정");
        model.addAttribute("content", "의약품 수정");

        return "admin/medicine/medicineModify";
    }

    @GetMapping("/medicineDelete")
    public String medicineDelete(Model model) {

        model.addAttribute("title", "의약품 삭제");
        model.addAttribute("content", "의약품 삭제");

        return "admin/medicine/medicineDelete";
    }

    @GetMapping("/medicineDetailView")
    public String medicineDetailView(Model model) {

        model.addAttribute("title", "의약품 상세");
        model.addAttribute("content", "의약품 상세");

        return "admin/medicine/medicineDetailView";
    }
}