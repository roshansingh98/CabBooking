package com.cg.mts.controller;

import com.cg.mts.entities.Cab;
import com.cg.mts.service.ICabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/* This would signify that we are in cab controller right now */
@RequestMapping("/cab")
/*
 * This annotation defines a class as a controller in spring boot. Rest
 * controller consists of @Controller and @ResponseBody annotation
 */
@RestController
public class CabController {

    /*
     * ICabService is an interface whose reference we are trying to call over here.
     * The object for the reference will be automatically created at run time
     * using @Autowired annotation.
     */
    @Autowired
    private ICabService cabService;

    /*
     * This method will add a cab object to the cab table. and return the cab object
     * added back to the server
     */
    @PostMapping("/add")
    public Cab add(@RequestBody Cab cab) {
        Cab newCab = new Cab(cab.getCarType(), cab.getPerKmRate());
        cab = cabService.insertCab(newCab);
        return cab;
    }

    /*
     * This method will update a cab object whose id matches with the one we are
     * passing. Once it updates, it will return the updated object back to the
     * server
     */
    @PutMapping("/update/{id}")
    public Cab update(@PathVariable("id") int cabId, @RequestBody Cab newCab) {
        newCab.setCabId(cabId);
        newCab = cabService.updateCab(newCab);
        return newCab;
    }

    /*
     * This method will delete a cab object from the database table, which we have
     * passed in the method
     */
    @DeleteMapping("/delete")
    public void delete(@RequestBody Cab cab) {
        cab = cabService.deleteCab(cab);
    }

    /*
     * This method will return List of cabs where the carType field matches with the
     * one we have passed in the url
     */
    @GetMapping("/retrieve/cabsOfType/{cabType}")
    public List<Cab> getCabsOfType(@PathVariable("cabType") String cabType) {
        List<Cab> cabs = cabService.viewCabsOfType(cabType);
        return cabs;
    }

    /*
     * This method will return the count of cabs where the carType field matches
     * with the one we have passed in the url
     */
    @GetMapping("/retrieve/countOfCabsType/{cabType}")
    public int getCountCabsOfType(@PathVariable("cabType") String cabType) {
        int count = cabService.countCabsOfType(cabType);
        return count;
    }
}
