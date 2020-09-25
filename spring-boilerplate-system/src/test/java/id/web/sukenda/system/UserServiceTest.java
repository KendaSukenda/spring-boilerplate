package id.web.sukenda.system;


import id.web.sukenda.common.enums.Role;
import id.web.sukenda.dto.UserDto;
import id.web.sukenda.entity.User;
import id.web.sukenda.system.handler.UserHandler;
import id.web.sukenda.system.router.UserRouter;
import id.web.sukenda.system.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {UserRouter.class, UserHandler.class})
@WebFluxTest
public class UserServiceTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private UserService userService;

    @Test
    public void doLogin() {
        User param = new User();
        param.setUsername("admin");
        param.setPassword("admin");

        webTestClient.post()
                .uri("/login")
                .body(Mono.just(param), User.class)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(User.class)
                .value(value -> value.getAccessToken());
    }

    @Test
    public void doRegister() {
        UserDto user = new UserDto();
        user.setUsername("nekonime");
        user.setPassword("nekonime");
        user.setRoles(Arrays.asList(Role.ROLE_ADMIN, Role.ROLE_TEACHER));

        webTestClient.post()
                .uri("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(user), UserDto.class)
                .exchange()
                .expectStatus().isCreated();

    }

}
