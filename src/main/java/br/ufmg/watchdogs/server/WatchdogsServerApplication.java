package br.ufmg.watchdogs.server;

import br.ufmg.watchdogs.server.mqtt.MqttClientConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class WatchdogsServerApplication {

	private static MqttClientConnection mqttClientConnection;

	@Autowired
	public WatchdogsServerApplication(MqttClientConnection mqttClientConnection) {
		this.mqttClientConnection = mqttClientConnection;
	}

	public static void main(String[] args) {

		SpringApplication.run(WatchdogsServerApplication.class, args);
		System.out.println("\nApplication launched successfully!!!\n");

		WatchdogsServerApplication.mqttClientConnection.keepSendingTestMessages();
	}
}
