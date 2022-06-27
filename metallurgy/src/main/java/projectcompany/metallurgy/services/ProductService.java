package projectcompany.metallurgy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projectcompany.metallurgy.repositories.CustomerRepository;
import projectcompany.metallurgy.repositories.PhotoRepository;
import projectcompany.metallurgy.repositories.ProductRepository;

@Service
public class ProductService {
    
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    private final PhotoRepository photoRepository;
    

    @Autowired
    public ProductService(ProductRepository productRepository, CustomerRepository customerRepository, PhotoRepository photoRepository) {
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
        this.photoRepository = photoRepository;
    }
    
   
    
    
    
}
