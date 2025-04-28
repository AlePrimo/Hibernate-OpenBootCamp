package com.example.Proyecto_Final_Hibernate.controller;


import com.example.Proyecto_Final_Hibernate.dao.BillingInfoDAO;
import com.example.Proyecto_Final_Hibernate.entities.BillingInfo;
import com.example.Proyecto_Final_Hibernate.repository.BillingInfoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BillingInfoController {

    private BillingInfoRepository billingInfoRepository;
    private BillingInfoDAO billingInfoDAO;

    public BillingInfoController(BillingInfoRepository billingInfoRepository, BillingInfoDAO billingInfoDAO) {
        this.billingInfoRepository = billingInfoRepository;
        this.billingInfoDAO = billingInfoDAO;
    }
    @GetMapping("/api/billingInfo")
   private List<BillingInfo> findAll(){
        return this.billingInfoRepository.findAll();
   }

    @GetMapping("/api/billingInfo/id/{id}")
    private BillingInfo  findById(@PathVariable Long id){
        return this.billingInfoRepository.findById(id).get();
    }


    @GetMapping("/api/billingInfo/{countrySearched}")
    private List<BillingInfo> findByCountry(@PathVariable String countrySearched){

        return this.billingInfoDAO.findByCountry(countrySearched);
}

@PostMapping("/api/billingInfo")
    private BillingInfo create(@RequestBody BillingInfo billingInfo){
        return this.billingInfoRepository.save(billingInfo);
}


@PutMapping("/api/billingInfo")
    private BillingInfo update(@RequestBody BillingInfo billingInfo){
         return  this.billingInfoRepository.save(billingInfo);
}

@DeleteMapping("/api/billingInfo/{id}")
    private String delete(@PathVariable Long id){
    if(billingInfoRepository.existsById(id)){
        this.billingInfoRepository.deleteById(id);
        return "Borrado Exitoso";
    }
    return "No existe ese elemento ";
}


}
