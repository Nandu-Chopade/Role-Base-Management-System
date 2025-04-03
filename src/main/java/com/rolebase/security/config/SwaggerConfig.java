package com.rolebase.security.config;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList("BearerAuth"))
                .components(new Components()
                        .addSecuritySchemes("BearerAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")))
                .info(new Info()
                        .title("Rolesbase User Management")
                        .version("1.0")
                        .description("<h2>Rolesbase User Management API</h2>\n" +
                                "<p>\n" +
                                "    The <strong>Rolesbase User Management API</strong> is a secure and scalable system designed for managing users, roles, and authentication in an enterprise environment. \n" +
                                "    It provides functionalities for user registration, authentication, role-based access control, and secure API interactions.\n" +
                                "</p>\n" +
                                "\n" +
                                "<h3>\uD83D\uDE80 Key Features</h3>\n" +
                                "<ul>\n" +
                                "    <li><strong>User Authentication & Authorization:</strong> Secure login and role-based access control (RBAC).</li>\n" +
                                "    <li><strong>Role Management:</strong> Assign and manage user roles such as <code>ADMIN</code>, <code>MANAGER</code>, <code>EMPLOYEE</code>, and <code>USER</code>.</li>\n" +
                                "    <li><strong>JWT Token-Based Security:</strong> API endpoints are secured using JSON Web Tokens (JWT).</li>\n" +
                                "    <li><strong>Address Management:</strong> Store and retrieve user address details.</li>\n" +
                                "    <li><strong>Spring Security Integration:</strong> Protect API endpoints with industry-standard security practices.</li>\n" +
                                "    <li><strong>RESTful API:</strong> Follows RESTful design principles for easy integration.</li>\n" +
                                "</ul>\n" +
                                "\n" +
                                "<h3>\uD83D\uDD11 Authentication</h3>\n" +
                                "<p>\n" +
                                "    To interact with secured endpoints, users must authenticate using their credentials. Upon successful login, a JWT token is provided, \n" +
                                "    which must be included in subsequent API requests via the <code>Authorization</code> header.\n" +
                                "</p>\n" +
                                "<pre><code>Authorization: Bearer YOUR_ACCESS_TOKEN</code></pre>\n" +
                                "\n" +
                                "<h3>\uD83D\uDCCC Endpoints Overview</h3>\n" +
                                "<ul>\n" +
                                "    <li><strong><code>POST /auth/register</code></strong> - Register a new user.</li>\n" +
                                "    <li><strong><code>POST /auth/login</code></strong> - Authenticate user and obtain JWT token.</li>\n" +
                                "    <li><strong><code>GET /users</code></strong> - Retrieve a list of registered users (Admin access required).</li>\n" +
                                "    <li><strong><code>GET /users/{id}</code></strong> - Fetch user details by ID.</li>\n" +
                                "    <li><strong><code>PUT /users/{id}</code></strong> - Update user information.</li>\n" +
                                "    <li><strong><code>DELETE /users/{id}</code></strong> - Delete a user (Admin access required).</li>\n" +
                                "</ul>\n" +
                                "\n" +
                                "<h3>\uD83D\uDCD8 Documentation</h3>\n" +
                                "<p>\n" +
                                "    For detailed API specifications, refer to the Swagger documentation provided below.\n" +
                                "</p>\n" +
                                "\n" +
                                "<h3>\uD83D\uDCA1 Tech Stack</h3>\n" +
                                "<ul>\n" +
                                "    <li><strong>Backend:</strong> Spring Boot, Spring Security, Hibernate, MySQL</li>\n" +
                                "    <li><strong>Authentication:</strong> JWT Token</li>\n" +
                                "    <li><strong>API Documentation:</strong> Swagger (OpenAPI)</li>\n" +
                                "</ul>\n" +
                                "\n" +
                                "<h3>\uD83D\uDCE9 Contact</h3>\n" +
                                "<p>\n" +
                                "    For support or inquiries, please contact <a href=\"mailto:nanduchopade05@gmail.com\">nanduchopade05@gmail.com</a>.\n" +
                                "</p>\n"));
    }

}
