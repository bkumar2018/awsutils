package util;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AmazonS3Util {

    protected static AmazonS3 s3client;
    protected List<String> jsonFileNames = new ArrayList<>();

    public AmazonS3 connectS3Bucket(String accessKey, String secretKey){
        final AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        final AmazonS3 s3client = AmazonS3ClientBuilder.standard().withCredentials(
                new AWSStaticCredentialsProvider(credentials)).withRegion(Regions.US_EAST_1).build();
        return s3client;
    }


    //This method is to download file from S3 bucket
    public void downloadFilesFromS3Bucket(AmazonS3 amazonS3Client, String bucketName, String prefix, String fileName){
        final S3Object s3Object = amazonS3Client.getObject(bucketName, prefix+fileName);
        final S3ObjectInputStream inputStream = s3Object.getObjectContent();
        try{
            FileUtils.copyInputStreamToFile(inputStream, new File("./awss3json/" + fileName));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    //This method is to upload file to s3 location
    public void uploadFileToS3(AmazonS3 amazonS3Client, String bucketName, String path, String fileName){

        amazonS3Client.putObject(bucketName, path, new File(System.getProperty("user.dir")+
                "//PushS3Files//"+fileName));
    }

}
