package uz.urinov.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.urinov.warehouse.config.NotFoundException;
import uz.urinov.warehouse.entity.Currency;
import uz.urinov.warehouse.entity.Input;
import uz.urinov.warehouse.entity.Supplier;
import uz.urinov.warehouse.entity.Warehouse;
import uz.urinov.warehouse.payload.InputDTO;
import uz.urinov.warehouse.repository.CurrencyRepository;
import uz.urinov.warehouse.repository.InputRepository;
import uz.urinov.warehouse.repository.SupplierRepository;
import uz.urinov.warehouse.repository.WarehouseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class InputService {

    private final InputRepository repository;
    private final WarehouseRepository warehouseRepository;
    private final SupplierRepository supplierRepository;
    private final CurrencyRepository currencyRepository;

    @Autowired
    public InputService(InputRepository repository, WarehouseRepository warehouseRepository, SupplierRepository supplierRepository, CurrencyRepository currencyRepository) {
        this.repository = repository;
        this.warehouseRepository = warehouseRepository;
        this.supplierRepository = supplierRepository;
        this.currencyRepository = currencyRepository;
    }

    public List<Input> inputs() {
        return repository.findAll();
    }

    public Input input(Integer id) {
        Optional<Input> optionalInput = repository.findById(id);
        if (!optionalInput.isPresent()) throw new NotFoundException("Input not found");
        return optionalInput.get();
    }

    public Input addInput(InputDTO dto) {
        Optional<Warehouse> ow = warehouseRepository.findById(dto.getWarehouseId());
        if (!ow.isPresent()) throw new NotFoundException("Warehouse id not found");
        Optional<Supplier> os = supplierRepository.findById(dto.getSupplierId());
        if (!os.isPresent()) throw new NotFoundException("Supplier id not found");
        Optional<Currency> oc = currencyRepository.findById(dto.getCurrencyId());
        if (!oc.isPresent()) throw new NotFoundException("Warehouse id not found");
        Optional<Input> maxId = repository.getMaxId();
        String code = String.valueOf(maxId.map(input -> input.getId() + 1).orElse(1));
        return repository.save(new Input(dto.getDate(), ow.get(), os.get(), oc.get(), dto.getFactureNumber(), code));
    }

    public Input editInput(Integer id, InputDTO dto) {
        Optional<Input> optionalInput = repository.findById(id);
        if (!optionalInput.isPresent()) throw new NotFoundException("Input id not found");
        Optional<Warehouse> ow = warehouseRepository.findById(dto.getWarehouseId());
        if (!ow.isPresent()) throw new NotFoundException("Warehouse id not found");
        Optional<Supplier> os = supplierRepository.findById(dto.getSupplierId());
        if (!os.isPresent()) throw new NotFoundException("Supplier id not found");
        Optional<Currency> oc = currencyRepository.findById(dto.getCurrencyId());
        if (!oc.isPresent()) throw new NotFoundException("Warehouse id not found");
        Input input = optionalInput.get();
        input.setFactureNumber(dto.getFactureNumber());
        input.setCurrency(oc.get());
        input.setSupplier(os.get());
        input.setWarehouse(ow.get());
        return repository.save(input);
    }

    public ResponseEntity<?> deleteInput(Integer id) {
        Optional<Input> optionalInput = repository.findById(id);
        if (!optionalInput.isPresent()) throw new NotFoundException("Input not found");
        try {
            repository.delete(optionalInput.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
