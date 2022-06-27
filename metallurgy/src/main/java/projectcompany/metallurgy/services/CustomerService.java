package projectcompany.metallurgy.services;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import projectcompany.metallurgy.entities.Customer;
import projectcompany.metallurgy.entities.Photo;
import projectcompany.metallurgy.error.ErrorService;
import projectcompany.metallurgy.repositories.CustomerRepository;
import projectcompany.metallurgy.repositories.PhotoRepository;

@Service
public class CustomerService {

    @Autowired
    private PhotoService photoService;

    private final CustomerRepository customerRepository;
    private final PhotoRepository photoRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, PhotoRepository photoRepository) {
        this.customerRepository = customerRepository;
        this.photoRepository = photoRepository;
    }

    @Transactional
    public void newCustomer(String name, MultipartFile image) throws Exception {
        Customer customer = new Customer();
        customer.setName(name);
        if (customer.getStatus() == null) {
            customer.setStatus(true);
        }
        Photo photo = photoService.save(image);
        customer.setPhoto(photo);
        validate(customer);
        customerRepository.save(customer);
    }

    @Transactional
    public void modifyCustomer(String id, String name, MultipartFile image) throws Exception {
        Optional<Customer> answer = customerRepository.findById(id);
        if (answer.isPresent()) {
            Customer customer = answer.get();
            if (name != null || name.isEmpty()) {
                customer.setName(name);
            }
            Photo photo = photoService.save(image);
            if (image != null || image.isEmpty()) {
                customer.setPhoto(photo);
            }
            validate(customer);
            customerRepository.save(customer);
        } else {
            throw new ErrorService("No se encontró el cliente.");
        }
    }

    @Transactional
    public void inactiveCustomer(String idCustomer) {
        Optional<Customer> answer = customerRepository.findById(idCustomer);
        if (answer.isPresent()) {
            Customer customer = answer.get();
            customer.setStatus(false);
            customerRepository.save(customer);
        }
    }
    
    @Transactional
    public void activeCustomer(String idCustomer){
        Optional<Customer> answer = customerRepository.findById(idCustomer);
        if (answer.isPresent()) {
            Customer customer = answer.get();
            customer.setStatus(true);
            customerRepository.save(customer);
        }
    }

    @Transactional
    public List<Customer> listAllCustomers(){
        return customerRepository.findAll();
    }
    
    @Transactional
    public List<Customer> listActiveCustomers(){
        return customerRepository.findByActive();
    }
    
    public void validate(Customer customer) throws ErrorService {
        if (customer.getName().trim().isEmpty()) {
            throw new ErrorService("El nombre no puede estar vacío.");
        }

    }

}
