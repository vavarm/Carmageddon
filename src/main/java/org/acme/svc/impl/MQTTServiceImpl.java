package org.acme.svc.impl;

import io.quarkus.logging.Log;
import org.acme.model.Game;
import org.eclipse.paho.client.mqttv3.MqttClient;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.svc.MQTTService;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

@ApplicationScoped
public class MQTTServiceImpl implements MQTTService {

    private MqttClient client;

    private final String topic = "varmandharumia";

    public MQTTServiceImpl() {
        try {
            client = new MqttClient("ws://mqtt.eclipseprojects.io/mqtt", MqttClient.generateClientId());
            MqttConnectOptions options = new MqttConnectOptions();
            options.setAutomaticReconnect(true);
            client.connect(options);
            Log.info("MQTT Service initialized");
        } catch (MqttException e) {
            Log.error("Error while initializing MQTT client", e);
        }
    }

    public void sendGameState() {
        Game game = Game.getInstance();
        Log.info("Sending game state: " + game);
        try {
            client.publish(topic, game.toString().getBytes(), 0, false);
        } catch (MqttException e) {
            Log.error("Error while sending game state", e);
        }
    }
}
