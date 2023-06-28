package io.sleepcountry.blob.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.models.BlobItem;

@Service
public class SleepCountryBlogAzureService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SleepCountryBlogAzureService.class);

	@Value("${azure.myblob.url}")
	private String azureUrl;

	@Value("${azure.myblob.container}")
	private String containerName;

	public List<String> getStorageConnection() {
		LOGGER.info(">> getStorageConnection");

		LOGGER.info("AZURE Url : " + azureUrl);
		LOGGER.info("AZURE Container Name : " + containerName);

		BlobContainerClient blobContainerClient = container();
		List<String> strList = new ArrayList<>();
		for (BlobItem blobItem : blobContainerClient.listBlobs()) {
			strList.add(blobItem.getName());
			LOGGER.info(blobItem.getName());
		}
		LOGGER.info("<< getStorageConnection");
		return strList;
	}

	public String downloadFileFromBlobs(String blobItem) {
		LOGGER.info(">> downloadFileFromBlobs");
		BlobContainerClient blobContainerClient = container();
		BlobClient blobClient = blobContainerClient.getBlobClient(blobItem);
		
		/*
		 * We have many download methods in BlobClient
		 */
		
//		ByteArrayOutputStream byteArrayOutput = new ByteArrayOutputStream();
//		blobClient.download(byteArrayOutput);
		
		blobClient.downloadToFile("C:\\Ajit\\Study\\Java\\Vipin\\test.txt", true);
		
//		blobClient.download(new ByteArrayOutputStream());
		LOGGER.info("<< downloadFileFromBlobs");
		return "success";
	}
	
	
	private BlobContainerClient container() {
		LOGGER.info(">> Connecting to Azure : BEGIN");
		BlobServiceClient blobServiceClient = new BlobServiceClientBuilder().connectionString(azureUrl).buildClient();
		BlobContainerClient blobContainerClient = blobServiceClient.getBlobContainerClient(containerName);
		LOGGER.info("<< Connecting to Azure : END");
		return blobContainerClient;
	}
}
