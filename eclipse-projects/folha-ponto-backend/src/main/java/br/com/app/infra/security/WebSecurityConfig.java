package br.com.app.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.app.infra.security.filter.JwtAuthenticationTokenFilter;

@Configuration // Classe de configuração.
@EnableWebSecurity // Habilita o security na aplicação.
@EnableGlobalMethodSecurity(prePostEnabled = true) // Realiza uma validação por método usando o @PreAuthorize.
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    /**
     * Utilizado pelo security para retornar a parte da senha.
     * 
     * @param authenticationManagerBuilder
     */
    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    /**
     * O modo de como a senha é geranciada será utilizando o BCryptPasswordEncoder.
     * 
     * @return PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Intercepta todas as requisições.
     * 
     * @return JwtAuthenticationTokenFilter
     */
    @Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
        return new JwtAuthenticationTokenFilter();
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf() //
                .disable() // Desabilita o csrf, pois para requisições stateless não importa o cross site script
                .exceptionHandling() //
                .authenticationEntryPoint(jwtAuthenticationEntryPoint) // Quando ocorrer uma excessão, deve ser chamada a exceção personalizada { @link JwtAuthenticationEntryPoint } (Problemas de autenticação)
                .and() //
                .sessionManagement() // Como será controlada a sessão do usuário
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Sessão será stateless, ou seja sem estado
                .and() //
                .authorizeRequests() //
                .antMatchers("/auth/**") // Será permitido acessar qualquer API que esteja abaixo de /auth, sem interferencia do security, pois neste momento a pessoa ainda não está autenticada, ou seja, irá se autenticar
                .permitAll() //
                .anyRequest() // Indica que qualquer outro request deverá estar autenticado
                .authenticated();
        httpSecurity.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
        httpSecurity.headers().cacheControl();
    }

}
