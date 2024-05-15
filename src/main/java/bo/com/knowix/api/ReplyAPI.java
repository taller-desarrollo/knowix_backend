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
import java.util.logging.Logger;

import bo.com.knowix.bl.ReplyBL;
import bo.com.knowix.dto.ReplyDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reply")
public class ReplyAPI {

    private final ReplyBL replyBL;
    private static final Logger LOGGER = Logger.getLogger(ReplyAPI.class.getName());

    @Autowired
    public ReplyAPI(ReplyBL replyBL) {
        this.replyBL = replyBL;
    }

    @GetMapping
    public ResponseEntity<List<ReplyDTO>> findAll() {
        LOGGER.info("Starting process to find all replies");
        try {
            LOGGER.info("Replies found successfully");
            return new ResponseEntity<>(replyBL.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.warning("Error occurred while finding all replies: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } finally {
            LOGGER.info("Finished process to find all replies");
        }
    }

    // utilizando public ReplyDTO findById(int id) {:
    @GetMapping("/{id}")
    public ResponseEntity<ReplyDTO> findById(@PathVariable int id) {
        LOGGER.info("Starting process to find reply by id");
        try {
            LOGGER.info("Reply found successfully");
            return new ResponseEntity<>(replyBL.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.warning("Error occurred while finding reply by id: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } finally {
            LOGGER.info("Finished process to find reply by id");
        }
    }

    // utilizando public ReplyDTO findByPurchaseId(int purchaseId) {:
    @GetMapping("/purchase/{purchaseId}")
    public ResponseEntity<ReplyDTO> findByPurchaseId(@PathVariable int purchaseId) {
        LOGGER.info("Starting process to find reply by purchase id");
        try {
            LOGGER.info("Reply found successfully");
            return new ResponseEntity<>(replyBL.findByPurchaseId(purchaseId), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.warning("Error occurred while finding reply by purchase id: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } finally {
            LOGGER.info("Finished process to find reply by purchase id");
        }
    }

    // utilizando public ReplyDTO create(ReplyDTO replyDTO) {:
    @PostMapping
    public ResponseEntity<ReplyDTO> create(@RequestBody ReplyDTO replyDTO) {
        LOGGER.info("Starting process to create reply");
        try {
            LOGGER.info("Reply created successfully");
            return new ResponseEntity<>(replyBL.create(replyDTO), HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.warning("Error occurred while creating reply: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } finally {
            LOGGER.info("Finished process to create reply");
        }
    }
}
