package uk.co.luciditysoftware.campervibe.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

@Configuration
@ComponentScan(
        basePackages = "uk.co.luciditysoftware.campervibe.site",
        excludeFilters = @ComponentScan.Filter(Controller.class)
)
public class RootContextConfiguration
{
}

