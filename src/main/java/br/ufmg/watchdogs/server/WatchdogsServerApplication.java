package br.ufmg.watchdogs.server;

import br.ufmg.watchdogs.server.mqtt.client.impl.MqttClientConnectorPahoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class WatchdogsServerApplication {

	private static MqttClientConnectorPahoImpl mqttClientConnector;

	@Autowired
	public WatchdogsServerApplication(MqttClientConnectorPahoImpl mqttClientConnector) {
		WatchdogsServerApplication.mqttClientConnector = mqttClientConnector;
	}

	public static void main(String[] args) {

		SpringApplication.run(WatchdogsServerApplication.class, args);
		System.out.println("\nApplication launched successfully!!!\n");

		WatchdogsServerApplication.mqttClientConnector.keepSendingTestMessages();
	}
}
