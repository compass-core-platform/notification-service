/** */
package controllers.notification;

import controllers.BaseController;
import java.util.concurrent.CompletionStage;

import controllers.RequestHandler;
import org.sunbird.BaseException;
import org.sunbird.request.LoggerUtil;
import org.sunbird.request.Request;
import play.mvc.Http;
import play.mvc.Result;
import utils.JsonKey;

/**
 * This controller will be responsible for different kind of notification handling.
 *
 * @author manzarul
 */
public class NotificationController extends BaseController {
  private static LoggerUtil logger = new LoggerUtil(NotificationController.class);

  public static final String NOTIFICATION = "notification";

  /**
   * This method will accept request for sending notification. notification can be sent on email,
   * sms or push on device
   *
   * @return a CompletableFuture of success response
   */
  public CompletionStage<Result> sendNotification() {
    logger.info("method call started for sendNotification ");
    Request request = new Request();
    try {
      request = createSBRequest(request());
    }catch (BaseException ex){
      return (CompletionStage<Result>)
              RequestHandler.handleFailureResponse(ex, httpExecutionContext, request(), request);
    }
    CompletionStage<Result> response = handleRequest(request, null, NOTIFICATION, request());
    logger.info("Method call end for sendNotification");
    return response;
  }

  /**
   * This method will accept request for sending sync notification. notification can be sent on
   * email, sms or push on device
   *
   * @return a CompletableFuture of success response
   */
  public CompletionStage<Result> sendSyncNotification() {
    logger.info("method call started for sendNotification ");
    request().getHeaders().addHeader(NOTIFICATION_DELIVERY_MODE, "sync");
    Request request = new Request();
    try {
      request = createSBRequest(request());
    }catch (BaseException ex){
      return (CompletionStage<Result>)
              RequestHandler.handleFailureResponse(ex, httpExecutionContext, request(), request);
    }
    CompletionStage<Result> response = handleRequest(request, null, NOTIFICATION, request());
    logger.info("Method call end for sendNotification");
    return response;
  }

  /**
   * This method will be used to verify otp.
   *
   * @return
   */
  public CompletionStage<Result> verifyOTP() {
    logger.info("method call started for verifyOTP ");
    Request request = new Request();
    try {
      request = createSBRequest(request());
    }catch (BaseException ex){
      return (CompletionStage<Result>)
              RequestHandler.handleFailureResponse(ex, httpExecutionContext, request(), request);
    }
    CompletionStage<Result> response = handleRequest(request, null, JsonKey.VERIFY_OTP, request());
    logger.info("Method call end for verifyOTP");
    return response;
  }


  /**
   * This method will accept request for sending new v2 notification. notification can be sent on
   * email, sms, Feed or push on device
   *
   * @return a CompletableFuture of success response
   */
  public CompletionStage<Result> sendV2Notification() {
    logger.info("method call started for sendNotification ");
    Request request = new Request();
    try {
      request = createSBRequest(request());
    }catch (BaseException ex){
      return (CompletionStage<Result>)
              RequestHandler.handleFailureResponse(ex, httpExecutionContext, request(), request);
    }
    request().getHeaders().addHeader(NOTIFICATION_DELIVERY_MODE, "sync");
    CompletionStage<Result> response = handleRequest(request, null, JsonKey.CREATE_NOTIFICATION, request());
    logger.info("Method call end for v2 sendNotification");
    return response;
  }



  /**
   * This method will accept reading the notification.
   *
   *
   * @return a CompletableFuture of success response
   */
  public CompletionStage<Result> readFeedNotification(String userId, Http.Request req) {
    logger.info("method call started for read Notification Feed ");
    Request request = new Request();
    try {
       request = createSBRequest(req);
    }catch (BaseException ex){
      return (CompletionStage<Result>)
              RequestHandler.handleFailureResponse(ex, httpExecutionContext, req, request);
    }
    request.getRequest().put("userId", userId);
    CompletionStage<Result> response = handleRequest(request, null, JsonKey.READ_FEED, request());
    logger.info("Method call end for read Notification Feed");
    return response;
  }

  /**
   * This method will accept update the notification.
   *
   *
   * @return a CompletableFuture of success response
   */
  public CompletionStage<Result> updateNotificationFeed() {
    logger.info("method call started for read Notification Feed ");
    Request request = new Request();
    try {
      request = createSBRequest(request());
    }catch (BaseException ex){
      return (CompletionStage<Result>)
              RequestHandler.handleFailureResponse(ex, httpExecutionContext, request(), request);
    }
    CompletionStage<Result> response = handleRequest(request, null, JsonKey.UPDATE_FEED, request());
    logger.info("Method call end for read Notification Feed");
    return response;
  }

}
