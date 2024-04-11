package bo.com.knowix.bl;

import bo.com.knowix.dao.PaymentMethodDAO;
import bo.com.knowix.dto.PaymentMethodDTO;
import bo.com.knowix.entity.PaymentMethodEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PaymentMethodBL {

    private final PaymentMethodDAO paymentMethodDAO;

    @Autowired
    public PaymentMethodBL(PaymentMethodDAO paymentMethodDAO) {
        this.paymentMethodDAO = paymentMethodDAO;
    }

    @Transactional
    public PaymentMethodDTO createPaymentMethod(PaymentMethodDTO dto) {
        PaymentMethodEntity entity = new PaymentMethodEntity();
        // Assuming all necessary fields are set in the dto
        entity.setCiPerson(dto.getCiPerson());
        entity.setNameOwner(dto.getNameOwner());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setQrImage(dto.getQrImage());
        entity.setAccountNumber(dto.getAccountNumber());
        entity.setBankBankId(dto.getBankBankId());
        entity.setKcUserKcUuid(dto.getKcUserKcUuid());
        entity.setAccountTypeAccountTypeId(dto.getAccountTypeAccountTypeId());
        paymentMethodDAO.save(entity);
        dto.setPaymentMethodId(entity.getPaymentMethodId()); // Set generated ID back to dto
        return dto;
    }

    public List<PaymentMethodDTO> findPaymentMethodsByKcUserKcUuid(String kcUserKcUuid) {
        return paymentMethodDAO.findByKcUserKcUuid(kcUserKcUuid).stream()
                .map(entity -> new PaymentMethodDTO(
                        entity.getPaymentMethodId(),
                        entity.getCiPerson(),
                        entity.getNameOwner(),
                        entity.getPhoneNumber(),
                        entity.getQrImage(),
                        entity.getAccountNumber(),
                        entity.getBankBankId(),
                        entity.getKcUserKcUuid(),
                        entity.getAccountTypeAccountTypeId()))
                .collect(Collectors.toList());
    }

    @Transactional
    public Optional<PaymentMethodDTO> updatePaymentMethod(String kcUserKcUuid, int paymentMethodId, PaymentMethodDTO dto) {
        Optional<PaymentMethodEntity> existingEntity = paymentMethodDAO.findById(paymentMethodId);
        if (existingEntity.isPresent() && existingEntity.get().getKcUserKcUuid().equals(kcUserKcUuid)) {
            PaymentMethodEntity entity = existingEntity.get();
            // Update fields from dto
            entity.setNameOwner(dto.getNameOwner());
            entity.setPhoneNumber(dto.getPhoneNumber());
            entity.setQrImage(dto.getQrImage());
            entity.setAccountNumber(dto.getAccountNumber());
            entity.setBankBankId(dto.getBankBankId());
            entity.setAccountTypeAccountTypeId(dto.getAccountTypeAccountTypeId());
            paymentMethodDAO.save(entity);
            return Optional.of(dto);
        }
        return Optional.empty();
    }

    public Optional<PaymentMethodDTO> findPaymentMethodById(int paymentMethodId) {
        return paymentMethodDAO.findById(paymentMethodId).map(entity -> new PaymentMethodDTO(
                entity.getPaymentMethodId(),
                entity.getCiPerson(),
                entity.getNameOwner(),
                entity.getPhoneNumber(),
                entity.getQrImage(),
                entity.getAccountNumber(),
                entity.getBankBankId(),
                entity.getKcUserKcUuid(),
                entity.getAccountTypeAccountTypeId()));
    }
}
