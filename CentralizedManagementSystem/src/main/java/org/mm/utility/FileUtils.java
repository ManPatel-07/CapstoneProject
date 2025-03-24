package org.mm.utility;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUtils 
{

	@Value("${app.adminImg.localStorage}")
	private String localStoragePath;

	@Value("${app.adminImg.storageType}")
	private Integer storageType;
	
	public File uploadFile(MultipartFile file, String parentFolder) {
		try {
			File photoFile = null;
			if (file != null) {

				InputStream in = null;
				String destDir = localStoragePath + File.separator + parentFolder;
				File f = new File(destDir);
				if (!f.exists()) {
					f.mkdirs();
				}
				if (storageType == 1) { // S3

				}
				byte[] buffer = new byte[1024];
				in = file.getInputStream();

				Date currentDate = new Date();
				String fileName = String.valueOf(currentDate.getTime())
						+ file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
				photoFile = new File(destDir, fileName);

				BufferedInputStream bufferedInputStream = new BufferedInputStream(in);

				FileOutputStream fos;

				fos = new FileOutputStream(photoFile);

				int len;
				while ((len = bufferedInputStream.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}
				fos.close();

			}
			return photoFile;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public File getFile(String fileName, String parentFolder) {
		try {

			String filePath = localStoragePath + File.separator + parentFolder + File.separator + fileName;
			if (storageType == 1) { // S3

			}
			File file = new File(filePath);
			return file;	
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
