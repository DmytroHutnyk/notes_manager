package hutnyk.notes_app.Service.Mapper;

import hutnyk.notes_app.Model.DTO.RoleDTO;
import hutnyk.notes_app.Model.Entity.Role;
import hutnyk.notes_app.Model.Entity.User;
import hutnyk.notes_app.Repository.IUserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
//
//@Data
//@Service
//public class RoleMapper {
//
//    private final IUserRepository userRepository;
//
//    public RoleDTO roleToDTO(Role role) {
//        return RoleDTO.builder()
//                .id(role.getId())
//                .name(role.getName())
//                .userSetId(role.getUserSet() != null ?
//                        role.getUserSet().stream().map(User::getId).collect(Collectors.toSet()) :
//                        null)
//                .build();
//    }
//
//    public Role DTOtoRole(RoleDTO roleDTO) {
//        return new Role(
//                roleDTO.getName(),
//                roleDTO.getUserSetId() != null ?
//                        roleDTO.getUserSetId().stream().map(userId -> userRepository.findById(userId)
//                                        .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId)))
//                                .collect(Collectors.toSet()) :
//                        null
//        );
//    }
//}