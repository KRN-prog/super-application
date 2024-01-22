package com.application.superapplication.service;

import com.application.superapplication.service.dto.UserRequestDto;
import com.application.superapplication.service.dto.UserResponseDto;
import com.application.superapplication.service.mapper.UserMapper;
import com.application.superapplication.repository.model.UserEntity;
import com.application.superapplication.repository.UserRepository;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.List;
import java.util.Optional;

import static com.application.superapplication.sample.UserRequestSample.oneUserRequestSample;
import static com.application.superapplication.sample.UserResponseSample.oneOtherUserResponseSample;
import static com.application.superapplication.sample.UserResponseSample.oneUserResponseSample;
import static com.application.superapplication.sample.UserSample.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsersServiceTest {
    @InjectMocks
    private UsersService usersService;

    @Mock
    private UserMapper userMapper;

    @Mock
    private UserRepository userRepository;

    @Nested
    class CreateUserEntity {
        @Test
        void shouldCreateUser() {
            UserRequestDto userRequestDto = oneUserRequestSample();

            UserEntity user = oneUserSample();
            user.setId(null);

            UserEntity userSaved = oneUserSample();

            UserResponseDto userResponseDto = oneUserResponseSample();

            when(userMapper.toEntity(userRequestDto)).thenReturn(user);
            when(userRepository.save(user)).thenReturn(userSaved);
            when(userMapper.toDto(userSaved)).thenReturn(userResponseDto);

            UserResponseDto userResponseDtoActual = usersService.createUser(userRequestDto);

            assertThat(userResponseDtoActual.getId()).isEqualTo(1);
            assertThat(userResponseDtoActual.getName()).isEqualTo("jean");
            assertThat(userResponseDtoActual.getAge()).isEqualTo(5);
            assertThat(userResponseDtoActual.getHeight()).isEqualTo(200);
        }
    }

    @Nested
    class GetUserEntity {
        @Test
        void shouldGetUserDtoById() {
            int userId = 1;

            UserEntity user = oneUserSample();

            UserResponseDto userResponseDto = oneUserResponseSample();

            when(userRepository.findById(userId)).thenReturn(Optional.of(user));
            when(userMapper.toDto(user)).thenReturn(userResponseDto);

            UserResponseDto userResponseDtoActual = usersService.getUserDtoById(1);
            assertThat(userResponseDtoActual.getId()).isEqualTo(1);
            assertThat(userResponseDtoActual.getName()).isEqualTo("jean");
            assertThat(userResponseDtoActual.getAge()).isEqualTo(5);
            assertThat(userResponseDtoActual.getHeight()).isEqualTo(200);
        }

        @Test
        void shouldNotFindUser() {
            int userId = 1;

            when(userRepository.findById(userId)).thenThrow(new RuntimeException("Utilisateur non trouvé."));

            assertThatExceptionOfType(RuntimeException.class)
                    .isThrownBy(() -> usersService.getUserDtoById((userId)))
                    .withMessage("Utilisateur non trouvé.");
        }
    }

    @Nested
    class GetAllUserEntity {
        @Test
        void  shouldGetAllUser() {
            List<UserEntity> userList = List.of(oneUserSample(), oneOtherUserSample());
            List<UserResponseDto> userResponseDtoList = List.of(oneUserResponseSample(), oneOtherUserResponseSample());

            when(userRepository.findAll()).thenReturn(userList);
            when(userMapper.toDto(userList.get(0))).thenReturn(userResponseDtoList.get(0));
            when(userMapper.toDto(userList.get(1))).thenReturn(userResponseDtoList.get(1));

            List<UserResponseDto> userResponseDtoActual = usersService.getAllUser();
            assertThat(userResponseDtoActual.size()).isEqualTo(2);

            assertThat(userResponseDtoActual.get(0).getId()).isEqualTo(1);
            assertThat(userResponseDtoActual.get(0).getName()).isEqualTo("jean");
            assertThat(userResponseDtoActual.get(0).getAge()).isEqualTo(5);
            assertThat(userResponseDtoActual.get(0).getHeight()).isEqualTo(200);

            assertThat(userResponseDtoActual.get(1).getId()).isEqualTo(2);
            assertThat(userResponseDtoActual.get(1).getName()).isEqualTo("jeanne");
            assertThat(userResponseDtoActual.get(1).getAge()).isEqualTo(15);
            assertThat(userResponseDtoActual.get(1).getHeight()).isEqualTo(175);
        }

        @Test
        void shouldNotGetUser() {
            when(userRepository.findAll()).thenThrow(new RuntimeException("Utilisateur non trouvé."));

            assertThatExceptionOfType(RuntimeException.class)
                    .isThrownBy(() -> usersService.getAllUser())
                    .withMessage("Utilisateur non trouvé.");
        }
    }

    @Nested
    class ModifyUserEntity {
        @Test
        void shouldModifyUser() {
            int userId = 1;
            UserEntity user = oneUserSample();
            UserEntity modifiedUser = oneModifiedUserSample();
            UserRequestDto userRequestDto = oneUserRequestSample();


            when(userRepository.findById(userId)).thenReturn(Optional.of(user));
            when(userMapper.updateEntity(userRequestDto, user)).thenReturn(modifiedUser);
            when(userRepository.save(user)).thenReturn(modifiedUser);

            UserEntity modifyingUser = usersService.modifyUser(userId, userRequestDto);
            assertThat(modifyingUser).isNotNull();
            assertThat(modifyingUser.getId()).isEqualTo(1);
            assertThat(modifyingUser.getName()).isEqualTo("julien");
            assertThat(modifyingUser.getAge()).isEqualTo(26);
            assertThat(modifyingUser.getHeight()).isEqualTo(165);

        }

        @Test
        void shouldNotModifyUser() {
            int userId = 1;

            when(userRepository.findById(userId)).thenThrow(new RuntimeException("Utilisateur non trouvé."));

            assertThatExceptionOfType(RuntimeException.class)
                    .isThrownBy(() -> usersService.getUserDtoById((userId)))
                    .withMessage("Utilisateur non trouvé.");
        }
    }

    @Nested
    class DeleteUserEntity {
        @Test
        void shouldDeleteUser() {
            int userId = 1;
            UserEntity user = oneUserSample();

            when(userRepository.findById(userId)).thenReturn(Optional.of(user));

            usersService.deleteUser(userId);
            verify(userRepository).findById(userId);
        }

        @Test
        void shouldNotFindUser() {
            int userId = 1;

            when(userRepository.findById(userId)).thenThrow(new RuntimeException("Utilisateur non trouvé."));

            assertThatExceptionOfType(RuntimeException.class)
                    .isThrownBy(() -> usersService.getUserDtoById((userId)))
                    .withMessage("Utilisateur non trouvé.");
        }
    }
}