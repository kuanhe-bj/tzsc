package io.renren.vas.service;

import org.springframework.web.multipart.MultipartFile;

public interface IimportService {
    Integer importExcel(MultipartFile myFile);
}
