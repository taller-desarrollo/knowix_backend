package bo.com.knowix.bl;

import bo.com.knowix.dao.ReplyDAO;
import bo.com.knowix.dao.repository.KcUserRepository;
import bo.com.knowix.api.ContentReportAPI;
import bo.com.knowix.dao.PurchaseDAO;
import bo.com.knowix.dto.ReplyDTO;
import bo.com.knowix.entity.ReplyEntity;
import bo.com.knowix.service.EmailService;
import bo.com.knowix.entity.KcUserEntity;
import bo.com.knowix.entity.PurchaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import java.util.logging.Logger;

@Service
public class ReplyBL{
    private final ReplyDAO replyDAO;
    private final PurchaseDAO purchaseDAO;

    private static final Logger LOGGER = Logger.getLogger(ContentReportAPI.class.getName());

    @Autowired
    private EmailService emailService;
    @Autowired
    private KcUserRepository kcUserRepository;

    @Autowired
    public ReplyBL(ReplyDAO replyDAO, PurchaseDAO purchaseDAO, EmailService emailService) {
        this.replyDAO = replyDAO;
        this.purchaseDAO = purchaseDAO;
        this.emailService = emailService;
    }

    @Transactional
    public List<ReplyDTO> findAll() {
        return replyDAO.findAll().stream().map(replyEntity -> new ReplyDTO(
                replyEntity.getReplyId(),
                replyEntity.isStatus(),
                replyEntity.getDate(),
                replyEntity.getComent(),
                replyEntity.getPurchase().getPurchaseId()
        )).collect(Collectors.toList());
    }

    // utilizando ReplyEntity findById (int id); de ReplyDAO:
    @Transactional
    public ReplyDTO findById(int id) {
        ReplyEntity replyEntity = replyDAO.findById(id);
        return new ReplyDTO(
                replyEntity.getReplyId(),
                replyEntity.isStatus(),
                replyEntity.getDate(),
                replyEntity.getComent(),
                replyEntity.getPurchase().getPurchaseId()
        );
    }

    @Transactional
    public ReplyDTO findByPurchaseId(int purchaseId) {
        List<ReplyEntity> replyEntities = replyDAO.findAll();
        for (ReplyEntity replyEntity : replyEntities) {
            if (replyEntity.getPurchase().getPurchaseId() == purchaseId) {
                return new ReplyDTO(
                        replyEntity.getReplyId(),
                        replyEntity.isStatus(),
                        replyEntity.getDate(),
                        replyEntity.getComent(),
                        replyEntity.getPurchase().getPurchaseId()
                );
            }
        }
        return null;
    }

    // crear un nuevo regitro de Reply:
    @Transactional
    public ReplyDTO create(ReplyDTO replyDTO) {
        ReplyEntity replyEntity = new ReplyEntity();
        replyEntity.setStatus(replyDTO.isStatus());
        replyEntity.setDate(replyDTO.getDate());
        replyEntity.setComent(replyDTO.getComent());

        LOGGER.info("Finding purchase by id: " + replyDTO.getPurchaseId());
        Optional<PurchaseEntity> purchaseEntity = purchaseDAO.findById(replyDTO.getPurchaseId());


        if (purchaseEntity.isPresent()) {
            LOGGER.info("Purchase found");
            replyEntity.setPurchase(purchaseEntity.get());
        }
        else {
            // TODO: control if its not present
        }
        
        replyDAO.save(replyEntity);

        LOGGER.info("Finding user by id: " + purchaseEntity.get().getKcUserKcUuid());
        KcUserEntity kcUser = kcUserRepository.findByKcUuid(purchaseEntity.get().getKcUserKcUuid());
        if(kcUser == null) {
            LOGGER.info("User not found");
            throw new RuntimeException("User not found");
        }
        else {
            String title = "";
            if (replyDTO.getComent().equals("Aprobado con Normalidad") == false) {
                title = "Tu compra ha sido aceptada";
            }
            else {
                title = "Tu compra ha sido rechazada con motivo: " + replyDTO.getComent();
            }
            LOGGER.info("User found, sending email to: " + kcUser.getEmail());
            emailService.sendEmail(kcUser.getEmail(), "Tu compra del curso a sido respondida", 
                title + "\n" +
                "Nombre del curso: " + purchaseEntity.get().getCourse().getCourseName() + "\n" +
                "Descripción del curso: " + purchaseEntity.get().getCourse().getCourseDescription() + "\n" +
                "Requerimientos del curso: " + purchaseEntity.get().getCourse().getCourseRequirements() + "\n" +
                "Precio del curso: " + purchaseEntity.get().getCourse().getCourseStandardPrice());
        }
                
        return new ReplyDTO(
                replyEntity.getReplyId(),
                replyEntity.isStatus(),
                replyEntity.getDate(),
                replyEntity.getComent(),
                replyEntity.getPurchase().getPurchaseId()
        );
    }


}