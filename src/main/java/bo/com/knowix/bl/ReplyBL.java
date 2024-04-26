package bo.com.knowix.bl;

import bo.com.knowix.dao.ReplyDAO;
import bo.com.knowix.dao.PurchaseDAO;
import bo.com.knowix.dto.ReplyDTO;
import bo.com.knowix.entity.ReplyEntity;
import bo.com.knowix.entity.PurchaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReplyBL{
    private final ReplyDAO replyDAO;
    private final PurchaseDAO purchaseDAO;

    @Autowired
    public ReplyBL(ReplyDAO replyDAO, PurchaseDAO purchaseDAO) {
        this.replyDAO = replyDAO;
        this.purchaseDAO = purchaseDAO;
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


}