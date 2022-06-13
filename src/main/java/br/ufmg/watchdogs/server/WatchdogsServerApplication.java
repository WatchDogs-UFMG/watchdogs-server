package br.ufmg.watchdogs.server;

import br.ufmg.watchdogs.server.mqtt.client.impl.MqttClientConnectorPahoImpl;
import br.ufmg.watchdogs.server.mqtt.uplink.MqttSubHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class WatchdogsServerApplication {

	private static MqttClientConnectorPahoImpl mqttClientConnector;
	private static MqttSubHandle mqttSubHandle;

	@Autowired
	public WatchdogsServerApplication(
			MqttClientConnectorPahoImpl mqttClientConnector,
			MqttSubHandle mqttSubHandle
	) {
		WatchdogsServerApplication.mqttClientConnector = mqttClientConnector;
		WatchdogsServerApplication.mqttSubHandle = mqttSubHandle;
	}

	public static void main(String[] args) {

		SpringApplication.run(WatchdogsServerApplication.class, args);

		mqttClientConnector.connect();
		mqttSubHandle.listen();

		System.out.println("\nApplication launched successfully!!!\n");

		// WatchdogsServerApplication.mqttClientConnector.keepSendingTestMessages();
	}
}
