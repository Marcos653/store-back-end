package com.store.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.model.Customer;
import com.store.model.Product;
import com.store.model.Sale;
import com.store.model.Seller;
import com.store.repository.CustomerRepository;
import com.store.repository.ProductRepository;
import com.store.repository.SaleRepository;
import com.store.repository.SellerRepository;
import com.store.shared.SaleDTO;

@Service
public class SaleServiceImpl implements SaleService {
    
    @Autowired
    private SaleRepository repo;

    @Autowired
    private CustomerRepository Crepo;

    @Autowired
    private SellerRepository Srepo;

    @Autowired
    private ProductRepository Prepo;

    @Override
    public List<SaleDTO> getAllSale() {
        List<Sale> sales = repo.findAll();
        return sales.stream()
            .map(s -> new ModelMapper().map(s, SaleDTO.class))
            .collect(Collectors.toList());
    }

    @Override
    public SaleDTO register(SaleDTO sale) {
        return salveSale(sale);
    }

    @Override
    public Optional<SaleDTO> getById(Long id) {
        Optional<Sale> sale = repo.findById(id);

        if(sale.isPresent()){
            return Optional.of(new ModelMapper()
                .map(sale.get(), SaleDTO.class));
        }

        return Optional.empty();

    }

    @Override
    public void deleteSale(Long id) {
        repo.deleteById(id);
        
    }

    @Override
    public SaleDTO updateSale(Long id, SaleDTO newSale) {
        newSale.setId(id);
        return salveSale(newSale);
    }

    private SaleDTO salveSale(SaleDTO sale) {
        Optional<Customer> customer = Crepo.findById(sale.getCustomer().getId());
        Optional<Seller> seller = Srepo.findById(sale.getSeller().getId());
        Optional<Product> product = Prepo.findById(sale.getProduct().getId());
        sale.setCustomer(customer.get());
        sale.setSeller(seller.get());
        sale.setProduct(product.get());
        ModelMapper mapper = new ModelMapper();
        Sale sale_entity = mapper.map(sale, Sale.class);
        sale_entity = repo.save(sale_entity);
        return mapper.map(sale_entity, SaleDTO.class);
    }

    @Override
    public SaleDTO partialUpdateSale(Long id, Integer sale_amount, Customer customer, Seller seller) {
        ModelMapper mapper = new ModelMapper();
        Optional<Sale> sale = repo.findById(id);

        if(sale.isPresent()){

            if(sale_amount != null){
                sale.get().getSale_amount();
                repo.save(sale.get());
            }

            if(customer != null){
                sale.get().setCustomer(customer);
                repo.save(sale.get());
            }

            if(seller != null){
                sale.get().setSeller(seller);
                repo.save(sale.get());
            }

            return mapper.map(sale.get(), SaleDTO.class);

        }

        return null;
        
    }
    
}
