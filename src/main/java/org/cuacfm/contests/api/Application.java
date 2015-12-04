package org.cuacfm.contests.api;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Arrays;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.cuacfm.contests.api.config._configBasePackage;
import org.cuacfm.contests.api.rest._restBasePackage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.core.env.SimpleCommandLinePropertySource;

@ComponentScan(basePackageClasses = { _configBasePackage.class, _restBasePackage.class })
@EnableAutoConfiguration
public class Application {

	private static final Logger logger = LoggerFactory.getLogger(Application.class);

	@Inject
	private Environment env;

	public static void main(String[] args) throws UnknownHostException {
		System.setProperty("org.jboss.logging.provider", "slf4j");
		SpringApplication app = new SpringApplication(Application.class);
		SimpleCommandLinePropertySource source = new SimpleCommandLinePropertySource(args);

		addDefaultProfile(app, source);

		// Environment env = app.run(args).getEnvironment();
		app.run(args);
	}

	@PostConstruct
	public void initApplication() throws IOException {
		if (env.getActiveProfiles().length == 0)
			logger.warn("No Spring profile configured, running with default configuration");
		else
			logger.info("Running with Spring profile(s) : {}", Arrays.toString(env.getActiveProfiles()));
	}

	private static void addDefaultProfile(SpringApplication app, SimpleCommandLinePropertySource source) {
		if (!source.containsProperty("spring.profiles.active"))
			app.setAdditionalProfiles("dev");
	}
}
