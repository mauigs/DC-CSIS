package com.example.csis3275300369499maureen.web;

import com.example.csis3275300369499maureen.entities.Salesman;
import com.example.csis3275300369499maureen.respositories.SalesmanRepository;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class SalesmanController {
    @Autowired
    private SalesmanRepository salesmanRepository;
    @GetMapping(path = "/index")
    public String salesman(Model model) {
        List<Salesman> salesman = salesmanRepository.findAll();
        model.addAttribute("listSalesmen", salesman);
        return "salesman";
    }

    @GetMapping("/delete")
    public String delete(Long id) {
        salesmanRepository.deleteById(id);
        return "redirect:/index";
    }

    @GetMapping("/editsalesman")
    public String editSalesman(Model model, Long id, HttpSession session){
        int num = 2;
        session.setAttribute("info", 0);
        Salesman salesman = salesmanRepository.findById(id).orElse(null);
        if(salesman==null) throw new RuntimeException("Salesman does not exist");
        model.addAttribute("salesman", salesman);
        return "editsalesman";
    }

    @PostMapping("/save")
    public String saveData(@RequestParam String salesmanName,
                           @RequestParam String itemType,
                           @RequestParam Double salesAmount,
                           @RequestParam String transactionDate,
                           Model model) {
        // Perform validation
        if (StringUtils.isBlank(salesmanName))
            model.addAttribute("errorMessage", "Salesman name cannot be blank");
            return "salesman"; // Return the page with the form
        }



}
