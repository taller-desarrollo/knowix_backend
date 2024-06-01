package bo.com.knowix.bl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bo.com.knowix.dao.PurchaseCuponDAO;
import bo.com.knowix.dto.CuponDTO;
import bo.com.knowix.dto.PurchaseCuponDTO;
import bo.com.knowix.dto.PurchaseDTO;
import bo.com.knowix.entity.PurchaseCuponEntity;
import jakarta.transaction.Transactional;

@Service
public class PurchaseCuponBL {
    
    private final PurchaseCuponDAO purchaseCuponDAO;

    @Autowired
    public PurchaseCuponBL(PurchaseCuponDAO purchaseCuponDAO, PurchaseDTO purchaseDTO, CuponDTO cuponDTO) {
        this.purchaseCuponDAO = purchaseCuponDAO;
    }

    //crear un purchaseCupon
    @Transactional
    public PurchaseCuponDTO createPurchaseCupon(PurchaseCuponDTO dto) {
        PurchaseCuponEntity entity = new PurchaseCuponEntity();
        entity.setPurchasePurchaseId(dto.getPurchasePurchaseId());
        entity.setCuponCuponId(dto.getCuponCuponId());
        purchaseCuponDAO.save(entity);
        dto.setPurchaseCuponId(entity.getPurchaseCuponId());
        return dto;
    }

    //obtener todos los purchaseCupons por purchasePurchaseId
    public List<PurchaseCuponDTO> findPurchaseCuponsByPurchasePurchaseId(Integer purchasePurchaseId) {
        return purchaseCuponDAO.findById(purchasePurchaseId).stream()
                .map(entity -> new PurchaseCuponDTO(
                        entity.getPurchaseCuponId(),
                        entity.getPurchasePurchaseId(),
                        entity.getCuponCuponId()))
                .collect(Collectors.toList());
    }

    //obtener todos los purchaseCupons
    public List<PurchaseCuponDTO> findAllPurchaseCupons() {
        return purchaseCuponDAO.findAll().stream()
                .map(entity -> new PurchaseCuponDTO(
                        entity.getPurchaseCuponId(),
                        entity.getPurchasePurchaseId(),
                        entity.getCuponCuponId()))
                .collect(Collectors.toList());
    }
}
