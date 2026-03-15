package de.turing85.quarkus.workshop;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Jacksonized
@Builder
public class HelloResponse {
  @lombok.Builder.Default
  String greeting = "Hello";

  @lombok.Builder.Default
  String name = "John Doe";
}
