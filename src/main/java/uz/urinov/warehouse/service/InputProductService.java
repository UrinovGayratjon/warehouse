package uz.urinov.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.urinov.warehouse.config.BadRequestException;
import uz.urinov.warehouse.config.NotFoundException;
import uz.urinov.warehouse.entity.Input;
import uz.urinov.warehouse.entity.InputProduct;
import uz.urinov.warehouse.entity.Product;
import uz.urinov.warehouse.payload.InputProductDTO;
import uz.urinov.warehouse.repository.InputProductRepository;
import uz.urinov.warehouse.repository.InputRepository;
import uz.urinov.warehouse.repository.ProductRepository;

import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.*;

@Service
public class InputProductService {
    private final InputProductRepository repository;
    private final ProductRepository productRepository;
    private final InputRepository inputRepository;

    @Autowired
    public InputProductService(InputProductRepository repository, ProductRepository productRepository, InputRepository inputRepository) {
        this.repository = repository;
        this.productRepository = productRepository;
        this.inputRepository = inputRepository;
    }

    public List<InputProduct> inputProducts() {
        return repository.findAll();
    }

    public InputProduct inputProduct(Integer id) {
        Optional<InputProduct> oip = repository.findById(id);
        if (!oip.isPresent()) throw new NotFoundException("Input product not found");
        return oip.get();
    }

    public InputProduct addInputProduct(InputProductDTO dto) {
        Optional<Product> op = productRepository.findById(dto.getProductId());
        if (!op.isPresent()) throw new NotFoundException("Product not found");
        Optional<Input> oi = inputRepository.findById(dto.getInputId());
        if (!oi.isPresent()) throw new NotFoundException("Input not found");
        Date date = oi.get().getDate();
        Period between = Period.between(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), dto.getExpireDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        if (between.isZero() || between.isNegative()) throw new BadRequestException("It has already expired");
        InputProduct ip = new InputProduct(op.get(), oi.get(), dto.getAmount(), dto.getPrice(), dto.getExpireDate());
        return repository.save(ip);
    }

    public InputProduct editInputProduct(Integer id, InputProductDTO dto) {
        Optional<InputProduct> optionalInputProduct = repository.findById(id);
        if (!optionalInputProduct.isPresent()) throw new NotFoundException("Input product not found");
        Optional<Product> op = productRepository.findById(dto.getProductId());
        if (!op.isPresent()) throw new NotFoundException("Product not found");
        Optional<Input> oi = inputRepository.findById(dto.getInputId());
        if (!oi.isPresent()) throw new NotFoundException("Input not found");
        Date date = oi.get().getDate();
        Period between = Period.between(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), dto.getExpireDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        if (between.isZero() || between.isNegative()) throw new BadRequestException("It has already expired");
        InputProduct ip = new InputProduct(id, op.get(), oi.get(), dto.getAmount(), dto.getPrice(), dto.getExpireDate());
        return repository.save(ip);
    }

    public ResponseEntity<?> deleteInputProduct(Integer id) {
        Optional<InputProduct> optionalInputProduct = repository.findById(id);
        if (!optionalInputProduct.isPresent()) throw new NotFoundException("Input product not found");
        try {
            repository.delete(optionalInputProduct.get());
            return status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            throw new BadRequestException("Input Product not deleted");
        }
    }
}
