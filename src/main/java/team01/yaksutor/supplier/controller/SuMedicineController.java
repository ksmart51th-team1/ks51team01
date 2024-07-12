package team01.yaksutor.supplier.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import team01.yaksutor.dto.*;
import team01.yaksutor.supplier.mapper.SuMedicineMapper;
import team01.yaksutor.supplier.service.SuMedicineService;

import java.util.List;

@Controller
@RequestMapping("/supplier")
@RequiredArgsConstructor
@Slf4j
public class SuMedicineController {
    private final HttpServletRequest request;
    private final SuMedicineService suMedicineService;
    private final SuMedicineMapper suMedicineMapper;

    @GetMapping("/myMedicineInsert")
    public String myMedicineInsert(Model model) {
        model.addAttribute("title", "내 의약품 등록");
        model.addAttribute("content", "내 의약품 등록");
        
        return "user/supplier/medicine/myMedicineInsert";
    }

    @GetMapping("/myMedicineSearchList")
    public String myMedicineSearchList(Model model) {
        String sid = request.getSession().getAttribute("S_ID").toString();
        log.info("sid: {}", sid);
        String suppCode = suMedicineMapper.getSuppCode(sid);
        List<SellMedicine> sellMediList = suMedicineService.getSellMediList(suppCode);


        model.addAttribute("sellMediList", sellMediList);
        model.addAttribute("title", "내 의약품 검색");
        model.addAttribute("content", "내 의약품 검색");

        return "user/supplier/medicine/myMedicineSearchList";
    }

    @GetMapping("/myMedicineModify")
    public String myMedicineModify(Model model,
                                   @RequestParam(value="goodsCode") String goodsCode) {
        String sid = request.getSession().getAttribute("S_ID").toString();
        String mediCode = suMedicineMapper.getMediCode(goodsCode);
        Medicine medicine = suMedicineMapper.getMedicineInfo(goodsCode);
        SellMedicine sellMedicine = suMedicineMapper.getSellMedicineInfo(goodsCode);
        List<Ingredient> ingrList = suMedicineMapper.getIngrList(mediCode);
        List<Efficacy> effiList = suMedicineMapper.getEffiList(mediCode);
        MedicineInfo mediInfo = new MedicineInfo();
        mediInfo.setMedicine(medicine);
        mediInfo.setSellMedicine(sellMedicine);
        mediInfo.setIngrList(ingrList);
        mediInfo.setEffiList(effiList);
        log.info("mediInfo: {}", mediInfo);
        model.addAttribute("mediInfo", mediInfo);
        model.addAttribute("title", "내 의약품 수정");
        model.addAttribute("content", "내 의약품 수정");

        return "user/supplier/medicine/myMedicineModify";
    }

    @GetMapping("/myMedicineDelete")
    public String myMedicineDelete(Model model,
                                   @RequestParam(value="goodsCode") String goodsCode) {

        String mediCode = suMedicineMapper.getMediCode(goodsCode);
        Medicine medicine = suMedicineMapper.getMedicineInfo(goodsCode);
        String mediName = medicine.getMediName();

        model.addAttribute("mediName", mediName);
        model.addAttribute("mediCode", mediCode);
        model.addAttribute("title", "내 의약품 삭제");
        model.addAttribute("content", "내 의약품 삭제");

        return "user/supplier/medicine/myMedicineDelete";
    }

    @GetMapping("/myMedicineDetailView")
    public String myMedicineDetailView(Model model,
                                       @RequestParam(value="goodsCode") String goodsCode) {
        String suppName = suMedicineMapper.getSuppName(goodsCode);
        String mediCode = suMedicineMapper.getMediCode(goodsCode);
        Medicine medicine = suMedicineMapper.getMedicineInfo(goodsCode);
        SellMedicine sellMedicine = suMedicineMapper.getSellMedicineInfo(goodsCode);
        List<Ingredient> ingrList = suMedicineMapper.getIngrList(mediCode);
        List<Efficacy> effiList = suMedicineMapper.getEffiList(mediCode);
        MedicineInfo mediInfo = new MedicineInfo();
        mediInfo.setMedicine(medicine);
        mediInfo.setSellMedicine(sellMedicine);
        mediInfo.setIngrList(ingrList);
        mediInfo.setEffiList(effiList);
        log.info("mediInfo: {}", mediInfo);
        String effies = "";
        for(int i = 0; i < effiList.size(); i++){
            effies += effiList.get(i).getEfficacy();
            if(i != effiList.size() - 1){
                effies += ", ";
            }
        }

        model.addAttribute("effies", effies);
        model.addAttribute("suppName", suppName);
        model.addAttribute("mediInfo", mediInfo);
        model.addAttribute("title", "내 의약품 상세");
        model.addAttribute("content", "내 의약품 상세");

        return "user/supplier/medicine/myMedicineDetailView";
    }

    @PostMapping("/insertMedicine")
    @ResponseBody
    public String insertMedicine(Model model,
                                 @RequestPart(value="medicineInfo") MedicineInfo medicineInfo,
                                 @RequestPart(value="img") List<MultipartFile> multipartFile) {
        log.info("medicineInfo: {}", medicineInfo);
        log.info("multipartFile: {}", multipartFile);
        suMedicineService.insertMedicine(medicineInfo, multipartFile);

        return "redirect:/supplier/myMedicineSearchList";
    }

    @PostMapping("/modifyMedicine")
    @ResponseBody
    public String modifyMedicine(Model model,
                                 @RequestPart(value="medicineInfo") MedicineInfo medicineInfo,
                                 @RequestPart(value="img") List<MultipartFile> multipartFile){
        log.info("medicineInfo: {}", medicineInfo);
        log.info("multipartFile: {}", multipartFile);

        suMedicineService.modifyMedicine(medicineInfo, multipartFile);

        return "redirect:/supplier/myMedicineSearchList";
    }

    @PostMapping("/myMedicineDelete")
    public String deleteMedicine(@RequestParam(value="mediCode") String mediCode){
        suMedicineMapper.deleteSellMedicine(mediCode);
        return "redirect:/supplier/myMedicineSearchList";
    }
}
