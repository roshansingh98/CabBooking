package com.cg.mts.service.ServiceUnitTesting;

import com.cg.mts.entities.Cab;
import com.cg.mts.exception.InvalidCabException;
import com.cg.mts.repository.ICabRepository;
import com.cg.mts.service.CabService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class CabServiceTesting {
    @InjectMocks
    private CabService service;

    @Mock
    private ICabRepository repo;

    @BeforeEach
    void setUp() throws Exception {
    }

    @Test
    void testInsertCab() throws InvalidCabException {

        Cab cab = Mockito.mock(Cab.class);
        Cab updated = Mockito.mock(Cab.class);
        Mockito.when(repo.save(cab)).thenReturn(updated);
        Cab result = service.insertCab(cab);
        assertEquals(updated, result);

    }

    @Test
    void testUpdateCab_1() {
        Cab cab = Mockito.mock(Cab.class);
        Cab updated = Mockito.mock(Cab.class);
        Mockito.when(cab.getCabId()).thenReturn(50);
        Mockito.when(repo.existsById(50)).thenReturn(true);

        Mockito.when(repo.save(cab)).thenReturn(updated);
        Cab result = service.updateCab(cab);
        assertEquals(updated, result);

    }

    @Test
    void testUpdateCab_2() {
        Cab cab = Mockito.mock(Cab.class);
        int cabId = 20;
        Mockito.when(cab.getCabId()).thenReturn(cabId);
        Mockito.when(repo.existsById(cabId)).thenReturn(false);
        Executable exe = () -> service.updateCab(cab);
        assertThrows(InvalidCabException.class, exe);
    }

    @Test
    public void testDeleteCab() {
        Cab cab = new Cab("Abc", 4.5f);
        Mockito.when(repo.findById(cab.getCabId())).thenReturn(Optional.of(cab)).thenReturn(null);
        assertEquals(service.deleteCab(cab), cab);
    }

    @Test
    public void viewCabssType() {
        Cab cab = new Cab("Abc", 4.5f);
        List<Cab> cabs = new ArrayList<Cab>();
        cabs.add(cab);
        Mockito.when(repo.findByCarType("Abc")).thenReturn(cabs);
        assertEquals(service.viewCabsOfType("Abc"), cabs);
    }

}
