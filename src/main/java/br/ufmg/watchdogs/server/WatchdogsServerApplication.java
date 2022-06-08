package br.ufmg.watchdogs.server;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

@SpringBootApplication
@EnableSwagger2
public class WatchdogsServerApplication {

	static Environment env;

	@Value("${mqtt.broker.url}")
	static private String broker;

	@Autowired
	public WatchdogsServerApplication(Environment env) {
		this.env = env;
	}

	public static void main(String[] args) {

		SpringApplication.run(WatchdogsServerApplication.class, args);
		System.out.println("\nApplication launched successfully!!!\n");

//		String broker;
//
//		if (Arrays.stream(env.getActiveProfiles()).anyMatch(profile -> profile.equals("dev"))) {
//			broker = "tcp://127.0.0.1:7892";
//		} else if (Arrays.stream(env.getActiveProfiles()).anyMatch(profile ->  profile.equals("prod"))){
//			broker = "tcp://172.17.0.1:1883";
//		} else {
//			broker = "tcp://127.0.0.1:7892";
//		}

		String topic = "/watchdogs/uplink/ack";
		String content = "Message from MqttPublishSample";
		int qos = 0;
		String clientId = "WD-1";
		MemoryPersistence persistence = new MemoryPersistence();

		try {

			MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
			MqttConnectOptions connOpts = new MqttConnectOptions();
			connOpts.setCleanSession(true);
			System.out.println("Connecting to broker: "+broker);
			sampleClient.connect(connOpts);
			System.out.println("Connected");

			for (int i = 0; i < 50; i++) {

				Thread.sleep(1000);

				System.out.println("Publishing message: "+content);
				MqttMessage message = new MqttMessage(content.getBytes());
				message.setQos(qos);
				sampleClient.publish(topic, message);
				System.out.println("Message published");
			}

			sampleClient.disconnect();
			System.out.println("Disconnected");
			System.exit(0);

		} catch(MqttException me) {
			System.out.println("reason "+me.getReasonCode());
			System.out.println("msg "+me.getMessage());
			System.out.println("loc "+me.getLocalizedMessage());
			System.out.println("cause "+me.getCause());
			System.out.println("excep "+me);
			me.printStackTrace();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
