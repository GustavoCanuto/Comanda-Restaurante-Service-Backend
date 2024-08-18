package com.projetoweb4.comandaRestaurante.service;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.projetoweb4.comandaRestaurante.infra.config.FtpProperties;
import com.projetoweb4.comandaRestaurante.service.recurso.FtpService;

@Service
public class UploadService {

	@Autowired
	private FtpService ftpService;

	@Autowired
	private FtpProperties ftpProperties;

	public String uploadImage(MultipartFile imagem, String prefixName, String imageBasePath) throws IOException {
		String imagemUrl = "";

		if (imagem != null && !imagem.isEmpty()) {
			String originalFileName = imagem.getOriginalFilename();
			String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));

			String hash = generateMD5(originalFileName);
			// Gerar um token único usando UUID
			String token = UUID.randomUUID().toString();

			String fileName = prefixName + hash + token + fileExtension;
			InputStream inputStream = imagem.getInputStream();
			String filePath = "public_html/" + imageBasePath + "/" + fileName;
			String imagePath = "/" + imageBasePath + "/" + fileName;

			try {
				ftpService.uploadFile(ftpProperties.getServer(), ftpProperties.getPort(), ftpProperties.getUser(),
						ftpProperties.getPass(), filePath, inputStream);
				imagemUrl = "https://" + ftpProperties.getServer() + imagePath;
			} catch (IOException e) {
				throw new IOException("Failed to upload file: " + e.getMessage(), e);
			}
		}

		return imagemUrl;
	}

	private String generateMD5(String input) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(input.getBytes());
			StringBuilder hexString = new StringBuilder();
			for (byte b : messageDigest) {
				String hex = Integer.toHexString(0xff & b);
				if (hex.length() == 1)
					hexString.append('0');
				hexString.append(hex);
			}
			return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

}
