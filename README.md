This is the aws utility for below task:
1. Connect to AWS
2. Download files from s3 
3. Upload file to S3

Require gradle dependencies as below :
dependencies {
    testCompile group: 'junit', name: 'junit', version: '4.12'
    compile 'com.amazonaws:aws-java-sdk:1.11.163'
    compile 'commons-io:commons-io:2.11.0'
}
