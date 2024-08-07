package team01.yaksutor.admin.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import team01.yaksutor.admin.dto.*;
import team01.yaksutor.admin.mapper.AdMedicineMapper;
import team01.yaksutor.dto.Ingredient;
import team01.yaksutor.file.dto.FileRequest;
import team01.yaksutor.file.util.FileUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AdMedicineService {
    private final AdMedicineMapper adMedicineMapper;

    /**
     * 의약품 리스트 출력
     * 작성자: 길범진
     * @param currentPage
     * @return
     */
    public Map<String, Object> getMedicineList(int currentPage){
        // 보여줄 행의 갯수
        int rowPerPage = 10;
        // 테이블의 조회할 시작행
        int startRow = (currentPage - 1) * rowPerPage;

        // 시작할  페이지 설정 초기화
        int startPageNum = 1;

        // 마지막 페이지 설정 초기화
        int endPageNum = 10;

        List<SellMediInfo> medicineList = adMedicineMapper.getMedicineList(startRow, rowPerPage);

        double cnt = adMedicineMapper.getMedicineListCnt();

        // 마지막 페이지
        int lastPage = (int) Math.ceil(cnt/rowPerPage);

        endPageNum = lastPage < 10 ? lastPage : endPageNum;

        // 동적 페이지 설정
        if(currentPage > 6 && lastPage > 9){
            startPageNum = currentPage - 5;
            endPageNum = currentPage + 4;
            // 마지막 페이지 번호가 마지막 페이지 수보다 클경우에 페이지 번호를 고정
            if(endPageNum >= lastPage){
                startPageNum = lastPage - 9;
                endPageNum = lastPage;
            }
        }

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("medicineList", medicineList);
        resultMap.put("lastPage", lastPage);
        resultMap.put("startPageNum", startPageNum);
        resultMap.put("endPageNum", endPageNum);

        return resultMap;
    }

    /**
     * 의약품 등록 트랜젝션
     * 작성자: 길범진
     * @param requestMedicine
     * @param fileRequestList
     */
    @Transactional
    public void insertMedicine(RequestMedicine requestMedicine,
                               List<FileRequest> fileRequestList){
        MedicalInfo medicalInfo = requestMedicine.getMedicalInfo();
        SellMediInfo sellMediInfo = requestMedicine.getSellMediInfo();
        // 이미지 가공
        for(FileRequest fileRequest : fileRequestList){
            adMedicineMapper.insertImg(fileRequest);
        }

        log.info("adMedicine: {}" , medicalInfo);
        adMedicineMapper.insertMedicine(medicalInfo);
        log.info("adMedicine: {}" , medicalInfo);
        String regMId = requestMedicine.getMedicalInfo().getRegMId();
        String mediCode = requestMedicine.getMedicalInfo().getMediCode();

        // 효능 불특정 다수 등록
        List<AdEfficacy> effiList = requestMedicine.getMedicalInfo().getEffiList();
        effiList.forEach(effi -> {
            effi.setRegMId(regMId);
            effi.setMediCode(mediCode);
            adMedicineMapper.insertEfficacy(effi);
        });

        // 성분 불특정 다수 등록
        List<AdIngredient> ingrList = requestMedicine.getMedicalInfo().getIngrList();
        log.info("ingrList: {}" , ingrList);
        ingrList.forEach(ingr -> {
            ingr.setRegMId(regMId);
            ingr.setMediCode(mediCode);
            log.info("ingrList: {}" , ingr);
            adMedicineMapper.insertIngredient(ingr);
        });
        String suppCode = adMedicineMapper.getSuppCode(regMId);
        String mediName = requestMedicine.getMedicalInfo().getMediName();
        sellMediInfo.setSuppCode(suppCode);
        sellMediInfo.setMediCode(mediCode);
        sellMediInfo.setMediName(mediName);
        sellMediInfo.setState("판매중");
        adMedicineMapper.insertSellMedicine(sellMediInfo);
    }

    /**
     * 수정할 의약품의 정보 검색 및 리턴
     * 작성자: 길범진
     * @param goodsCode
     * @return
     */
    public Map<String, Object> getMedicalInfo(String goodsCode){
        MedicalInfo medicalInfo = adMedicineMapper.getMedicineInfo(goodsCode);
        String suppName = adMedicineMapper.getSuppName(goodsCode);
        SellMediInfo sellMediInfo = adMedicineMapper.getSellMediInfo(goodsCode);
        RequestMedicine requestMedicine = new RequestMedicine();
        requestMedicine.setMedicalInfo(medicalInfo);
        requestMedicine.setSellMediInfo(sellMediInfo);
        String mediCode = medicalInfo.getMediCode();
        List<AdIngredient> ingrList = adMedicineMapper.getAdIngredient(mediCode);
        List<AdEfficacy> effiList = adMedicineMapper.getAdEfficacy(mediCode);
        requestMedicine.getMedicalInfo().setIngrList(ingrList);
        requestMedicine.getMedicalInfo().setEffiList(effiList);
        log.info("medicalInfo: {}", medicalInfo);
        Map<String, Object> mediObj = new HashMap<>();

        mediObj.put("requestMedicine", requestMedicine);
        mediObj.put("suppName", suppName);

        return mediObj;
    }

    /**
     * 의약품 수정 트랜젝션
     * 작성자: 길범진
     * @param requestMedicine
     * @param fileRequestList
     */
    @Transactional
    public void modifyMedicine(RequestMedicine requestMedicine,
                               @RequestParam(value="fileList") List<FileRequest> fileRequestList){
        String regMId = requestMedicine.getMedicalInfo().getRegMId();
        String suppCode = adMedicineMapper.getSuppCode(regMId);
        String mediCode = requestMedicine.getMedicalInfo().getMediCode();

        MedicalInfo medicalInfo = requestMedicine.getMedicalInfo();
        SellMediInfo sellMediInfo = requestMedicine.getSellMediInfo();
        if(fileRequestList != null){
            for(FileRequest fileRequest : fileRequestList){
                adMedicineMapper.insertImg(fileRequest);
            }
        }else{
            String oriImg = adMedicineMapper.getMedicineImg(mediCode);
            medicalInfo.setMediImg(oriImg);
            sellMediInfo.setMediImg(oriImg);
        }

        adMedicineMapper.updateMedicine(medicalInfo);
        adMedicineMapper.deleteIngredient(mediCode);
        adMedicineMapper.deleteEfficacy(mediCode);

        List<AdIngredient> ingrList = medicalInfo.getIngrList();
        ingrList.forEach(ingr -> {
            ingr.setRegMId(regMId);
            ingr.setMediCode(mediCode);
            log.info("ingrList: {}" , ingr);
            adMedicineMapper.insertIngredient(ingr);
        });

        List<AdEfficacy> effiList = medicalInfo.getEffiList();
        effiList.forEach(effi -> {
            effi.setRegMId(regMId);
            effi.setMediCode(mediCode);
            adMedicineMapper.insertEfficacy(effi);
        });

        sellMediInfo.setMediName(medicalInfo.getMediName());
        sellMediInfo.setSuppCode(suppCode);
        sellMediInfo.setMediCode(mediCode);
        adMedicineMapper.updateSellMedicine(sellMediInfo);
    }

    /**
     * 의약품 수정 트랜젝션
     * 작성자: 길범진
     * @param mediCode
     */
    @Transactional
    public void deleteMedicine(String mediCode){
        adMedicineMapper.deleteSellMedicine(mediCode);
    }
}
