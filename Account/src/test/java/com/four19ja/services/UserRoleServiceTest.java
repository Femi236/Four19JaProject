package com.four19ja.services;

import com.four19ja.entities.UserRole;
import com.four19ja.entities.UserRoleID;
import com.four19ja.repositories.UserRoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;


@ExtendWith(MockitoExtension.class)
public class UserRoleServiceTest {
    private transient UserRole userRole1;
    private transient UserRole userRole2;
    private transient UserRoleID userRoleID1;

    @Mock
    private transient UserRoleRepository userRoleRepository;

    private transient UserRoleService userRoleService;

    @BeforeEach
    public void init() {
        userRole1 = new UserRole(1,1);
        userRole2 = new UserRole(2,2);
        userRoleID1 = new UserRoleID(userRole1.getUserID(), userRole1.getRoleID());
        userRoleService = new UserRoleService(userRoleRepository);
    }

    @Test
    public void saveValidUserRole() {
        when(userRoleRepository.save(any(UserRole.class))).then(returnsFirstArg());
        String response = userRoleService.addNewUserRole(userRole1.getUserID(), userRole1.getRoleID());
        assertEquals("Saved", response);

    }

    @Test
    public void deleteValidUserRole() {
        when(userRoleRepository.findById(any(UserRoleID.class))).thenReturn(Optional.ofNullable(userRole1));
        doNothing().when(userRoleRepository).deleteById(any(UserRoleID.class));
        String response = userRoleService.deleteUserRole(userRoleID1);
        assertEquals("Deleted", response);
    }

    @Test
    public void deleteNonExistingUserRole() {
        when(userRoleRepository.findById(any(UserRoleID.class))).thenReturn(Optional.ofNullable(null));
        String response = userRoleService.deleteUserRole(userRoleID1);
        assertEquals("Does not exist", response);
    }

}
