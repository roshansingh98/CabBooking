package com.cg.mts.service;

import com.cg.mts.entities.Cab;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CabServiceTest {

    @Test
    public void testForCrudOp() {

        Cab cab = new Cab("hatch", 15);

        ICabService cabService = new CabService();


    }
}