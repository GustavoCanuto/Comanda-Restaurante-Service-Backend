package com.projetoweb4.comandaRestaurante.service.recurso;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class FtpService {

    private static final Logger logger = LoggerFactory.getLogger(FtpService.class);

    private FTPClient ftpClient;

    public FtpService() {
        this.ftpClient = new FTPClient();
    }

    public void connect(String server, int port, String user, String pass) throws IOException {
        logger.info("Connecting to FTP server {} on port {}", server, port);
        ftpClient.connect(server, port);
        boolean login = ftpClient.login(user, pass);
        if (!login) {
            throw new IOException("FTP login failed");
        }
        logger.info("Successfully logged in to FTP server");

        ftpClient.enterLocalPassiveMode();
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
    }

    public void disconnect() throws IOException {
        if (ftpClient.isConnected()) {
            ftpClient.logout();
            logger.info("Logged out from FTP server");
            ftpClient.disconnect();
            logger.info("Disconnected from FTP server");
        }
    }

    public void uploadFile(String server, int port, String user, String pass, String filePath, InputStream inputStream) throws IOException {
        connect(server, port, user, pass);
        try (InputStream input = inputStream) {
            boolean done = ftpClient.storeFile(filePath, input);
            if (!done) {
                throw new IOException("Failed to upload file to " + filePath);
            }
            logger.info("File uploaded successfully to {}", filePath);
        } finally {
            disconnect();
        }
    }

    public void deleteFile(String server, int port, String user, String pass, String remoteFilePath) throws IOException {
        connect(server, port, user, pass);
        try {
            FTPFile[] ftpFiles = ftpClient.listFiles(remoteFilePath);
            if (ftpFiles.length > 0) {
                boolean deleted = ftpClient.deleteFile(remoteFilePath);
                if (!deleted) {
                    throw new IOException("Failed to delete file at " + remoteFilePath);
                }
                logger.info("File deleted successfully at {}", remoteFilePath);
            } else {
                logger.warn("File does not exist at {}", remoteFilePath);
            }
        } finally {
            disconnect();
        }
    }
}