# Recognizers-Text for the JVM

Floada maintains this fork of [Microsoft Recognizers-Text](https://github.com/microsoft/Recognizers-Text) for its Java recognizers. The Maven modules live in [Java](Java/README.md). The resource generator reads YAML from [Patterns](Patterns), and the Java tests use the shared [Specs](Specs).

Build and test with JDK 17 from the `Java` directory:

```sh
mvn -B -Dcheckstyle.skip clean install
```

The GitHub Actions workflow publishes release artifacts from `master` and snapshots for same-repository pull requests.

## Contributing

See the [contributing guide](CONTRIBUTING.md) and [code of conduct](CODE_OF_CONDUCT.md).
