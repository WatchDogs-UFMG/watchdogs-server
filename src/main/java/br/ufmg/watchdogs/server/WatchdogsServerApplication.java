package br.ufmg.watchdogs.server;

import br.ufmg.watchdogs.server.mqtt.client.impl.MqttClientAdapterPahoImpl;
import br.ufmg.watchdogs.server.mqtt.client.impl.MqttCallbackAdapterPahoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class WatchdogsServerApplication {

	private static MqttClientAdapterPahoImpl mqttClientConnector;
	private static MqttCallbackAdapterPahoImpl mqttSubListenerAdapterPahoImpl;

	@Autowired
	public WatchdogsServerApplication(
			MqttClientAdapterPahoImpl mqttClientConnector,
			MqttCallbackAdapterPahoImpl mqttSubListenerAdapterPahoImpl
	) {
		WatchdogsServerApplication.mqttClientConnector = mqttClientConnector;
		WatchdogsServerApplication.mqttSubListenerAdapterPahoImpl = mqttSubListenerAdapterPahoImpl;
	}

	public static void main(String[] args) {

		SpringApplication.run(WatchdogsServerApplication.class, args);

		mqttClientConnector.connect();
		mqttSubListenerAdapterPahoImpl.listen();

		System.out.println("\nApplication launched successfully!!!\n");

		// WatchdogsServerApplication.mqttClientConnector.keepSendingTestMessages();
	}
}
