package team01.yaksutor.supplier.service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import team01.yaksutor.admin.dto.RequestMedicine;
import team01.yaksutor.dto.*;
import team01.yaksutor.file.dto.FileRequest;
import team01.yaksutor.file.util.FileUtils;
import team01.yaksutor.supplier.mapper.SuMedicineMapper;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class SuMedicineService {
    private final SuMedicineMapper suMedicineMapper;
    private final FileUtils fileUtils;
    private final HttpServletRequest request;

    public List<SellMedicine> getSellMediList(String suppCode){
        List<SellMedicine> sellMediList = suMedicineMapper.getListSellMedicine(suppCode);

        return sellMediList;
    }

    @Transactional
    public void insertMedicine(MedicineInfo medicineInfo,
                               List<MultipartFile> multipartFile) {
        String sid = request.getSession().getAttribute("S_ID").toString();
        log.info("sid: {}", sid);
        Medicine medicine = medicineInfo.getMedicine();
        SellMedicine sellMedicine = medicineInfo.getSellMedicine();
        List<Ingredient> ingrList = medicineInfo.getIngrList();
        List<Efficacy> effiList = medicineInfo.getEffiList();
        List<FileRequest> fileList = fileUtils.uploadFiles(multipartFile);
        fileList.forEach( file -> {
            log.info("file:{}", file);
            medicine.setMediImg(file.getFileIdx());
            sellMedicine.setMediImg(file.getFileIdx());
        });
        for(FileRequest fileRequest : fileList){
            suMedicineMapper.insertImg(fileRequest);
        }

        medicine.setRegMId(sid);
        suMedicineMapper.insertMedicine(medicine);
        String mediCode = medicine.getMediCode();
        sellMedicine.setSuppCode(suMedicineMapper.getSuppCode(sid));
        sellMedicine.setMediName(medicine.getMediName());
        sellMedicine.setMediCode(medicine.getMediCode());
        suMedicineMapper.insertSellMedicine(sellMedicine);
        ingrList.forEach( ingr -> {
            ingr.setMediCode(mediCode);
            ingr.setRegMId(sid);
            suMedicineMapper.insertIngredient(ingr);
        });
        effiList.forEach( effi -> {
           effi.setMediCode(mediCode);
           effi.setRegMId(sid);
           suMedicineMapper.insertEfficacy(effi);
        });
    }

    @Transactional
    public void modifyMedicine(MedicineInfo medicineInfo,
                               List<MultipartFile> multipartFile){
        Medicine medicine = medicineInfo.getMedicine();
        SellMedicine sellMedicine = medicineInfo.getSellMedicine();
        List<Ingredient> ingrList = medicineInfo.getIngrList();
        List<Efficacy> effiList = medicineInfo.getEffiList();
        String mediCode = medicine.getMediCode();
        String sid = request.getSession().getAttribute("S_ID").toString();
        String suppCode = suMedicineMapper.getSuppCode(sid);

        List<FileRequest> fileList = fileUtils.uploadFiles(multipartFile);
        fileList.forEach( file -> {
            log.info("file:{}", file);
            medicine.setMediImg(file.getFileIdx());
            sellMedicine.setMediImg(file.getFileIdx());
        });
        for(FileRequest fileRequest : fileList){
            suMedicineMapper.insertImg(fileRequest);
        }

        medicine.setRegMId(sid);
        suMedicineMapper.updateMedicine(medicine);

        sellMedicine.setSuppCode(suppCode);
        sellMedicine.setMediName(medicine.getMediName());

        sellMedicine.setMediCode(medicine.getMediCode());
        suMedicineMapper.updateSellMedicine(sellMedicine);

        suMedicineMapper.deleteIngredient(mediCode);
        suMedicineMapper.deleteEfficacy(mediCode);

        ingrList.forEach( ingr -> {
            ingr.setMediCode(medicine.getMediCode());
            ingr.setRegMId(sid);
            suMedicineMapper.insertIngredient(ingr);
        });

        effiList.forEach( effi -> {
            effi.setMediCode(medicine.getMediCode());
            effi.setRegMId(sid);
            suMedicineMapper.insertEfficacy(effi);
        });
    }
}
