package com.cartisan.modern.acceptancetest.data;

import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ApplicationConfigurationsTest {
    ConfigurableApplicationContext mockConfigurableApplicationContext = mock(ConfigurableApplicationContext.class);
    ConfigurableEnvironment mockConfigurableEnvironment = mock(ConfigurableEnvironment.class);
    Map<String, Object> systemProperties = new HashMap<>();
    private final ApplicationConfigurations applicationConfigurations = applicationConfigurations();

    @Test
    public void overwrite() throws Exception {
        applicationConfigurations.overwrite("name", "value");
        assertThat(systemProperties).containsExactly(new SimpleEntry<>("name", "value"));
    }

    @Test
    public void restore() throws Exception {
        systemProperties.put("existingName", "existingValue");
        applicationConfigurations.overwrite("name", "value");

        applicationConfigurations.restore();

        assertThat(systemProperties).doesNotContain(new SimpleEntry<>("name", "value"));
        assertThat(systemProperties).containsExactly(new SimpleEntry<>("existingName", "existingValue"));
    }

    public ApplicationConfigurations applicationConfigurations() {
        when(mockConfigurableEnvironment.getSystemProperties()).thenReturn(systemProperties);
        when(mockConfigurableApplicationContext.getEnvironment()).thenReturn(mockConfigurableEnvironment);
        return new ApplicationConfigurations(mockConfigurableApplicationContext);
    }

}