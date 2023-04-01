package uz.urinov.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.urinov.warehouse.config.BadRequestException;
import uz.urinov.warehouse.config.NotFoundException;
import uz.urinov.warehouse.entity.Attachment;
import uz.urinov.warehouse.entity.Category;
import uz.urinov.warehouse.entity.Measurement;
import uz.urinov.warehouse.entity.Product;
import uz.urinov.warehouse.payload.ProductDTO;
import uz.urinov.warehouse.repository.AttachmentRepository;
import uz.urinov.warehouse.repository.CategoryRepository;
import uz.urinov.warehouse.repository.MeasurementRepository;
import uz.urinov.warehouse.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.*;

@Service
public class ProductService {
    private final ProductRepository repository;
    private final CategoryRepository categoryRepository;
    private final AttachmentRepository attachmentRepository;
    private final MeasurementRepository measurementRepository;

    @Autowired
    public ProductService(ProductRepository repository, CategoryRepository categoryRepository, AttachmentRepository attachmentRepository, MeasurementRepository measurementRepository) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
        this.attachmentRepository = attachmentRepository;
        this.measurementRepository = measurementRepository;
    }

    public List<Product> products() {
        return repository.findAll();
    }

    public Product product(Integer id) {
        Optional<Product> optionalProduct = repository.findById(id);
        if (!optionalProduct.isPresent()) throw new NotFoundException("Product not found");
        return optionalProduct.get();
    }

    public Product addProduct(ProductDTO dto) {
        Optional<Category> oc = categoryRepository.findById(dto.getCategoryId());
        if (!oc.isPresent()) throw new NotFoundException("Category not found");
        Optional<Attachment> oa = attachmentRepository.findById(dto.getPhotoId());
        if (!oa.isPresent()) throw new NotFoundException("Photo not found");
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(dto.getMeasurementId());
        if (!optionalMeasurement.isPresent()) throw new NotFoundException("Measurement not found");
        Optional<Product> maxId = repository.getMaxId();
        String code = String.valueOf(maxId.map(product -> product.getId() + 1).orElse(1));
        Product product = new Product(code, oc.get(), optionalMeasurement.get(), oa.get());
        product.setName(dto.getName());
        return repository.save(product);
    }

    public Product editProduct(Integer id, ProductDTO dto) {
        Optional<Product> op = repository.findById(id);
        if (!op.isPresent()) throw new NotFoundException("Product not found");
        Optional<Category> oc = categoryRepository.findById(dto.getCategoryId());
        if (!oc.isPresent()) throw new NotFoundException("Category not found");
        Optional<Attachment> oa = attachmentRepository.findById(dto.getPhotoId());
        if (!oa.isPresent()) throw new NotFoundException("Photo not found");
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(dto.getMeasurementId());
        if (!optionalMeasurement.isPresent()) throw new NotFoundException("Measurement not found");
        Product product = new Product(op.get().getCode(), oc.get(), optionalMeasurement.get(), oa.get());
        product.setId(op.get().getId());
        product.setName(dto.getName());
        product.setActive(dto.isActive());
        return repository.save(product);
    }

    public ResponseEntity<?> deleteProduct(Integer id) {
        Optional<Product> optionalProduct = repository.findById(id);
        if (!optionalProduct.isPresent()) throw new NotFoundException("Product not found");
        try {
            repository.delete(optionalProduct.get());
            return status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            throw new BadRequestException("Product not deleted");
        }
    }
}
