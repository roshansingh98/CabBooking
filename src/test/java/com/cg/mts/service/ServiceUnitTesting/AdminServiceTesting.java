package com.cg.mts.service.ServiceUnitTesting;

import com.cg.mts.entities.Admin;
import com.cg.mts.entities.TripBooking;
import com.cg.mts.exception.AdminNotFoundException;
import com.cg.mts.exception.CabNotFoundException;
import com.cg.mts.exception.CustomerNotFoundException;
import com.cg.mts.repository.IAdminRepository;
import com.cg.mts.service.AdminService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class AdminServiceTesting {

    @InjectMocks
    private AdminService adminService;

    @Mock
    private IAdminRepository adminRepository;

    /*
    * Test to check successful insertion of the Admin
    */
    @Test
    void testInsertAdmin(){
        Admin admin = Mockito.mock(Admin.class);
        Admin updatedAdmin = Mockito.mock(Admin.class);

        Mockito.when(adminRepository.save(admin)).thenReturn(updatedAdmin);
        Admin result = adminService.insertAdmin(admin);
        assertEquals(updatedAdmin, result);
    }


    /*
     * Test to check successful updating of the Admin
     */
    @Test
    void testUpdateAdmin(){
        Admin admin = Mockito.mock(Admin.class);
        Admin updatedAdmin = Mockito.mock(Admin.class);

        int adminId = 5;

        Mockito.when(admin.getAdminId()).thenReturn(adminId);
        Mockito.when(adminRepository.existsById(5)).thenReturn(true);
        Mockito.when(adminRepository.save(admin)).thenReturn(updatedAdmin);

        Admin result = adminService.updateAdmin(admin);
        assertEquals(updatedAdmin, result);
    }


    /*
     * Test to check successful throw of exception when updating an admin which doesn't exist
     */
    @Test
    void testUpdateAdminThrowException(){
        Admin admin = Mockito.mock(Admin.class);
        int adminId = 5;

        Mockito.when(admin.getAdminId()).thenReturn(adminId);
        Mockito.when(adminRepository.existsById(5)).thenReturn(false);

        Executable exe = () -> adminService.updateAdmin(admin);
        assertThrows(AdminNotFoundException.class, exe);
    }


    /*
     * Test to check if list of trips is returned successfully
     */
    @Test
    void testGetAllTrips(){
        List<TripBooking> trips = new ArrayList<>();
        TripBooking tripBooking = Mockito.mock(TripBooking.class);
        trips.add(tripBooking);
        Mockito.when(adminRepository.getAllTrips(5)).thenReturn(trips);

        assertEquals(trips , adminService.getAllTrips(5));
    }

    /*
     * Test to check if it throws exception when there is nothing on the list
     */
    @Test
    void testGetAllTripsReturnException(){
        List<TripBooking> trips = new ArrayList<>();

        Mockito.when(adminRepository.getAllTrips(5)).thenReturn(trips);

        Executable exe = () -> adminService.getAllTrips(5);
        assertThrows(CustomerNotFoundException.class , exe);
    }

    @Test
    void testTripsCabwise(){
        List<TripBooking> trips = new ArrayList<>();
        TripBooking tripBooking = Mockito.mock(TripBooking.class);
        trips.add(tripBooking);

        Mockito.when(adminRepository.getTripsCabwise()).thenReturn(trips);

        assertEquals(trips , adminService.getTripsCabwise());
    }

    @Test
    void testGetTripsCabwiseReturnException(){
        List<TripBooking> trips = new ArrayList<>();

        Mockito.when(adminRepository.getTripsCabwise()).thenReturn(trips);

        Executable exe = () -> adminService.getTripsCabwise();
        assertThrows(CabNotFoundException.class , exe);
    }


}
