package bo.com.knowix.api;

import bo.com.knowix.bl.CuponBL;
import bo.com.knowix.dto.CuponDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cupones")
public class CuponAPI {

    @Autowired
    private CuponBL cuponBL;

    // Endpoint para crear un cupón
    @PostMapping("/crear")
    public ResponseEntity<?> createCupon(@RequestBody CuponDTO cuponDTO) {
        try {
            CuponDTO createdCupon = cuponBL.createCupon(cuponDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdCupon);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear el cupón: " + e.getMessage());
        }
    }

    // Endpoint para listar todos los cupones
    @GetMapping("/listar")
    public ResponseEntity<?> listCupones() {
        try {
            List<CuponDTO> cupones = cuponBL.listCupones();
            if (cupones.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No se encontraron cupones.");
            }
            return ResponseEntity.ok(cupones);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al listar los cupones: " + e.getMessage());
        }
    }

    // Endpoint para buscar un cupón por ID
    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> getCuponById(@PathVariable int id) {
        try {
            CuponDTO cuponDTO = cuponBL.getCuponById(id);
            if (cuponDTO == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Cupón no encontrado para el ID: " + id);
            }
            return ResponseEntity.ok(cuponDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al buscar el cupón por ID: " + e.getMessage());
        }
    }

    @PostMapping("/buscar-por-codigo")
    public ResponseEntity<?> getCuponByCode(@RequestBody String cuponCode) {
        try {
            CuponDTO cuponDTO = cuponBL.getCuponByCode(cuponCode);
            if (cuponDTO == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Cupón no encontrado para el código: " + cuponCode);
            }
            return ResponseEntity.ok(cuponDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al buscar el cupón por código: " + e.getMessage());
        }
    }
}
