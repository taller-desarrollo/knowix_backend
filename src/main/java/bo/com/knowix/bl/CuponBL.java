package bo.com.knowix.bl;

import bo.com.knowix.dao.CuponDAO;
import bo.com.knowix.dto.CuponDTO;
import bo.com.knowix.entity.CuponEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CuponBL {

    @Autowired
    private CuponDAO cuponDAO;

    // Crear un cupón
    @Transactional
    public CuponDTO createCupon(CuponDTO cuponDTO) {
        try {
            CuponEntity cuponEntity = convertToEntity(cuponDTO);
            cuponEntity = cuponDAO.save(cuponEntity);
            return convertToDTO(cuponEntity);
        } catch (Exception e) {
            throw new RuntimeException("Error al crear el cupón: " + e.getMessage(), e);
        }
    }

    // Listar todos los cupones
    public List<CuponDTO> listCupones() {
        try {
            List<CuponEntity> cuponEntities = cuponDAO.findAll();
            if (cuponEntities.isEmpty()) {
                throw new RuntimeException("No se encontraron cupones.");
            }
            return cuponEntities.stream().map(this::convertToDTO).collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error al listar los cupones: " + e.getMessage(), e);
        }
    }

    // Buscar un cupón por ID
    public CuponDTO getCuponById(int cuponId) {
        try {
            Optional<CuponEntity> cuponEntityOptional = cuponDAO.findById(cuponId);
            if (cuponEntityOptional.isPresent()) {
                return convertToDTO(cuponEntityOptional.get());
            } else {
                throw new RuntimeException("Cupón no encontrado para el ID: " + cuponId);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar el cupón por ID: " + e.getMessage(), e);
        }
    }

    // Convertir de DTO a Entity
    private CuponEntity convertToEntity(CuponDTO cuponDTO) {
        CuponEntity cuponEntity = new CuponEntity();
        cuponEntity.setCuponId(cuponDTO.getCuponId());
        cuponEntity.setDiscountType(cuponDTO.getDiscountType());
        cuponEntity.setDiscountAmount(cuponDTO.getDiscountAmount());
        cuponEntity.setCuponCode(cuponDTO.getCuponCode());
        cuponEntity.setStartDate(cuponDTO.getStartDate());
        cuponEntity.setEndDate(cuponDTO.getEndDate());
        cuponEntity.setMinAmountPurchase(cuponDTO.getMinAmountPurchase());
        cuponEntity.setDescriptionPromotion(cuponDTO.getDescriptionPromotion());
        return cuponEntity;
    }

    // Convertir de Entity a DTO
    private CuponDTO convertToDTO(CuponEntity cuponEntity) {
        CuponDTO cuponDTO = new CuponDTO();
        cuponDTO.setCuponId(cuponEntity.getCuponId());
        cuponDTO.setDiscountType(cuponEntity.getDiscountType());
        cuponDTO.setDiscountAmount(cuponEntity.getDiscountAmount());
        cuponDTO.setCuponCode(cuponEntity.getCuponCode());
        cuponDTO.setStartDate(cuponEntity.getStartDate());
        cuponDTO.setEndDate(cuponEntity.getEndDate());
        cuponDTO.setMinAmountPurchase(cuponEntity.getMinAmountPurchase());
        cuponDTO.setDescriptionPromotion(cuponEntity.getDescriptionPromotion());
        return cuponDTO;
    }
}
