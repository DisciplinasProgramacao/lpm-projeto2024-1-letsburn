package com.letsburn.lpmprojeto20241letsburn.config;

import java.util.TimeZone;

import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;

@Configuration

public class SpringTimeZoneConfig {
  @PostConstruct
  public void timeZoneConfig() {
    TimeZone.setDefault(TimeZone.getTimeZone("America/Sao_Paulo"));
  }
}
