package DevSGMA_PTC.SGMA_PTC.Models.DTO.Users;

import DevSGMA_PTC.SGMA_PTC.Models.DTO.Roles.RoleDTO;

public class UserDTO {
    private Long id;
    private String username;
    private String lastName;
    private String email;
    private String teacherGrade;
    private RoleDTO role;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTeacherGrade() { return teacherGrade; }
    public void setTeacherGrade(String teacherGrade) { this.teacherGrade = teacherGrade; }

    public RoleDTO getRole() { return role; }
    public void setRole(RoleDTO role) { this.role = role; }
}
