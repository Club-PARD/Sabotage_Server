package club.pard.server.soonjji.sabotage.converter;

import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.RequiredArgsConstructor;

@Converter
@RequiredArgsConstructor
public class PasswordEncryptConverter implements AttributeConverter<String, String>{
    private final PasswordEncoder passwordEncoder;

    @Override
    public String convertToDatabaseColumn(String entityAttribute) { return passwordEncoder.encode(entityAttribute); }

    @Override
    public String convertToEntityAttribute(String databaseColumn) { return databaseColumn; }
}
