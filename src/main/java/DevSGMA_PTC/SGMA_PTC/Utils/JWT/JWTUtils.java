package DevSGMA_PTC.SGMA_PTC.Utils.JWT;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JWTUtils {

    @Value("${security.jwt.secret}")
    private String jwtSecret;
    @Value("${security.jwt.issuer}")
    private String issuer;
    @Value("${security.jwt.expiration}")
    private long msExpiration;

    private final Logger log = LoggerFactory.getLogger(JWTUtils.class);

    /**
     * Metodo para crear JWT
     *
     * @param id
     * @param email
     * @param role
     * @param level
     * @param grade puede ser null si no aplica (ej: Instructor)
     * @return token JWT como String
     */
    public String create(String id, String email, String role, String level, String grade) {

        //Decodifica el secreto Base64 y crea una clave HMAC-SHA segura
        SecretKey signingKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));

        //Obtiene la fecha catual y calcula la fecha de expiración
        Date now = new Date();
        Date expiration = new Date(now.getTime() + msExpiration);

        JwtBuilder builder = Jwts.builder()
                .setId(id)                                              // ID único (JWT ID)
                .setIssuedAt(now)                                       // Fecha de emisión
                .setSubject(email)                                     // Sujeto (usuario)
                .claim("id", id)
                .claim("role", role)
                .setIssuer(issuer)                                      // Emisor del token
                .setExpiration(msExpiration >= 0 ? expiration : null)   // Expiración (si es >= 0)
                .signWith(signingKey, SignatureAlgorithm.HS256);         // Firma con algoritmo HS256

        if (grade != null) {
            builder.claim("grade", grade);
        }

        return builder.compact();    // Convierte a String compacto
    }

    // Método para extraer grade del token
    public String extractGrade(String token) {
        Claims claims = parseToken(token);
        return claims.get("grade", String.class);  // devuelve null si no existe
    }

    // Métodos existentes
    public String extractRole(String token) {
        return parseToken(token).get("role", String.class);
    }

    public String extractLevel(String token) {
        return parseToken(token).get("level", String.class);
    }

    public String getValue(String jwt) {
        return parseClaims(jwt).getSubject();
    }

    public String getKey(String jwt) {
        return parseClaims(jwt).getId();
    }

    public Claims parseToken(String jwt) {
        return parseClaims(jwt);
    }

    public boolean validate(String token) {
        try {
            parseClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            log.warn("Token inválido: {}", e.getMessage());
            return false;
        }
    }



    //######################## METODOS COMPLEMENTARIOS ########################

    private Claims parseClaims(String jwt) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret)))
                .build()
                .parseClaimsJws(jwt)
                .getBody();
    }
}
