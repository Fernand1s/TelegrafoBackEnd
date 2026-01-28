package com.telegrafo.backend.service;

import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

@Service
public class ImagemService {

    public String processarImagem(InputStream inputStream) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            Thumbnails.of(inputStream)
                    .size(600, 600)
                    .outputFormat("jpeg")
                    .outputQuality(0.7)
                    .toOutputStream(outputStream);

            byte[] processedImageBytes = outputStream.toByteArray();

            return Base64.getEncoder().encodeToString(processedImageBytes);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao processar imagem", e);
        }
    }
}