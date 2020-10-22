package com.cg.mts.service;

import com.cg.mts.entities.Cab;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CabServiceTest {

    //Along with the normal CRUD operations it also tests for getCabsOfType
    @Test
    public void testForCrudOp() {

        Cab cab = new Cab("hatch", 15);
        Cab cab1 = new Cab("sedan", 20);
        Cab cab2 = new Cab("SUV", 25);

        ICabService cabService = new CabService();

        assertEquals(cab, cabService.insertCab(cab));
        cabService.insertCab(cab1);
        cabService.insertCab(cab2);

        Cab cab3 = new Cab("MonsterTruck", 100);
        cab3.setCabId(cab1.getCabId());

        assertEquals("MonsterTruck", cabService.updateCab(cab3).getCarType());

        cab3 = cabService.deleteCab(cab2);

        assertEquals(0, cabService.countCabsOfType(cab3.getCarType())); //tests for getCabsOfType also

    }

    @Test
    public void testForViewCabsOfType() {

        Cab cab = new Cab("hatch", 15);
        Cab cab1 = new Cab("sedan", 20);
        Cab cab2 = new Cab("SUV", 25);
        Cab cab3 = new Cab("sedan", 25);
        Cab cab4 = new Cab("sedan", 25);

        ICabService cabService = new CabService();

        cabService.insertCab(cab);
        cabService.insertCab(cab1);
        cabService.insertCab(cab2);
        cabService.insertCab(cab3);
        cabService.insertCab(cab4);

        System.out.println(cabService.viewCabsOfType("sedan"));

        List<Integer> li = new ArrayList<Integer>();

        for (Cab cabReturn : cabService.viewCabsOfType("sedan")) {
            li.add(((Integer) cabReturn.getCabId()));
        }

        //li contains(2,4,5) as it can be understood from the cabInsertion
        assertEquals(4, li.get(1));

    }
}