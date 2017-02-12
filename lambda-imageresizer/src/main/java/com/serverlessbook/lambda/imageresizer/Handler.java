package com.serverlessbook.lambda.imageresizer;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import org.apache.log4j.Logger;

public class Handler implements RequestHandler<S3Event, Void> {

  private static final Logger LOGGER = Logger.getLogger(Handler.class);

  private void resizeImage(String bucket, String key) {
    LOGGER.info("Resizing s3://" + bucket + "/" + key);
  }

  @Override
  public Void handleRequest(S3Event input, Context context) {
    input.getRecords().forEach(s3EventNotificationRecord ->
        resizeImage(s3EventNotificationRecord.getS3().getBucket().getName(),
            s3EventNotificationRecord.getS3().getObject().getKey()));
    return null;
  }
}
