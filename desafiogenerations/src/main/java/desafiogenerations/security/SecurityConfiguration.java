package desafiogenerations.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity // Permite personalizar as configurações de segurança
public class SecurityConfiguration {

    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        // Desabilita a proteção ao CSRF pois o Token já vai fazer essa proteção
        // Muda a configuração para stateless (padrão de apis rest)
        // Permite que a requisição de login não precise de token

        return httpSecurity
                .csrf(csrf -> csrf.disable()) // Desabilita CSRF
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Define política de criação de sessão
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers(HttpMethod.POST, "/funcionarios/criar").permitAll()
                        .requestMatchers(HttpMethod.POST, "/login").permitAll()
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**").permitAll()
                        .anyRequest().authenticated()) // Exige autenticação para qualquer outra requisição
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class) // Adiciona filtro customizado
                .build();

    }

//    A anotação @Bean no Spring serve para exportar uma classe para o Spring,
//    permitindo que ela seja carregada e que o Spring injete dependências dela em outras classes.

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }

    // Mostra para o Spring que esse é o algoritimo de hash para senha
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
