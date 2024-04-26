package bo.com.knowix.dto;

import java.time.LocalDateTime;

public class ReplyDTO {

    private int replyId;
    private boolean status;
    private LocalDateTime date;
    private String coment;
    private int purchaseId;

    public ReplyDTO() {
    }

    public ReplyDTO(int replyId, boolean status, LocalDateTime date, String coment, int purchaseId) {
        this.replyId = replyId;
        this.status = status;
        this.date = date;
        this.coment = coment;
        this.purchaseId = purchaseId;
    }

    // Getters and Setters

    public int getReplyId() {
        return replyId;
    }

    public void setReplyId(int replyId) {
        this.replyId = replyId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getComent() {
        return coment;
    }

    public void setComent(String coment) {
        this.coment = coment;
    }

    public int getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(int purchaseId) {
        this.purchaseId = purchaseId;
    }

    @Override
    public String toString() {
        return "ReplyDTO{" +
                "replyId=" + replyId +
                ", status=" + status +
                ", date=" + date +
                ", coment='" + coment + '\'' +
                ", purchaseId=" + purchaseId +
                '}';
    }
}
