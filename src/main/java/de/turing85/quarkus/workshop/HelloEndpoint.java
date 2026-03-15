package de.turing85.quarkus.workshop;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import io.vertx.core.eventbus.EventBus;
import lombok.RequiredArgsConstructor;

@Path("/hello")
@Produces(MediaType.APPLICATION_JSON)
@RequiredArgsConstructor
public class HelloEndpoint {
  private final EventBus eventBus;

  @GET
  public HelloResponse sayHello() {
    final HelloResponse toReturn = HelloResponse.builder().build();
    sendUserEvent(toReturn);
    return toReturn;
  }

  private void sendUserEvent(HelloResponse toReturn) {
    eventBus.publish("user", toReturn.getName());
  }

  @Path("{name}")
  @GET
  public HelloResponse sayHello(@PathParam("name") @NotNull @NotEmpty @NotBlank String name) {
    // @formatter:off
    final HelloResponse toReturn = HelloResponse.builder()
        .name(name)
        .build();
    // @formatter:on
    sendUserEvent(toReturn);
    return toReturn;
  }
}
