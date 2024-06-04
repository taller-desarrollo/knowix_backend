package bo.com.knowix.bl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bo.com.knowix.dao.PurchaseCuponDAO;
import bo.com.knowix.dto.PurchaseCuponDTO;
import bo.com.knowix.entity.PurchaseCuponEntity;
import jakarta.transaction.Transactional;

@Service
public class PurchaseCuponBL {
    
    private final PurchaseCuponDAO purchaseCuponDAO;

    @Autowired
    public PurchaseCuponBL(PurchaseCuponDAO purchaseCuponDAO) {
        this.purchaseCuponDAO = purchaseCuponDAO;
    }

    // Crear un purchaseCupon
    @Transactional
    public PurchaseCuponDTO createPurchaseCupon(PurchaseCuponDTO dto) {
        PurchaseCuponEntity entity = new PurchaseCuponEntity();
        entity.setPurchasePurchaseId(dto.getPurchasePurchaseId());
        entity.setCuponCuponId(dto.getCuponCuponId());
        purchaseCuponDAO.save(entity);
        dto.setPurchaseCuponId(entity.getPurchaseCuponId());
        return dto;
    }

    // Obtener todos los purchaseCupons por purchasePurchaseId
    public List<PurchaseCuponDTO> findPurchaseCuponsByPurchasePurchaseId(Integer purchasePurchaseId) {
        return purchaseCuponDAO.findByPurchasePurchaseId(purchasePurchaseId).stream()
                .map(entity -> new PurchaseCuponDTO(
                        entity.getPurchaseCuponId(),
                        entity.getPurchasePurchaseId(),
                        entity.getCuponCuponId()))
                .collect(Collectors.toList());
    }

    // Obtener todos los purchaseCupons por cuponCuponId
    public List<PurchaseCuponDTO> findPurchaseCuponsByCuponCuponId(Integer cuponCuponId) {
        return purchaseCuponDAO.findByCuponCuponId(cuponCuponId).stream()
                .map(entity -> new PurchaseCuponDTO(
                        entity.getPurchaseCuponId(),
                        entity.getPurchasePurchaseId(),
                        entity.getCuponCuponId()))
                .collect(Collectors.toList());
    }

    // Obtener todos los purchaseCupons
    public List<PurchaseCuponDTO> findAllPurchaseCupons() {
        return purchaseCuponDAO.findAll().stream()
                .map(entity -> new PurchaseCuponDTO(
                        entity.getPurchaseCuponId(),
                        entity.getPurchasePurchaseId(),
                        entity.getCuponCuponId()))
                .collect(Collectors.toList());
    }
}