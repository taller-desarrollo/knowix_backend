package bo.com.knowix.api;

import bo.com.knowix.bl.PurchaseBL;
import bo.com.knowix.dto.PurchaseDTO;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.sql.Timestamp;

import java.math.BigDecimal; 
import java.util.List;

import bo.com.knowix.bl.ReplyBL;
import bo.com.knowix.dto.ReplyDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/reply")
public class ReplyAPI {

    private final ReplyBL replyBL;

    @Autowired
    public ReplyAPI(ReplyBL replyBL) {
        this.replyBL = replyBL;
    }

    @GetMapping
    public ResponseEntity<List<ReplyDTO>> findAll() {
        return new ResponseEntity<>(replyBL.findAll(), HttpStatus.OK);
    }

    // utilizando public ReplyDTO findById(int id) {:
    @GetMapping("/{id}")
    public ResponseEntity<ReplyDTO> findById(@PathVariable int id) {
        return new ResponseEntity<>(
                replyBL.findById(id),
                HttpStatus.OK
        );
    }

    // utilizando     public ReplyDTO findByPurchaseId(int purchaseId) {:
    @GetMapping("/purchase/{purchaseId}")
    public ResponseEntity<ReplyDTO> findByPurchaseId(@PathVariable int purchaseId) {
        return new ResponseEntity<>(
                replyBL.findByPurchaseId(purchaseId),
                HttpStatus.OK
        );
    }

    //utilizando     public ReplyDTO create(ReplyDTO replyDTO) {:
    @PostMapping
    public ResponseEntity<ReplyDTO> create(@RequestBody ReplyDTO replyDTO) {
        return new ResponseEntity<>(
                replyBL.create(replyDTO),
                HttpStatus.CREATED
        );
    }



}
