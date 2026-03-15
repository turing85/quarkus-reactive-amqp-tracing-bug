package de.turing85.quarkus.workshop;

import jakarta.inject.Singleton;

import io.quarkus.logging.Log;
import io.quarkus.runtime.Startup;
import io.quarkus.vertx.ConsumeEvent;
import lombok.AccessLevel;
import lombok.Getter;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@Singleton
@Getter(AccessLevel.PRIVATE)
@SuppressWarnings("unused")
@Startup
public class ArtemisEventListener {
  private final Emitter<String> userEmitter;

  public ArtemisEventListener(@Channel("user-send") Emitter<String> userEmitter) {
    this.userEmitter = userEmitter;
  }

  @ConsumeEvent(value = "user", blocking = true)
  public void sendUserEvent(String event) {
    Log.infof("Sending user event: %s", event);
    getUserEmitter().send(event);
  }

  @Incoming("user-receive")
  void receiveUser(String userMessage) {
    Log.infof("Receiving user: %s", userMessage);
  }
}
