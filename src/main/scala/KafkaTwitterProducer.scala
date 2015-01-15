/**
 * Created by vishnu on 14/12/14.
 */
import java.io._
import java.util.Properties

import kafka.producer.{KeyedMessage, ProducerConfig, Producer}
import twitter4j._

object KafkaTwitterProducer extends App {


  //val properties = new Properties()
  //properties.load(new FileInputStream(args(0)))


  val apiKey = "*******"
  val apiSecret = "*******"
  val accessToken = "*******"
  val accessTokenSecret = "*******"

  val twitterConfig = new twitter4j.conf.ConfigurationBuilder()
    .setOAuthConsumerKey(apiKey)
    .setOAuthConsumerSecret(apiSecret)
    .setOAuthAccessToken(accessToken)
    .setOAuthAccessTokenSecret(accessTokenSecret)
    .setJSONStoreEnabled(true)
    .build


  val twitterStream = new TwitterStreamFactory(twitterConfig).getInstance()

  val outputFile = args(0)
  val fileWriter = new FileWriter(outputFile)

  val tweetsListener = new TweetsListener(fileWriter)
  twitterStream.addListener(tweetsListener)

  twitterStream.sample()



}

class TweetsListener(writer: Writer) extends StatusListener() {

  val props = new Properties()
  props.put("metadata.broker.list", "localhost:9092");
  props.put("zk.connect", "localhost:2181");
  props.put("serializer.class", "kafka.serializer.StringEncoder");
  props.put("request.required.acks", "1")
  val topic = "test"

  val producer = new Producer[Any,Any](new ProducerConfig(props))

  def onStatus(status: Status) {
    val tweet = status.getText
    if (tweet != null) {

      //val line = DataObjectFactory.getRawJSON(status);
      val line = status.getText
      producer.send(new KeyedMessage(topic,line))
      print(line)
      writer.write(line)
      writer.flush()
    }
  }

  def onDeletionNotice(statusDeletionNotice: StatusDeletionNotice) {}
  def onTrackLimitationNotice(numberOfLimitedStatuses: Int) {}
  def onException(ex: Exception) {}
  def onScrubGeo(arg0: Long, arg1: Long) {}
  def onStallWarning(warning: StallWarning) {}
}
