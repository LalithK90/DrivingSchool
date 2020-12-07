package lk.samarasingherSuper;

import lk.samarasingherSuper.asset.commonAsset.model.Enum.CivilStatus;
import lk.samarasingherSuper.asset.commonAsset.model.Enum.Gender;
import lk.samarasingherSuper.asset.commonAsset.model.Enum.Title;
import lk.samarasingherSuper.asset.employee.entity.Employee;
import lk.samarasingherSuper.asset.employee.entity.enums.Designation;
import lk.samarasingherSuper.asset.employee.entity.enums.EmployeeStatus;
import lk.samarasingherSuper.asset.employee.service.EmployeeService;
import lk.samarasingherSuper.asset.userManagement.entity.Role;
import lk.samarasingherSuper.asset.userManagement.entity.User;
import lk.samarasingherSuper.asset.userManagement.service.RoleService;
import lk.samarasingherSuper.asset.userManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.stream.Collectors;

@RestController
public class ApplicationCreateRestController {
    private final RoleService roleService;
    private final UserService userService;
    private final EmployeeService employeeService;


    @Autowired
    public ApplicationCreateRestController(RoleService roleService, UserService userService,
                                           EmployeeService employeeService) {
        this.roleService = roleService;
        this.userService = userService;
        this.employeeService = employeeService;
    }

    @GetMapping("/select/user")
    public String saveUser() {
        //roles list start
        String[] roles = {"ADMIN"};
        for (String s : roles) {
            Role role = new Role();
            role.setRoleName(s);
            roleService.persist(role);
        }

//Employee
        Employee employee = new Employee();
        employee.setCode("11111111");
        employee.setName("Admin User");
        employee.setCallingName("Admin");
        employee.setName("908670000V");
        employee.setMobileOne("0750000000");
        employee.setTitle(Title.MR);
        employee.setGender(Gender.MALE);
        employee.setDesignation(Designation.LA);
        employee.setCivilStatus(CivilStatus.UNMARRIED);
        employee.setEmployeeStatus(EmployeeStatus.WORKING);
        employee.setDateOfBirth(LocalDate.now().minusYears(18));
        employee.setDateOfAssignment(LocalDate.now());
        Employee employeeDb = employeeService.persist(employee);


        //admin user one
        User user = new User();
        user.setEmployee(employeeDb);
        user.setUsername("admin");
        user.setPassword("admin");
        String message = "Username:- " + user.getUsername() + "\n Password:- " + user.getPassword();
        user.setEnabled(true);
        user.setRoles(roleService.findAll()
                .stream()
                .filter(role -> role.getRoleName().equals("ADMIN"))
                .collect(Collectors.toList()));
        userService.persist(user);

        return message;
    }


}
