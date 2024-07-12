package team01.yaksutor.admin.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import team01.yaksutor.admin.dto.*;
import team01.yaksutor.admin.mapper.AdMedicineMapper;
import team01.yaksutor.admin.service.AdMedicineService;
import team01.yaksutor.file.dto.FileRequest;
import team01.yaksutor.file.util.FileUtils;

import java.nio.channels.MulticastChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/medicine")
@RequiredArgsConstructor
@Slf4j
public class AdMedicineController {
    private final FileUtils fileUtils;
    private final AdMedicineMapper adMedicineMapper;
    private final AdMedicineService adMedicineService;
    List<AdIngredient> newAdIngredientList = new ArrayList<AdIngredient>();
    List<AdEfficacy> newAdEfficacyList = new ArrayList<AdEfficacy>();

    @GetMapping("/medicineInsert")
    public String medicineInsert(Model model) {
        model.addAttribute("title", "의약품 등록");
        model.addAttribute("content", "의약품 등록");
        return "admin/medicine/medicineInsert";
    }

    @GetMapping("/medicineSearchList")
    public String medicineSearchList(Model model,
                                     @RequestParam(value="currentPage", required = false, defaultValue = "1") int currentPage) {
        Map<String, Object> medicineListObject = adMedicineService.getMedicineList(currentPage);

        @SuppressWarnings("unchecked")
        List<SellMediInfo> medicineList = (List<SellMediInfo>) medicineListObject.get("medicineList");
        int lastPage = (int) medicineListObject.get("lastPage");
        int startPageNum = (int) medicineListObject.get("startPageNum");
        int endPageNum = (int) medicineListObject.get("endPageNum");

        model.addAttribute("title", "의약품 리스트");
        model.addAttribute("content", "의약품 리스트");
        model.addAttribute("medicineList", medicineList);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("lastPage", lastPage);
        model.addAttribute("startPageNum", startPageNum);
        model.addAttribute("endPageNum", endPageNum);

        return "admin/medicine/medicineSearchList";
    }

    @GetMapping("/medicineModify")
    public String medicineModify(Model model,
                                 @RequestParam(value="goodsCode") String goodsCode) {
        Map<String, Object> mediObj = adMedicineService.getMedicalInfo(goodsCode);
        RequestMedicine requestMedicine = (RequestMedicine) mediObj.get("requestMedicine");
        String suppName = mediObj.get("suppName").toString();

        model.addAttribute("requestMedicine", requestMedicine);
        model.addAttribute("title", "의약품 수정");
        model.addAttribute("content", "의약품 수정");

        return "admin/medicine/medicineModify";
    }

    @GetMapping("/medicineDelete")
    public String medicineDelete(Model model,
                                 @RequestParam(value="goodsCode") String goodsCode) {

        String mediCode = adMedicineMapper.getMediCode(goodsCode);
        String mediName = adMedicineMapper.getMedicineName(mediCode);

        model.addAttribute("mediCode", mediCode);
        model.addAttribute("mediName", mediName);
        model.addAttribute("title", "의약품 삭제");
        model.addAttribute("content", "의약품 삭제");

        return "admin/medicine/medicineDelete";
    }

    @GetMapping("/medicineDetailView")
    public String medicineDetailView(Model model,
                                     @RequestParam(value="goodsCode") String goodsCode) {
        Map<String, Object> mediObj = adMedicineService.getMedicalInfo(goodsCode);
        RequestMedicine requestMedicine = (RequestMedicine) mediObj.get("requestMedicine");
        String suppName = mediObj.get("suppName").toString();
        String effies ="";
        List<AdEfficacy> efficacyList = requestMedicine.getMedicalInfo().getEffiList();
        boolean first = true;
        for (AdEfficacy adEfficacy : efficacyList) {
            if(!first){
                effies += ", ";
                first = false;
            }
            effies += adEfficacy.getEfficacy();
            effies += " ";
        }

        model.addAttribute("effies", effies);
        model.addAttribute("requestMedicine", requestMedicine);
        model.addAttribute("suppName", suppName);
        model.addAttribute("title", "의약품 상세");
        model.addAttribute("content", "의약품 상세");

        return "admin/medicine/medicineDetailView";
    }

    @PostMapping("/checkLevel")
    @ResponseBody
    public int checkLevel(@RequestParam(value="regMId") String regMId,
                             Model model) {
        log.info("아이디 체크 : {}", regMId);
        int idLevel;
        idLevel = adMedicineMapper.checkLevel(regMId);

        return idLevel;
    }

    /*@PostMapping("/medicineInsert")
    public String medicineInsert(Model model,
                                 MedicalInfo medicalInfo,
                                 SellMediInfo sellMediInfo) {

        log.info("의약품 등록 Medicine: {}", medicalInfo);
        log.info("의약품 등록 SellMedicine: {}", sellMediInfo);


        return "redirect:/admin/adminMain";
    }*/

    @PostMapping("/medicineInsert")
    @ResponseBody
    public String medicineInsert(Model model,
                                 @RequestPart(value="requestMedicine") RequestMedicine requestMedicine,
                                 @RequestPart(value="img") List<MultipartFile> multipartFile){

        List<FileRequest> fileList = fileUtils.uploadFiles(multipartFile);
        fileList.forEach( file -> {
            log.info("file:{}", file);
            requestMedicine.getMedicalInfo().setMediImg(file.getFileIdx());
            requestMedicine.getSellMediInfo().setMediImg(file.getFileIdx());
        });
        log.info("requestMedicine: {}", requestMedicine);
        log.info("img: {}",multipartFile.size());
        adMedicineService.insertMedicine(requestMedicine, fileList);

        return "redirect:/admin/medicine/medicineSearchList";
    }

    @PostMapping("/medicineModify")
    @ResponseBody
    public String medicineModify(Model model,
                                 @RequestPart(value="requestMedicine") RequestMedicine requestMedicine,
                                 @RequestPart(value="img") List<MultipartFile> multipartFile){
        log.info("requestMedicine: {}", requestMedicine);

        List<FileRequest> fileList = fileUtils.uploadFiles(multipartFile);
        fileList.forEach( file -> {
            log.info("file:{}", file);
            requestMedicine.getMedicalInfo().setMediImg(file.getFileIdx());
            requestMedicine.getSellMediInfo().setMediImg(file.getFileIdx());
        });
        adMedicineService.modifyMedicine(requestMedicine, fileList);

        return "redirect:/admin/medicine/medicineSearchList";
    }

    @PostMapping("/medicineDelete")
    public String medicineDelete(@RequestParam(value="mediCode") String mediCode){
        adMedicineService.deleteMedicine(mediCode);

        return "redirect:/admin/medicine/medicineSearchList";
    }

}