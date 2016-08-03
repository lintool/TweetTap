import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

public class TweetTap {
  public static void main(String[] argv) throws Exception {
    StatusListener listener = new StatusListener() {
      long statusCnt = 0;

      public void onStatus(Status status) {
        System.out.println(status.getId() + "\t" + status.getUser().getScreenName() + "\t" +
          status.getCreatedAt() + "\t" + status.getText().replaceAll("[\\t\\r\\n]", " "));
      }

      public void onScrubGeo(long userId, long upToStatusId) {}
      public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {}
      public void onTrackLimitationNotice(int numberOfLimitedStatuses) {}
      public void onStallWarning(StallWarning warning) {}

      public void onException(Exception e) {
        e.printStackTrace();
      }
    };

    TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
    twitterStream.addListener(listener);
    twitterStream.sample();
  }
}
