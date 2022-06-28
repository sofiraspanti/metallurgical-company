package projectcompany.metallurgy.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import projectcompany.metallurgy.entities.Customer;
import projectcompany.metallurgy.services.CustomerService;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    //-------------------------------------------------------------------------
    @GetMapping("/form")
    public String newCustomer(ModelMap model) {
        model.addAttribute("customer", new Customer());
        List<Customer> customers = customerService.listAllCustomers();
        model.addAttribute("customer", customers);
        return "customer/newCustomer";
    }

    @PostMapping("/form")
    public String viewNewCustomer(@RequestParam String name, @RequestParam MultipartFile photo, RedirectAttributes attr) {
        try {
            customerService.newCustomer(name, photo);
            attr.addFlashAttribute("success", "¡El cliente se ha creado exitosamente!.");
        } catch (Exception e) {
            attr.addFlashAttribute("error", "Ha ocurrido un error inesperado.");
            e.printStackTrace();
            return "customer/newCustomer";
        }
        return "redirect:/customer/form";
    }

    //-------------------------------------------------------------------------
    @GetMapping("modify/{id}")
    public String modifyCustomer(@PathVariable String idCustomer, ModelMap model) {
        model.put("customer", customerService.getOne(idCustomer));
        List<Customer> customers = customerService.listAllCustomers();
        model.addAttribute("customer", customers);
        return "customer/modifyCustomer";
    }

    @PostMapping("/modify")
    public String saveCustomer(@RequestParam String idCustomer, @RequestParam String newName, MultipartFile newPhoto, ModelMap model, RedirectAttributes attr) {
        try {
            customerService.modifyCustomer(idCustomer, newName, newPhoto);
            attr.addFlashAttribute("success", "¡Los cambios se guardaron exitosamente!.");
        } catch (Exception e) {
            attr.addFlashAttribute("error", "Hubo un error inesperado.");
            e.printStackTrace();
            model.put("customer", customerService.getOne(idCustomer));
            return "/customer/modifyCustomer";
        }
        return "redirect:/customers/list";
    }

    //-------------------------------------------------------------------------
    @GetMapping("/inactive/{id}")
    public String inctive(@PathVariable String idCustomer, ModelMap model) {
        try {
            customerService.inactiveCustomer(idCustomer);
        } catch (Exception e) {
            return "/customer/listCustomer";
        }
        return "redirect:/customers/list";
    }

    //-------------------------------------------------------------------------
    @GetMapping("/active/{id}")
    public String active(@PathVariable String idCustomer, ModelMap model) {
        try {
            customerService.activeCustomer(idCustomer);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "/customer/listCustomers";
        }
        return "redirect:/customers/list";
    }

    //-------------------------------------------------------------------------
    @GetMapping("/list")
    public String listCustomers(ModelMap model) {
        List<Customer> customers = customerService.listAllCustomers();
        model.addAttribute("customer", customers);
        return "/customer/listCustomers";
    }
    //-------------------------------------------------------------------------
    
}
